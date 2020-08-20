package transformsapi.transformsproperties.pivotproperties

sealed trait GroupByType

case class DateHistogram(field: String,
                         fixed_interval: String,
                         delay: Option[String] = None,
                         time_zone: Option[String] = None) extends GroupByType

case class Histogram(fields: String, interval: Int) extends GroupByType

case class Terms(field: String) extends GroupByType
