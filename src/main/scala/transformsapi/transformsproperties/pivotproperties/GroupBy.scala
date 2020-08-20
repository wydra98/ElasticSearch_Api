package transformsapi.transformsproperties.pivotproperties


case class GroupBy (terms: Option[Terms] = None,
                    date_histogram: Option[DateHistogram] = None,
                    histogram: Option[Histogram] = None)