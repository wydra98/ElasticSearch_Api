package Test.TransformApi.CreateTransforms.PivotParts

import Test.TransformApi.CreateTransforms.PivotParts.GroupByProperties.{Histogram, Terms}
import com.sksamuel.elastic4s.requests.searches.aggs.responses.bucket.DateHistogram

case class GroupBy(srcIndex: String,
                   dstindex: String,
                   terms: Option[Terms] = None,
                   histogram: Option[Histogram] = None,
                   dateHistogram: Option[DateHistogram] = None){

  def start = {}

}
