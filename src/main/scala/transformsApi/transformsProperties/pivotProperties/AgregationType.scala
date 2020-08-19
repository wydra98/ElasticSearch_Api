package transformsApi.transformsProperties.pivotProperties

sealed trait AgregationType


case class Avg(field: String) extends AgregationType

case class Max(field: String) extends AgregationType

case class Min(field: String) extends AgregationType

case class Sum(field: String) extends AgregationType

case class ValueCount(field: String) extends AgregationType