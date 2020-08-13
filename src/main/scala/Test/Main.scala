package Test

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.stream.ActorMaterializer
import scala.concurrent.Future
import scala.util.{Failure, Success}

object Main extends App {

  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  val port = "http://localhost:9200"
  val index = "kibana_sample_data_flights"

  val request = HttpRequest(
    method = HttpMethods.POST,
    uri = s"$port/$index/_search?pretty=true",
    entity = HttpEntity(ContentTypes.`application/json`, "{ \"query\": { \"match_all\" : {} } }")
  )

  val responseFuture: Future[HttpResponse] = Http().singleRequest(request)
  responseFuture
    .onComplete {
      case Success(res) => {
        println(res)
        println(res.attributes.toString())
        println(res.headers.toString())
        println(res.entity.toString)
      }
      case Failure(_) => sys.error("something wrong")
    }

}
