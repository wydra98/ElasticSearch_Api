package Test

import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpMethods, HttpRequest}

case class Transform() {

  def postTransform(): Unit = {
    val port = "http://localhost:9200"
    val request = HttpRequest(
      method = HttpMethods.POST,
      uri = s"$port/_transform/_preview?pretty",
      entity = HttpEntity(ContentTypes.`application/json`, TransformRequestBody().transformEntity1)
    )
    Elasticsearch().getResponseFromRequest(request)
  }

  def putTransform(index: String): Unit = {
    val port = "http://localhost:9200"
    val request = HttpRequest(
      method = HttpMethods.PUT,
      uri = s"$port/_transform/$index",
      entity = HttpEntity(ContentTypes.`application/json`, TransformRequestBody().transformEntity1)
    )
    Elasticsearch().getResponseFromRequest(request)
  }

  def startTransform(index: String) {
    val port = "http://localhost:9200"
    val request = HttpRequest(
      method = HttpMethods.POST,
      uri = s"$port/_transform/$index/_start"
    )
    Elasticsearch().getResponseFromRequest(request)
  }

  def stopTransform(index: String) {
    val port = "http://localhost:9200"
    val request = HttpRequest(
      method = HttpMethods.POST,
      uri = s"$port/_transform/$index/_stop"
    )
    Elasticsearch().getResponseFromRequest(request)
  }

  def deleteTransform(index: String) {
    val port = "http://localhost:9200"
    val request = HttpRequest(
      method = HttpMethods.DELETE,
      uri = s"$port/_transform/$index"
    )
    Elasticsearch().getResponseFromRequest(request)
  }

  def getTransform(index: String) {
    val port = "http://localhost:9200"
    val request = HttpRequest(
      method = HttpMethods.GET,
      uri = s"$port/_transform/$index?pretty"
    )
    Elasticsearch().getResponseFromRequest(request)
  }

  def getTransformStatistics(index: String) {
    val port = "http://localhost:9200"
    val request = HttpRequest(
      method = HttpMethods.GET,
      uri = s"$port/_transform/$index/_stats?pretty"
    )
    Elasticsearch().getResponseFromRequest(request)
  }
}
