package transformsapi.transformsproperties.pivotproperties

case class Aggregations(avg: Option[Avg] = None,
                        sum: Option[Sum] = None,
                        min: Option[Min] = None,
                        max: Option[Max] = None,
                        value_count: Option[ValueCount] = None,
                        cardinality: Option[Cardinality] = None,
                        percentiles: Option[Percentiles] = None,
                        bucket_script: Option[BucketScript] = None,
                        bucket_selector: Option[BucketSelector] = None,
                        scripted_metric: Option[ScriptedMetric] = None,
                        bounds: Option[GeoBounds] = None)
