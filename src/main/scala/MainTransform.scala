import TransformsApi.Dest.Dest
import TransformsApi.Pivot.Aggregations.Aggregations
import TransformsApi.Pivot.Aggregations.Parts.{Sum, ValueCount}
import TransformsApi.Pivot.GroupBy.GroupBy
import TransformsApi.Pivot.GroupBy.Parts.Terms
import TransformsApi.Pivot.Pivot
import TransformsApi.Source.Source
import TransformsApi.Sync.Sync
import TransformsApi.Sync.Time.Time
import TransformsApi.TransformConfig

object MainTransform extends App {

  val port = "http://localhost:9200"
  val id = "transform1"

  val transformConfig = TransformConfig(
    source = Source("kibana_sample_data_flights",Some(exampleQuery)),
    dest = Dest("transform_1"),
    pivot =  Pivot(
      Map("carrier" -> GroupBy(Some(Terms("OriginCityName")))),
      Map("flights_count" -> Aggregations(value_count = Some(ValueCount("FlightNum"))),
        "delay_mins_total" -> Aggregations(sum = Some(Sum("FlightDelayMin"))),
        "flight_mins_total" -> Aggregations(sum = Some(Sum("FlightTimeMin")))
      )),
    frequency = Some("5m"),
    description = Some("TransformTest"),
    sync = (Some(Sync(Time("timestamp", Some("60s")))))
  )

  val exampleQuery: String = {
    """{
         "bool": {
          "filter": [
              { "term": { "Cancelled": false } }
          ]
       }
      }"""
  }

  /** 1. Tworzenie transformacji - tylko do podglądu */
  //TransformApi().postTransform(transformConfig,id,port)

  /** 2. Tworzenie transformacji */
  //TransformApi().putTransform(transformConfig,id,port)

  /** 3. Startowanie transformacji */
  //TransformApi().startTransform(id,port)

  /** 4. Zatrzymanie transformacji */
  //TransformApi().stopTransform(id,port)

  /** 5. Usunięcie transformacji */
  //TransformApi().deleteTransform(id,port)

  /** 6. Informacje o transformacji */
  //TransformApi().getTransform(id,port)

  /** 7. Statystyki o transformacji */
  //TransformApi().getTransformStatistics(id,port)
}
