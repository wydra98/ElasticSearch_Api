package TransformsApi


import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpMethods, HttpRequest}
import io.circe.generic.auto._
import io.circe.syntax._

case class TransformApi() {

  def postTransform(transformConfig: TransformConfig, id: String, port: String): Unit = {
//
//    var json = transformConfig
//      .asJson
//      .dropNullValues
//      .mapObject(_.mapValues(_.dropNullValues))
//      .mapObject(_.mapValues(_.mapObject(_.mapValues(_.dropNullValues))))
//      .mapObject(_.mapValues(_.mapObject(_.mapValues(_.mapObject(_.mapValues(_.dropNullValues))))))
//      .noSpaces
//
//    println(json)
//    if (transformConfig.source.query.isDefined) {
//      val correctQuery = transformConfig.source.query.get
//      transformConfig.source.query = Some("queryToChange")
//
//      json = json.replaceAll("\"queryToChange\"", correctQuery)
//      println(json)
//    }
//
//    val request = HttpRequest(
//      method = HttpMethods.POST,
//      uri = s"$port/_transform/_preview?pretty",
//      entity = HttpEntity(ContentTypes.`application/json`, json)
//    )
//    Response().getResponseFromRequest(request)
  }


  def putTransform(transformConfig: TransformConfig, id: String, port: String): Unit = {
    val json = transformConfig
      .asJson
      .dropNullValues
      .mapObject(_.mapValues(_.dropNullValues))
      .mapObject(_.mapValues(_.mapObject(_.mapValues(_.dropNullValues))))
      .mapObject(_.mapValues(_.mapObject(_.mapValues(_.mapObject(_.mapValues(_.dropNullValues))))))
      .noSpaces

    val request = HttpRequest(
      method = HttpMethods.PUT,
      uri = s"$port/_transform/$id",
      entity = HttpEntity(ContentTypes.`application/json`, json)
    )
    Response().getResponseFromRequest(request)
  }

  def startTransform(id: String, port: String) {
    val request = HttpRequest(
      method = HttpMethods.POST,
      uri = s"$port/_transform/$id/_start"
    )
    Response().getResponseFromRequest(request)
  }

  def stopTransform(id: String, port: String) {
    val request = HttpRequest(
      method = HttpMethods.POST,
      uri = s"$port/_transform/$id/_stop"
    )
    Response().getResponseFromRequest(request)
  }

  def deleteTransform(id: String, port: String) {
    val request = HttpRequest(
      method = HttpMethods.DELETE,
      uri = s"$port/_transform/$id"
    )
    Response().getResponseFromRequest(request)
  }

  def getTransform(id: String, port: String) {
    val request = HttpRequest(
      method = HttpMethods.GET,
      uri = s"$port/_transform/$id?pretty"
    )
    Response().getResponseFromRequest(request)
  }

  def getTransformStatistics(id: String, port: String) {
    val request = HttpRequest(
      method = HttpMethods.GET,
      uri = s"$port/_transform/$id/_stats?pretty"
    )
    Response().getResponseFromRequest(request)
  }
}
