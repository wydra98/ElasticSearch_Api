package RollupApi.Groups

import RollupApi.Groups.DateHistogram.DateHistogram
import RollupApi.Groups.Histogram.Histogram
import RollupApi.Groups.Terms.Terms

case class Groups(date_histogram: DateHistogram,
                  histogram: Option[Histogram] = None,
                  terms: Option[Terms] = None){

}
