package Test

import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpMethods, HttpRequest}

case class Rollup() {

  def putRollUpJob(jobId: String): Unit = {
    val port = "http://localhost:9200"
    val request = HttpRequest(
      method = HttpMethods.PUT,
      uri = s"$port/_rollup/job/$jobId",
      entity = HttpEntity(ContentTypes.`application/json`, RollupRequestBody().rollupEntity1)
    )
    Elasticsearch().getResponseFromRequest(request)
  }

  def startRollupJob(jobId: String) {
    val port = "http://localhost:9200"
    val request = HttpRequest(
      method = HttpMethods.POST,
      uri = s"$port/_rollup/job/$jobId/_start"
    )
    Elasticsearch().getResponseFromRequest(request)
  }

  def stopRollupJob(jobId: String) {
    val port = "http://localhost:9200"
    val request = HttpRequest(
      method = HttpMethods.POST,
      uri = s"$port/_rollup/job/$jobId/_stop"
    )
    Elasticsearch().getResponseFromRequest(request)
  }

  def deleteRollupJob(jobId: String) {
    val port = "http://localhost:9200"
    val request = HttpRequest(
      method = HttpMethods.DELETE,
      uri = s"$port/_rollup/job/$jobId"
    )
    Elasticsearch().getResponseFromRequest(request)
  }

  def getRollupJobDetails(jobId: String) {
    val port = "http://localhost:9200"
    val request = HttpRequest(
      method = HttpMethods.GET,
      uri = s"$port/_rollup/job/$jobId?pretty"
    )
    Elasticsearch().getResponseFromRequest(request)
  }

  def getRollupCapabilities(index: String) {
    val port = "http://localhost:9200"
    val request = HttpRequest(
      method = HttpMethods.GET,
      uri = s"$port/_rollup/data/$index?pretty"
    )
    Elasticsearch().getResponseFromRequest(request)
  }

  def getRollupIndexCapabilities(index: String) {
    val port = "http://localhost:9200"
    val request = HttpRequest(
      method = HttpMethods.GET,
      uri = s"$port/$index/_rollup/data?pretty"
    )
    Elasticsearch().getResponseFromRequest(request)
  }

  def searchRollupData(index: String, query: String) {
    val port = "http://localhost:9200"
    val request = HttpRequest(
      method = HttpMethods.GET,
      uri = s"$port/$index/_rollup_search?pretty=true",
      entity = HttpEntity(ContentTypes.`application/json`, query)
    )
    Elasticsearch().getResponseFromRequest(request)
  }

}
