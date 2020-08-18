import TransformsApi.Dest.Dest
import TransformsApi.Pivot.Aggregations.Aggregations
import TransformsApi.Pivot.Aggregations.Parts.{Sum, ValueCount}
import TransformsApi.Pivot.GroupBy.GroupBy
import TransformsApi.Pivot.GroupBy.Parts.Terms
import TransformsApi.Pivot.Pivot
import TransformsApi.Source.Source
import TransformsApi.Sync.Sync
import TransformsApi.Sync.Time.Time
import TransformsApi.Transform
import TransformsApi.TransformConfigs.TransformConfig

object MainTransform extends App {

  val transformConfig = TransformConfig(
    id = "transform1",
    dest = Dest("transform_1"),
    frequency = "5m",
  )
    .withId("transform1")
    .withSource(Source("kibana_sample_data_flights"))
    .withDest(Dest("transform_1"))
    .withPivot(
      Pivot(
      Map("carrier" -> GroupBy(Some(Terms("OriginCityName")))),
      Map("flights_count" -> Aggregations(value_count = Some(ValueCount("FlightNum"))),
        "delay_mins_total" -> Aggregations(sum = Some(Sum("FlightDelayMin"))),
        "flight_mins_total" -> Aggregations(sum = Some(Sum("FlightTimeMin")))
      )))
    .withFrequency(Some("5m"))
    .withDescription(Some("TransformTest"))
    .withSync(Some(Sync(Time("timestamp", Some("60s")))))
    .build


    //  Transform().postTransform(transformConfig)
      Transform().putTransform(transformConfig)
  //  Transform().startTransform("transform1")
  //  Transform().stopTransform("transform1")
  //  Transform().deleteTransform("transform1")
  //  Transform().getTransform("transform1")
  //  Transform().getTransformStatistics("transform1")
}
