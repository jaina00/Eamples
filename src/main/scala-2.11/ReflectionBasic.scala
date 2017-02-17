/**
  * Created by abhishek on 02/02/17.
  */

trait T[A] {
  val vT: A

  def mT = vT
}

class C(foo: Int) extends T[String] {
  val vT = "T"
  val vC = "C"

  def mC = vC

  class C2

}

object ReflectionBasic extends App {

}
