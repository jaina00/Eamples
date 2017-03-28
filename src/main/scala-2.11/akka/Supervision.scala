package akka

import akka.actor.SupervisorStrategy.{Escalate, Restart, Resume, Stop}
import akka.actor.{Actor, ActorRef, ActorSystem, OneForOneStrategy, Props}
import scala.concurrent.duration._

/**
  * Created by abhishek on 21/03/17.
  */

class ChildActor extends Actor {

  import ChildActor._

  override def preStart() = {
    println("ChildActor preStart hook....")
  }

  override def preRestart(reason: Throwable, message: Option[Any]) = {
    println("ChildActor preRestart hook...")
    super.preRestart(reason, message)
  }

  override def postRestart(reason: Throwable) = {
    println("ChildActor postRestart hook...")
    super.postRestart(reason)
  }

  override def postStop() = {
    println("ChildActor postStop...")
  }

  def receive = {
    case "Resume" =>
      throw ResumeException
    case "Stop" =>
      throw StopException
    case "Restart" =>
      throw RestartException
    case _ =>
      throw new Exception
  }
}

object ChildActor {

  case object ResumeException extends Exception

  case object StopException extends Exception

  case object RestartException extends Exception

}

class ParentSupervisor extends Actor {

  import ChildActor._

  var childRef: ActorRef = _

  override val supervisorStrategy =
    OneForOneStrategy(maxNrOfRetries = 10, withinTimeRange = 1 second) {
      case ResumeException => Resume
      case RestartException => Restart
      case StopException => Stop
      case _: Exception => Escalate
    }

  override def preStart() = {
    // Create Child Actor
    childRef = context.actorOf(Props[ChildActor], "ChildActor")
    Thread.sleep(100)
  }

  def receive = {
    case msg: String =>
      println(s"parent received ${msg}")
      childRef forward msg
      self ! 0
      Thread.sleep(100)
    case 0 =>
      println(s"0 is called")
  }
}

object SupervisionExample extends App {

  // Create the 'supervision' actor system
  val system = ActorSystem("supervision")

  // Create Parent Supervisor Actor
  val parent = system.actorOf(Props[ParentSupervisor], "parent")

  //  parent ! "Resume"
  //  Thread.sleep(1000)
  //  println()

  parent ! "Restart"
  Thread.sleep(1000)
  println()

  //  parent ! "Stop"
  //  Thread.sleep(1000)
  //  println()

  system.shutdown()


}