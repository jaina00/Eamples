/**
  * Created by abhishek on 22/02/17.
  */

import cats._
import cats.instances.all._
import cats.syntax.order._

object CatsExample extends App {

  val len: (String => Int) = _.length

  val lst = List("scala", "html")

  val t = lst.map(len)

  val r = Functor[List].map(List("scala", "html"))(len)

  println(r)
  println(t)
  println(1 > 2.0)
  println(1.0 compare 2.0)
  println(1.0 max 2.0 max 0.5)
}
