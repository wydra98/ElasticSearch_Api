package transformsApi.transformsProperties

import transformsApi.transformsProperties.pivotProperties.{Aggregations, GroupBy}

case class Pivot(group_by: Map[String, GroupBy],
                 aggregations: Map[String,Aggregations]){

}
