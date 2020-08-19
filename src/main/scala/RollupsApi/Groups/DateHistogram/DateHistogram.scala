package RollupsApi.Groups.DateHistogram

case class DateHistogram(field: String,
                         fixed_interval: String,
                         delay: Option[String] = None,
                         time_zone: Option[String] = None){

}
