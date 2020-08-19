import transformsApi.transformsProperties.pivotProperties.{Aggregations, GroupBy, Sum, Terms, ValueCount}
import transformsApi.transformsProperties.syncProperties.Time
import transformsApi.transformsProperties.{Dest, Pivot, Source, Sync}
import transformsApi.{TransformApi, TransformConfig, TransformUpdateConfig}

object MainTransform extends App {

  val port = "http://localhost:9200"
  val id = "transform1"

  val exampleQuery: String = {
    """{
         "bool": {
          "filter": [
              { "term": { "Cancelled": false } }
          ]
       }
      }"""
  }

  val transformConfig = TransformConfig("transform1",
    source = Source("kibana_sample_data_flights",Some(exampleQuery)),
    dest = Dest("transform_1"),
    pivot =  Pivot(
      Map("carrier" -> GroupBy(terms = Some(Terms("OriginCityName")))),
      Map("flights_count" -> Aggregations(value_count = Some(ValueCount("FlightNum"))),
        "delay_mins_total" -> Aggregations(sum = Some(Sum("FlightDelayMin"))),
        "flight_mins_total" -> Aggregations(sum = Some(Sum("FlightTimeMin")))
      )),
    frequency = Some("5m"),
    sync = Some(Sync(Time("timestamp", Some("60s"))))
  )

  val transformUpdateConfig = TransformUpdateConfig(
    source = Source("kibana_sample_data_flights"/*, Some(exampleQuery)*/),
    dest = Dest("transform_1"),
    description = Some("Transform Test"),
    frequency = Some("15m"),
    sync = Some(Sync(Time("timestamp", Some("60s"))))
  )

  /** 1. Tworzenie transformacji - tylko do podglądu */
//  TransformApi().postTransform(transformConfig,port)

  /** 2. Tworzenie transformacji */
  TransformApi().putTransform(transformConfig,id,port)

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

  val temp = TransformApi
}
