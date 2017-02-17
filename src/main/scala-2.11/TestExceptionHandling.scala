/**
  * Created by abhishek on 10/11/16.
  */

import akka.actor.ActorSystem
import akka.stream.{ActorMaterializer, ActorMaterializerSettings, Supervision}
import akka.stream.scaladsl.Source
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

object TestExceptionHandling {

  val decider: Supervision.Decider = {
    case _: RuntimeException => {
      Supervision.Stop
      throw new RuntimeException("Please, don't swallow me!")
    }
    case _ => Supervision.Stop
  }

  def main(args: Array[String]): Unit = {
    implicit val actorSystem = ActorSystem()
    val materializerSettings = ActorMaterializerSettings(actorSystem).withSupervisionStrategy(decider)
    implicit val materializer = ActorMaterializer(materializerSettings)(actorSystem)


    Source(List(1, 2, 3, 4, 5, 6, 7, 8, 9)).map { i =>
      if (i == 4) {
        throw new RuntimeException("Please, don't swallow me!")
      } else {
        i
      }
    }.runForeach { i =>
      println(s"Received $i")
    }.onComplete {
      case Success(_) =>
        println("Done")
      case Failure(e) =>
    }

    println("1111111111")
  }
}