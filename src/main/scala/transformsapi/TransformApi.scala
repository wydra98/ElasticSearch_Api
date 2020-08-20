package transformsapi

import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpMethods, HttpRequest}
import io.circe.generic.auto._
import io.circe.syntax._

case class TransformApi() {


  def postTransform(transformConfig: TransformConfig, port: String): Unit = {
    val json = transformConfig
      .asJson
      .dropNullValues
      .mapObject(_.mapValues(_.dropNullValues))
      .mapObject(_.mapValues(_.mapObject(_.mapValues(_.dropNullValues))))
      .mapObject(_.mapValues(_.mapObject(_.mapValues(_.mapObject(_.mapValues(_.dropNullValues))))))
      .mapObject(_.mapValues(_.mapObject(_.mapValues(_.mapObject(_.mapValues(_.mapObject(_.mapValues(_.dropNullValues))))))))
      .noSpaces
      val request = HttpRequest(
        method = HttpMethods.POST,
        uri = s"$port/_transform/_preview?pretty",
        entity = HttpEntity(ContentTypes.`application/json`, json)
      )
      Response().getResponseFromRequest(request)
  }

  def putTransform(transformConfig: TransformConfig, index: String, port: String): Unit = {
    val json = transformConfig
      .asJson
      .dropNullValues
      .mapObject(_.mapValues(_.dropNullValues))
      .mapObject(_.mapValues(_.mapObject(_.mapValues(_.dropNullValues))))
      .mapObject(_.mapValues(_.mapObject(_.mapValues(_.mapObject(_.mapValues(_.dropNullValues))))))
      .mapObject(_.mapValues(_.mapObject(_.mapValues(_.mapObject(_.mapValues(_.mapObject(_.mapValues(_.dropNullValues))))))))
      .noSpaces
    val request = HttpRequest(
      method = HttpMethods.PUT,
      uri = s"$port/_transform/$index",
      entity = HttpEntity(ContentTypes.`application/json`, json)
    )
    Response().getResponseFromRequest(request)
  }

  def updateTransform(transformConfig: TransformUpdateConfig, index: String, port: String): Unit = {
    val json = transformConfig
      .asJson
      .dropNullValues
      .mapObject(_.mapValues(_.dropNullValues))
      .mapObject(_.mapValues(_.mapObject(_.mapValues(_.dropNullValues))))
      .mapObject(_.mapValues(_.mapObject(_.mapValues(_.mapObject(_.mapValues(_.dropNullValues))))))
      .noSpaces
    val request = HttpRequest(
      method = HttpMethods.POST,
      uri = s"$port/_transform/$index/_update?pretty",
      entity = HttpEntity(ContentTypes.`application/json`, json)
    )
    Response().getResponseFromRequest(request)
  }

  def startTransform(index: String, port: String) {
    val request = HttpRequest(
      method = HttpMethods.POST,
      uri = s"$port/_transform/$index/_start"
    )
    Response().getResponseFromRequest(request)
  }

  def stopTransform(index: String, port: String) {
    val request = HttpRequest(
      method = HttpMethods.POST,
      uri = s"$port/_transform/$index/_stop"
    )
    Response().getResponseFromRequest(request)
  }

  def deleteTransform(index: String, port: String) {
    val request = HttpRequest(
      method = HttpMethods.DELETE,
      uri = s"$port/_transform/$index"
    )
    Response().getResponseFromRequest(request)
  }

  def getTransform(index: String, port: String) {
    val request = HttpRequest(
      method = HttpMethods.GET,
      uri = s"$port/_transform/$index?pretty"
    )
    Response().getResponseFromRequest(request)
  }

  def getTransformStatistics(index: String, port: String) {
    val request = HttpRequest(
      method = HttpMethods.GET,
      uri = s"$port/_transform/$index/_stats?pretty"
    )
    Response().getResponseFromRequest(request)
  }

}
