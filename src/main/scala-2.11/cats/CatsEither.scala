package cats1

import cats.data._
import cats.implicits._
import cats1.MyError.{BusinessError, BusinessError1, BusinessError2, BusinessError3}
import cats1.MyError2.{BusinessError4, BusinessError5, BusinessError6, BusinessErrorNoStack}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.control.NoStackTrace

/**
  * Created by abhishek on 06/04/17.
  */


object MyError {

  sealed trait BusinessError

  case class BusinessError1() extends BusinessError

  case class BusinessError2() extends BusinessError

  case class BusinessError3() extends BusinessError

}

object MyError2 {

  sealed trait BusinessErrorNoStack extends NoStackTrace

  case class BusinessError4() extends BusinessErrorNoStack with BusinessError

  case class BusinessError5() extends BusinessErrorNoStack

  case class BusinessError6() extends BusinessErrorNoStack

}

object CatsEither extends App {

  def f = Future {
    throw new Exception("there is an exception")
  }

  def et = EitherT[Future, BusinessError, Int](f.map(x => Right(x)).recover {
    case e =>
      Left(BusinessError1())
  })

  val res: Future[Int] = et.fold(
    (error: BusinessError) => error match {
      case BusinessError1() => 1
      case BusinessError2() => 2
      case BusinessError3() => 3
    },
    (str: Int) => str
  )
}

object CatsEither2 extends App {

  def f = Future {
    throw new BusinessError4
  }.recover {
    case e: BusinessError4 => 1
    case e: BusinessError5 => 2
    case e:BusinessErrorNoStack => 3
  }
}


