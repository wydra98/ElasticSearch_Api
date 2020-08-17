package Test.TransformApi.CreateTransforms

import Test.TransformApi.CreateTransforms.PivotParts.Pivot
import Test.TransformApi.CreateTransforms.SettingsParts.Settings
import Test.TransformApi.CreateTransforms.SourceParts.Source
import Test.TransformApi.CreateTransforms.SyncParts.SyncProperties.Sync
import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import io.circe.Printer

import scala.concurrent.ExecutionContextExecutor

case class CreateTransform(id: String,
                           source: Source,
                           dest: Dest,
                           pivot: Pivot,
                           description: Option[String] = None,
                           frequency: Option[String] = None,
                           settings: Option[Settings] = None,
                           sync: Option[Sync] = None
                          ){

  implicit val system: ActorSystem = ActorSystem()
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher

  val printer = Printer.noSpaces.copy(dropNullValues = true)
  import io.circe.generic.auto._
  import io.circe.syntax._

//  def createTransform(): Unit = {
//    val port = "http://localhost:9200"
//    val request = HttpRequest(
//      method = HttpMethods.PUT,
//      uri = s"$port/_transform/$id",
//      entity = HttpEntity(ContentTypes.`application/json`, createTransformFromJson())
//    )
//    getResponseFromRequest(request)
//  }

  def createTransformFromJson(): Unit = {
    var jsonBody: String = ""
    jsonBody += "{"
    jsonBody += "\"source\":"
    jsonBody += printer.print(source.asJson)
    jsonBody += ","
    jsonBody += "\"dest\":"
    jsonBody += printer.print(dest.asJson)
    jsonBody += ","
    jsonBody += "\"pivot\":"
    jsonBody += printer.print(pivot.asJson)
    jsonBody += ","
    if(description.isDefined){
      jsonBody += "\"description\":"
      jsonBody += printer.print(description.asJson)
      jsonBody += ","
    }
    if(frequency.isDefined){
      jsonBody += "\"frequency\":"
      jsonBody += printer.print(description.asJson)
      jsonBody += ","
    }
    if(settings.isDefined){
      jsonBody += "\"settings\":"
      jsonBody += printer.print(settings.asJson)
      jsonBody += ","
    }
    if(sync.isDefined){
      jsonBody += "\"frequency\":"
      jsonBody += printer.print(sync.asJson)
      jsonBody += ","
    }






    jsonBody += "}"


    println(jsonBody)
    //jsonBody
  }

//  def getResponseFromRequest(request: HttpRequest): Unit = {
//    val responseFuture: Future[HttpResponse] = Http().singleRequest(request)
//    responseFuture
//      .onComplete {
//        case Success(res) => {
//          //println(res + "\n")
//          res.entity.dataBytes.runFold(ByteString(""))(_ ++ _).foreach { body =>
//            println("Response is succesfully" /* + body.utf8String*/)
//          }
//        }
//        case Failure(_) => sys.error("something wrong")
//      }
//  }
}

