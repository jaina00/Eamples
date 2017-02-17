
//Monads are used to provide a standard interface for
// composing and sequencing operations on some contained values

trait Option[A] {
  def map[B](f: A => B): Option[B]

  def flatMap[B](f: A => Option[B]): Option[B]
}

case class Some[A](a: A) extends Option[A] {
  def map[B](f: A => B): Option[B] = new Some(f(a))

  def flatMap[B](f: A => Option[B]): Option[B] = f(a)
}

case class None[A]() extends Option[A] {
  def map[B](f: A => B): Option[B] = new None

  def flatMap[B](f: A => Option[B]): Option[B] = new None
}

object OptionMonads extends App {

}


