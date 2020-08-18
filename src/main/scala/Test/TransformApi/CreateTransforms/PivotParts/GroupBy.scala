package Test.TransformApi.CreateTransforms.PivotParts

import Test.TransformApi.CreateTransforms.PivotParts.GroupByProperties.{DateHistogram, GroupByOption, Histogram, Terms}

case class GroupBy(srcIndex: String,
                   dstIndex: String,
                   option: GroupByOption)
    {

  def parseToJson(): String = {
    var jsonBody: String = ""
    jsonBody += "{"
    jsonBody += "\"group_by\":{"
    option match {
      case _: Option[Terms] => jsonBody += s"${'"'}${srcIndex}${'"'}:{${'"'}terms${'"'}:{${'"'}field${'"'}:${'"'}${dstIndex}${'"'}}}"
      case _: Option[Histogram] => jsonBody += s"${'"'}${srcIndex}${'"'}:{${'"'}histogram${'"'}:{${'"'}field${'"'}:${'"'}${dstIndex}${'"'}}}"
      case _: Option[DateHistogram] => jsonBody += s"${'"'}${srcIndex}${'"'}:{${'"'}date_histogram${'"'}:{${'"'}field${'"'}:${'"'}${dstIndex}${'"'}}}"
    }
    jsonBody += "}"
    jsonBody
  }
}
