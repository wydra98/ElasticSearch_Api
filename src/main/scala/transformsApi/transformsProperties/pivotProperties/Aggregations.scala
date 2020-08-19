package transformsApi.transformsProperties.pivotProperties

case class Aggregations(avg: Option[Avg] = None,
                        sum: Option[Sum] = None,
                        min: Option[Min] = None,
                        max: Option[Max] = None,
                        value_count: Option[ValueCount] = None)
