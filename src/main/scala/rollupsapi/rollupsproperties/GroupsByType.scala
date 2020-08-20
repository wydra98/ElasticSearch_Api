package rollupsapi.rollupsproperties

sealed trait GroupsByType

case class DateHistogram(field: String,
                         fixed_interval: String,
                         delay: Option[String] = None,
                         time_zone: Option[String] = None) extends GroupsByType

case class Histogram(fields: List[String],
                     interval: Int) extends GroupsByType

case class Terms(fields: List[String]) extends GroupsByType