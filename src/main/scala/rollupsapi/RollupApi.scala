package rollupsapi

import transformsapi.Response
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpMethods, HttpRequest}
import io.circe.generic.auto._
import io.circe.syntax._

case class RollupApi(){

  def putRollUpJob(entity: RollupConfig, id: String, port: String): Unit = {
    val json = entity
      .asJson
      .dropNullValues
      .mapObject(_.mapValues(_.dropNullValues))
      .mapObject(_.mapValues(_.mapObject(_.mapValues(_.dropNullValues))))
      .noSpaces
    val request = HttpRequest(
      method = HttpMethods.PUT,
      uri = s"$port/_rollup/job/$id",
      entity = HttpEntity(ContentTypes.`application/json`, json)
    )
    Response().getResponseFromRequest(request)
  }

  def startRollupJob(id: String, port: String) {
    val request = HttpRequest(
      method = HttpMethods.POST,
      uri = s"$port/_rollup/job/$id/_start"
    )
    Response().getResponseFromRequest(request)
  }

  def stopRollupJob(id: String, port: String) {
    val request = HttpRequest(
      method = HttpMethods.POST,
      uri = s"$port/_rollup/job/$id/_stop"
    )
    Response().getResponseFromRequest(request)
  }

  // to delete rollup have to delete index by JobId & rollup_index
  def deleteRollupJob(id: String, port: String) {
    val request = HttpRequest(
      method = HttpMethods.DELETE,
      uri = s"$port/_rollup/job/$id"
    )
    Response().getResponseFromRequest(request)
  }

  def getRollupJobDetails(id: String, port: String) {
    val request = HttpRequest(
      method = HttpMethods.GET,
      uri = s"$port/_rollup/job/$id?pretty"
    )
    Response().getResponseFromRequest(request)
  }

  def getRollupCapabilities(indexPattern: String, port: String) {
    val request = HttpRequest(
      method = HttpMethods.GET,
      uri = s"$port/_rollup/data/$indexPattern?pretty"
    )
    Response().getResponseFromRequest(request)
  }

  def getRollupIndexCapabilities(index_name: String, port: String) {
    val request = HttpRequest(
      method = HttpMethods.GET,
      uri = s"$port/$index_name/_rollup/data?pretty"
    )
    Response().getResponseFromRequest(request)
  }

//  def searchRollupData(index: String, query: String) {
//    val request = HttpRequest(
//      method = HttpMethods.GET,
//      uri = s"$port/$index/_rollup_search?pretty=true",
//      entity = HttpEntity(ContentTypes.`application/json`, query)
//    )
//    Response().getResponseFromRequest(request)
//  }
}
