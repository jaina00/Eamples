package akka.cluster

/**
  * Created by abhishek on 23/03/17.
  */
object BeanCounter extends App{

  type Error = String

  sealed trait ParentType

  case class Child1() extends ParentType

  case class Child2() extends ParentType

  def count(items: List[ParentType]): Either[Error, Int] = {


    items match {
      case e: List[Child1] => Right(items.length)
      case e: List[Child2] =>
        Left("Can't count, won't count tomatoes. i only count beans")
      case _ => Left("I only count beans")
    }
  }

  println(count(List(Child1(),Child1(),Child1())))
  println(count(List(Child1(),Child1(),Child1())))

}