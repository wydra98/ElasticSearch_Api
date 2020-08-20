package transformsapi.transformsproperties

import transformsapi.transformsproperties.pivotproperties.{Aggregations, GroupBy}

case class Pivot(group_by: Map[String, GroupBy],
                 aggregations: Map[String,Aggregations]){

}
