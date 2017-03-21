//import cats.instances.unit
//
//import scala.collection.GenTraversableOnce
//
///**
//  * Created by abhishek on 14/03/17.
//  */
//object MonadsExample extends App {
//
//  //  val t = List(1, 3, 3)
//  //  println(t.map(x => identity(x)))
//  //  println(t.flatMap(x => List(x)))
//
//  trait MonadTrait[T[_]] {
//    def map[A,B](f: A => B): MonadTrait[B]
//
//    def flatMap[A,B](f: A => MonadTrait[B]): MonadTrait[B]
//  }
//
//  object ListWrapperAsFunctor extends MonadTrait[]{
//
//  }
//
//  case class ListWrapper(list: List[Int]) {
//    // just wrap
//    def map[B](f: Int => B): List[B] = list.map(f)
//
//    // just wrap
//    def flatMap[B](f: Int => List[B]): List[B] = list.flatMap(f)
//  }
//
//
//  val l1 = List(1, 2, 3, 4)
//  val l2 = List(5, 6, 7, 8)
//  val result1 = for {
//    x <- l1
//    y <- l2
//  } yield x * y
//  // same as
//  // val result  = l1.flatMap(i => l2.map(_ * i))
//  System.out.println(s"The result 1 is: ${result1}")
//
//  val wrapper1 = ListWrapper(List(1, 2, 3, 4))
//  val wrapper2 = ListWrapper(List(5, 6, 7, 8))
//  val result2 = for {
//    x <- wrapper1
//    y <- wrapper2
//  } yield x * y
//  System.out.println(s"The result 2 is: ${result2}")
//
//}
