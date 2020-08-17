package Test

class TestCircle extends App {
  import io.circe.generic.auto._
  import io.circe.syntax._

  //sealed trait Foo
  case class Bar(xs: Vector[String])// extends Foo
  case class Qux(i: Int, d: Option[Double])// extends Foo

  val foo: Qux = Qux(13, Some(14.0))

  val json = foo.asJson.noSpaces
  println(json)


}
