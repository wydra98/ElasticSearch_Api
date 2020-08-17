package Test

import Test.TransformApi.CreateTransforms
import Test.TransformApi.CreateTransforms.PivotParts
import Test.TransformApi.CreateTransforms.PivotParts.Aggregations
import Test.TransformApi.CreateTransforms.SourceParts.Source

object Main extends App {

  //Elasticsearch().getAllDocumentsFromIndex("kibana_sample_data_flights")

 // Transform().createTransform("transform1")
  //Transform().startTransform("transform1")
  val firstTransform = CreateTransforms.CreateTransform(
    "hejo",
    Source("fajny source"),
    CreateTransforms.Dest("działa jak natura chciała",Some("dołem jakiegosik pipelina")),
    PivotParts.Pivot(Aggregations("z jakiegoś indexu", "do jakiegoś"),PivotParts.GroupBy("z jakiegoś indexu","do jakiegoś indexu")),
    Some("czy teraz jest ok?")
    //Some("5m"))
  )

  firstTransform.createTransformFromJson()

 //Transform().postTransform()
 // Transform().putTransform("transform1")
  //Transform().startTransform("transform1")
 // Transform().stopTransform("transform1")
 // Transform().deleteTransform("transform1")
 // Transform().getTransform("transform1")
 // Transform().getTransformStatistics("transform1")

//  Rollup().putRollUpJob("rollup1")
//  Rollup().startRollupJob("rollup1")
//  Rollup().stopRollupJob("rollup1")
//  Rollup().deleteRollupJob("rollup1")
//  Rollup().getRollupJobDetails("rollup1")
//  Rollup().getRollupCapabilities("_all")
//  Rollup().getRollupIndexCapabilities("sensor_rollup")
//  Rollup().getFromRollup("sensor_rollup", RollupRequestBody().rollupSearch1)

}