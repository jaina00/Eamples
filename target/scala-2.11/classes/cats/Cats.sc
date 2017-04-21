import cats._
import cats.data._
import cats.implicits._
import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.Future


type EitherF[A, B] = EitherT[Future, A, B]
type Result[T] = EitherF[String, T]


def f1 = Future {
  throw new Exception("there is an exception")
}


def someEitherT: Result[Int] = EitherT {
  Future {
    1
  }.map(x => Right(x))
    .recover {
      case e =>
        Left("There is an error et1")
    }
}
println("222222222")