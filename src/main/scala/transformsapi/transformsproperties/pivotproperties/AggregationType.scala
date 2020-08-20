package transformsapi.transformsproperties.pivotproperties

sealed trait AggregationType


case class Avg(field: String) extends AggregationType

case class Max(field: String) extends AggregationType

case class Min(field: String) extends AggregationType

case class Sum(field: String) extends AggregationType

case class ValueCount(field: String) extends AggregationType

case class Cardinality(field: String) extends AggregationType

case class Percentiles(field: String) extends AggregationType

case class BucketScript(buckets_path: Map[String, String],
                        script: String,
                        gap_policy: Option[String] = None,
                        format: Option[String] = None) extends AggregationType

case class BucketSelector(buckets_path: Map[String, String],
                        script: String,
                        gap_policy: Option[String] = None) extends AggregationType

case class ScriptedMetric(init_script: String,
                          map_script: String,
                          combine_script: String,
                          reduce_script: String) extends AggregationType

case class GeoBounds(top_left: Map[String, Double],
                     bottom_right: Map[String, Double]) extends AggregationType



//  YES    Average
//  YES    Bucket script
//  YES    Bucket selector
//  YES    Cardinality
//      Filter
//  YES    Geo bounds
//      Geo centroid
//  YES    Max
//  YES    Min
//  YES    Percentiles
//      Rare Terms
//  YES   Scripted metric
//  YES    Sum
//      Terms
//  YES    Value count
//      Weighted average

