package akka.persistent

import akka.actor.ActorLogging
import akka.persistence.{PersistentActor, SnapshotOffer}

/**
  * Created by abhishek on 28/03/17.
  */

object Counter {

  sealed trait Operation {
    val count: Int
  }

  case class Increment(override val count: Int) extends Operation

  case class Decrement(override val count: Int) extends Operation

  case class Cmd(op: Operation)

  case class Evt(op: Operation)

  case class State(count: Int)

}


class Counter extends PersistentActor with ActorLogging {

  import Counter._

  println("Starting ........................")

  var state: State = State(count = 0)

  def updateState(evt: Evt): Unit = evt match {
    case Evt(Increment(count)) =>
      state = State(count = state.count + count)
      takeSnapshot
    case Evt(Decrement(count)) =>
      state = State(count = state.count - count)
      takeSnapshot
  }

  override def persistenceId: String = "counter-example" //this

  // Persistent receive on recovery mood. Persistent actor always start with recovering mood
  override def receiveRecover: Receive = {
    case evt: Evt =>
      println(s"Counter receive ${evt} on recovering mood")
      updateState(evt)
    case SnapshotOffer(_, snapshot: State) =>
      state = snapshot
  }

  // Persistent receive on normal mood
  override def receiveCommand: Receive = {
    case cmd@Cmd(op) =>
      println("command---" + op)
      persist(Evt(op)) { evt =>
        updateState(evt)
      }
    case "print" =>
      println(s"the state is ${state.count}")
  }


  def takeSnapshot = {
    if (state.count % 5 == 0) {
      saveSnapshot(state)
    }
  }

}
