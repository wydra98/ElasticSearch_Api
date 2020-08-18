
import RollupApi.Groups.DateHistogram.DateHistogram
import RollupApi.Groups.Groups
import RollupApi.Groups.Terms.Terms
import RollupApi.Metrics.Metrics
import RollupApi.RollupJobConfig


object MainRollup extends App {

  val rollupJobConfig = RollupJobConfig("kibana_sample_data_*", "rollup1_index", "*/30 * * * * ?", 1000L,
    Groups(DateHistogram("timestamp", "60m"),
      terms = Some(Terms(List("DistanceKilometers", "AvgTicketPrice")))),
    Some(List(Metrics("DistanceKilometers", List("min", "max", "sum")),
      Metrics("AvgTicketPrice", List("avg"))))
  )

//  val json = rollupJobConfig
//    .asJson
//    .dropNullValues
//    .mapObject(_.mapValues(_.dropNullValues))
//    .mapObject(_.mapValues(_.mapObject(_.mapValues(_.dropNullValues))))
//  println(json)


//    Rollup().putRollUpJob("rollup1", rollupJobConfig)
//    Rollup().startRollupJob("rollup1")
//    Rollup().stopRollupJob("rollup1")
//    Rollup().deleteRollupJob("rollup1")
//    Elasticsearch().deleteIndex("rollup1_index")
//    Rollup().getRollupJobDetails("rollup1")
//    Rollup().getRollupCapabilities("_all")
//    Rollup().getRollupIndexCapabilities("rollup1_index")
//    Rollup().searchRollupData("rollup1_index", RollupRequestBody().rollupSearch1)

}
