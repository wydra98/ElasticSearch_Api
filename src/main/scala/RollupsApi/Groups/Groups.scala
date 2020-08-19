package RollupsApi.Groups

import RollupsApi.Groups.DateHistogram.DateHistogram
import RollupsApi.Groups.Histogram.Histogram
import RollupsApi.Groups.Terms.Terms

case class Groups(date_histogram: DateHistogram,
                  histogram: Option[Histogram] = None,
                  terms: Option[Terms] = None){

}
