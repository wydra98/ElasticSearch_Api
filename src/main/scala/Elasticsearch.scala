import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.stream.ActorMaterializer
import akka.util.ByteString

import scala.concurrent.Future
import scala.util.{Failure, Success}

case class Elasticsearch() {

  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  def getResponseFromRequest(request: HttpRequest): Unit ={
    val responseFuture: Future[HttpResponse] = Http().singleRequest(request)
    responseFuture
      .onComplete {
        case Success(res) => {
          println("\n"+ res + "\n")
          res.entity.dataBytes.runFold(ByteString(""))(_ ++ _).foreach { body =>
            println("Got response, body: \n" + body.utf8String)
          }
        }
        case Failure(_) => sys.error("something wrong")
      }
  }

  def getAllDocumentsFromIndex(index: String) {
    val port = "http://localhost:9200"
    val request = HttpRequest(
      method = HttpMethods.GET,
      uri = s"$port/$index/_search?pretty=true",
      entity = HttpEntity(ContentTypes.`application/json`, "{ \"query\": { \"match_all\" : {} } }")
    )
    getResponseFromRequest(request)
  }

  def deleteIndex(index: String) {
    val port = "http://localhost:9200"
    val request = HttpRequest(
      method = HttpMethods.DELETE,
      uri = s"$port/$index?pretty=true",
    )
    getResponseFromRequest(request)
  }
}
