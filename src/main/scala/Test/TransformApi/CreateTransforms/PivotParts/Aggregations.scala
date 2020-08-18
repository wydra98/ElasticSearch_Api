package Test.TransformApi.CreateTransforms.PivotParts

case class Aggregations(srcIndex: String,
                        dstIndex: String,
                        parameters: String)
//                        avg: Option[String] = None,
//                        filter: Option[String] = None,
//                        max: Option[String] = None,
//                        min: Option[String] = None,
//                        sum: Option[String] = None)
                          {

  def parseToJson(): String = {
    var jsonBody: String = ""
    jsonBody += "{"
    jsonBody += "\"aggregations\":{"
    parameters match {
      case "avg" => jsonBody += s"${'"'}${srcIndex}${'"'}:{${'"'}avg${'"'}:{${'"'}field${'"'}:${'"'}${dstIndex}${'"'}}}"
      case "filter" => jsonBody += s"${'"'}${srcIndex}${'"'}:{${'"'}filter${'"'}:{${'"'}field${'"'}:${'"'}${dstIndex}${'"'}}}"
      case "max" => jsonBody += s"${'"'}${srcIndex}${'"'}:{${'"'}max${'"'}:{${'"'}field${'"'}:${'"'}${dstIndex}${'"'}}}"
      case "min" => jsonBody += s"${'"'}${srcIndex}${'"'}:{${'"'}min${'"'}:{${'"'}field${'"'}:${'"'}${dstIndex}${'"'}}}"
      case "sum" => jsonBody += s"${'"'}${srcIndex}${'"'}:{${'"'}sum${'"'}:{${'"'}field${'"'}:${'"'}${dstIndex}${'"'}}}"
      case _ => println("jest supper miło i przujemnie")
    }
    jsonBody += "}"
    jsonBody
  }
}