package Test

object mainTest extends App {

  Elasticsearch().getAllDocumentsFromIndex("kibana_sample_data_flights")

//  Transform().postTransform()
//  Transform().putTransform("transform1")
//  Transform().startTransform("transform1")
//  Transform().stopTransform("transform1")
//  Transform().deleteTransform("transform1")
//  Transform().getTransform("transform1")
//  Transform().getTransformStatistics("transform1")

//  Rollup().putRollUpJob("rollup1")
//  Rollup().startRollupJob("rollup1")
//  Rollup().stopRollupJob("rollup1")
//  Rollup().deleteRollupJob("rollup1")
//  Rollup().getRollupJobDetails("rollup1")
//  Rollup().getRollupCapabilities("_all")
//  Rollup().getRollupIndexCapabilities("sensor_rollup")
//  Rollup().searchRollupData("sensor_rollup", RollupRequestBody().rollupSearch1)

}