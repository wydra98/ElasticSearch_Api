package Test.TransformApi.CreateTransforms

import Test.Elasticsearch
import Test.TransformApi.CreateTransforms.PivotParts.Pivot
import Test.TransformApi.CreateTransforms.SettingsParts.Settings
import Test.TransformApi.CreateTransforms.SourceParts.Source
import Test.TransformApi.CreateTransforms.SyncParts.SyncProperties.Sync
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.stream.ActorMaterializer
import akka.util.ByteString

import scala.concurrent.{ExecutionContextExecutor, Future}
import scala.util.{Failure, Success}


case class CreateTransform(id: String,
                           description: Option[String],
                           dest: Dest,
                           frequency: Option[String],
                           pivot: Pivot,
                           settings: Option[Settings],
                           source: Option[Source],
                           sync: Option[Sync]
                 ) {

  implicit val system: ActorSystem = ActorSystem()
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher

  def createTransform(index: String): Unit = {
    val port = "http://localhost:9200"
    val request = HttpRequest(
      method = HttpMethods.PUT,
      uri = s"$port/_transform/$index",
      entity = HttpEntity(ContentTypes.`application/json`, createTransformFromJson())
    )
    getResponseFromRequest(request)
  }

  def createTransformFromJson(): String = {


    "hejo"
  }

  def getResponseFromRequest(request: HttpRequest): Unit ={
    val responseFuture: Future[HttpResponse] = Http().singleRequest(request)
    responseFuture
      .onComplete {
        case Success(res) => {
          //println(res + "\n")
          res.entity.dataBytes.runFold(ByteString(""))(_ ++ _).foreach { body =>
            println("Got response, body: \n" + body.utf8String)
          }
        }
        case Failure(_) => sys.error("something wrong")
      }
  }
}

