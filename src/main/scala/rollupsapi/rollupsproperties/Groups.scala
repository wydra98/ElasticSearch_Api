package rollupsapi.rollupsproperties

case class Groups(date_histogram: Option[DateHistogram] = None,
                  histogram: Option[Histogram] = None,
                  terms: Option[Terms] = None)
