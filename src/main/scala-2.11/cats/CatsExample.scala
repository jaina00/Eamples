package cats

/**
  * Created by abhishek on 22/02/17.
  */

import cats.data.Xor._
import cats.data._

final class User private(val name: String, val age: Int, val email: String)

object User extends App{
  def isValidEmail(email : String): UserError Xor String =
    if ("""^[-a-z0-9!#$%&'*+/=?^_`{|}~]+(\.[-a-z0-9!#$%&'*+/=?^_`{|}~]+)*@([a-z0-9]([-a-z0-9]{0,61}[a-z0-9])?\.)*(aero|arpa|asia|biz|cat|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|net|org|pro|tel|travel|[a-z][a-z])$"""
      .r.findFirstIn(email) == None) left(InvalidEmail(email)) else right(email)
  def isValidAge(age: Int): UserError Xor Int = if (age >= 0) right(age) else left(InvalidAge(age))
  def isValidName(name: String): UserError Xor String = if (name.length > 0) right(name) else left(EmptyName)

  def create(name: String, age: Int, email: String): UserError Xor User =
    for {
      vname <- isValidName(name)
      vage <- isValidAge(age)
      vemail <- isValidEmail(email)
    } yield (new User(vname,vage,vemail))

  println(create("asdfasdf",-1,"") map {_.name})
  println(create("Li Ka-shing",87,"nospam@lksf.org") map {_.name})
}


sealed abstract class UserError
case object EmptyName extends UserError
case class InvalidEmail(s: String) extends UserError
case class InvalidAge(age: Int) extends UserError

//object CatsExample extends App {
//
//  val len: (String => Int) = _.length
//
//  val lst = List("scala", "html")
//
//  val t = lst.map(len)
//
//  val r = Functor[List].map(List("scala", "html"))(len)
//
//  println(r)
//  println(t)
//  println(1 > 2.0)
//  println(1.0 compare 2.0)
//  println(1.0 max 2.0 max 0.5)
//}

