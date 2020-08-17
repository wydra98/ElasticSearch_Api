package Test.TransformApi.CreateTransforms.PivotParts

import Test.TransformApi.CreateTransforms.PivotParts.GroupByProperties.{DateHistogram, Histogram, Terms}

case class GroupBy(terms: Option[Terms],
                   histogram: Option[Histogram],
                   dateHistogram: Option[DateHistogram]){

}
