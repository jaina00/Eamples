import org.joda.time.Seconds

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.util.Try

/**
  * Created by abhishek on 25/02/17.
  */
object OnTrack extends App {

  //  def F1(in: Int) = Future {
  //    in
  //  }
  //
  //  def F2(in: Int) = Future {
  //    in
  //  }
  //
  //  def F3(in: Int) = Future {
  //    in
  //  }
  //
  //  def F4(in: Int) = Future {
  //    in
  //  }
  //
  //  val t = for {
  //    x <- F1(1)
  //    y <- F2(2)
  //    z <- F3(3)
  //    _ <- F4(4)
  //  } yield ()
  //
  //  //      val list = Seq(F1(1), F2(2), F3(3), F4(4))
  //  //
  //  //      val t = Future.sequence(list)
  //
  //  Await.ready(t, 5 seconds)
  //

  def incrementSeq(in: Seq[Int]) = {
    Try(in.mkString("").toInt).toOption
      .map(_ + 1)
      .map(_.toString.split("").toSeq.map(_.toInt))
  }

  println(incrementSeq(Seq.empty))
  println(incrementSeq(Seq(0)))
  println(incrementSeq(Seq(1, 2, 3)))
  println(incrementSeq(Seq(9, 9, 9)))


  val myStringOption = Some("test")

  myStringOption.map { x => println(x)
  }

  myStringOption match {
    case Some(text) => println(text)
  }

}
