package TransformsApi.Pivot

import TransformsApi.Pivot.Aggregations.Aggregations
import TransformsApi.Pivot.GroupBy.GroupBy

case class Pivot(group_by: Map[String, GroupBy],
                 aggregations: Map[String,Aggregations]){

}
