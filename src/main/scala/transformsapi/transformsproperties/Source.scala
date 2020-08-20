package transformsapi.transformsproperties

case class Source(index: String,
                  var query: Option[String] = None)