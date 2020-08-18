package TransformsApi

import Test.Elasticsearch
import TransformsApi.TransformConfigs.TransformConfig
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpMethods, HttpRequest}
import io.circe.generic.auto._
import io.circe.syntax._

case class Transform() {

  val port = "http://localhost:9200"

  def postTransform(transformConfig: TransformConfig): Unit = {
    val json = transformConfig
      .asJson
      .dropNullValues
      .mapObject(_.mapValues(_.dropNullValues))
      .mapObject(_.mapValues(_.mapObject(_.mapValues(_.dropNullValues))))
      .mapObject(_.mapValues(_.mapObject(_.mapValues(_.mapObject(_.mapValues(_.dropNullValues))))))
      .noSpaces
    val request = HttpRequest(
      method = HttpMethods.POST,
      uri = s"$port/_transform/_preview?pretty",
      entity = HttpEntity(ContentTypes.`application/json`, json)
    )
    Elasticsearch().getResponseFromRequest(request)
  }

  def putTransform(transformConfig: TransformConfig): Unit = {
    val json = transformConfig
      .asJson
      .dropNullValues
      .mapObject(_.mapValues(_.dropNullValues))
      .mapObject(_.mapValues(_.mapObject(_.mapValues(_.dropNullValues))))
      .mapObject(_.mapValues(_.mapObject(_.mapValues(_.mapObject(_.mapValues(_.dropNullValues))))))
      .noSpaces
    val request = HttpRequest(
      method = HttpMethods.PUT,
      uri = s"$port/_transform/${transformConfig.id}",
      entity = HttpEntity(ContentTypes.`application/json`, json)
    )
    Elasticsearch().getResponseFromRequest(request)
  }

  def startTransform(index: String) {
    val request = HttpRequest(
      method = HttpMethods.POST,
      uri = s"$port/_transform/$index/_start"
    )
    Elasticsearch().getResponseFromRequest(request)
  }

  def stopTransform(index: String) {
    val request = HttpRequest(
      method = HttpMethods.POST,
      uri = s"$port/_transform/$index/_stop"
    )
    Elasticsearch().getResponseFromRequest(request)
  }

  def deleteTransform(index: String) {
    val request = HttpRequest(
      method = HttpMethods.DELETE,
      uri = s"$port/_transform/$index"
    )
    Elasticsearch().getResponseFromRequest(request)
  }

  def getTransform(index: String) {
    val request = HttpRequest(
      method = HttpMethods.GET,
      uri = s"$port/_transform/$index?pretty"
    )
    Elasticsearch().getResponseFromRequest(request)
  }

  def getTransformStatistics(index: String) {
    val request = HttpRequest(
      method = HttpMethods.GET,
      uri = s"$port/_transform/$index/_stats?pretty"
    )
    Elasticsearch().getResponseFromRequest(request)
  }
}
