import com.sksamuel.elastic4s.requests.searches.queries.BoolQuery
import com.sksamuel.elastic4s.requests.searches.queries.term.TermQuery
import transformsapi.transformsproperties.pivotproperties.{Aggregations, BucketScript, GroupBy, Sum, Terms, ValueCount}
import transformsapi.transformsproperties.syncproperties.Time
import transformsapi.transformsproperties.{Dest, Pivot, Source, Sync}
import transformsapi.{TransformApi, TransformConfig, TransformUpdateConfig}
//import io.circe.generic.auto._
//import io.circe.syntax._

object MainTransform extends App {

  val port = "http://localhost:9200"
  val id = "transform1"

  val query = BoolQuery(
    filters = List(TermQuery("Canceled", false))
  )

  val transformConfig = TransformConfig("transform1",
    source = new Source("kibana_sample_data_flights",query),
    dest = Dest("transform_1"),
    pivot =  Pivot(
      Map("carrier" -> GroupBy(terms = Some(Terms("OriginCityName")))),
      Map("flights_count" -> Aggregations(value_count = Some(ValueCount("FlightNum"))),
        "delay_mins_total" -> Aggregations(sum = Some(Sum("FlightDelayMin"))),
        "flight_mins_total" -> Aggregations(sum = Some(Sum("FlightTimeMin"))),
        "delay_time_percentage:" -> Aggregations(bucket_script = Some(BucketScript(
          Map("delay_time" -> "delay_mins_total.value",
          "flight_time" -> "flight_mins_total.value"),
          "(params.delay_time / params.flight_time) * 100")))
        )
    ),
    frequency = Some("5m"),
    sync = Some(Sync(Time("timestamp", Some("60s"))))
  )

  val transformUpdateConfig = TransformUpdateConfig(
    source = new Source("kibana_sample_data_flights",query),
    dest = Dest("transform_1"),
    description = Some("Transform Test"),
    frequency = Some("15m"),
    sync = Some(Sync(Time("timestamp", Some("60s"))))
  )

  /** 1. Tworzenie transformacji - tylko do podglądu */
  TransformApi().postTransform(transformConfig,port)

  /** 2. Tworzenie transformacji */
//  TransformApi().putTransform(transformConfig,id,port)

  /** 2. Update transformacji */
//  TransformApi().updateTransform(transformUpdateConfig,id,port)

  /** 3. Startowanie transformacji */
//  TransformApi().startTransform(id,port)

  /** 4. Zatrzymanie transformacji */
//  TransformApi().stopTransform(id,port)

  /** 5. Usunięcie transformacji */
//  TransformApi().deleteTransform(id,port)

  /** 6. Informacje o transformacji */
//  TransformApi().getTransform(id,port)

  /** 7. Statystyki o transformacji */
//  TransformApi().getTransformStatistics(id,port)

}
