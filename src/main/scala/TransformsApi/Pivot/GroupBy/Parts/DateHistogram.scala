package TransformsApi.Pivot.GroupBy.Parts

case class DateHistogram(field: String,
                         fixed_interval: String,
                         delay: Option[String] = None,
                         time_zone: Option[String] = None) {

                         }
