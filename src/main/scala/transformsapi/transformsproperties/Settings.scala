package transformsapi.transformsproperties

case class Settings(docs_per_second: Option[Float] = None,
                    max_page_search_size: Option[Long] = None)
