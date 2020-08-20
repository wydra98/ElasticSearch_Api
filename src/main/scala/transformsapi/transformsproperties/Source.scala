package transformsapi.transformsproperties

import com.sksamuel.elastic4s.json.XContentBuilder
import com.sksamuel.elastic4s.requests.searches.queries.{Query, QueryBuilderFn}
import io.circe.Json
import io.circe.parser._

case class Source(index: String,
                  var query: Option[Json] = None){

  def this(index: String, query: Query){
    this(index, Some(parse(QueryBuilderFn.apply(query).string()).getOrElse(Json.Null)))
  }

}