package Test.TransformApi.CreateTransforms.PivotParts

case class Aggregations(srcIndex: String,
                        dstIndex: String,
                        avg: Option[String] = None,
                        filter: Option[String] = None,
                        max: Option[String] = None,
                        min: Option[String] = None,
                        sum: Option[String] = None)
