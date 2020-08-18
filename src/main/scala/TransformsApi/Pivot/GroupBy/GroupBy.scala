package TransformsApi.Pivot.GroupBy

import TransformsApi.Pivot.GroupBy.Parts.{DateHistogram, Histogram, Terms}

case class GroupBy (terms: Option[Terms] = None,
                    date_histogram: Option[DateHistogram] = None,
                    histogram: Option[Histogram] = None){

}
