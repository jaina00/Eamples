package akka.cluster

import akka.actor.{Actor, RootActorPath}
import akka.cluster.ClusterEvent.MemberUp


/**
  * Created by abhishek on 23/03/17.
  */
class Backend extends Actor {

  val cluster = Cluster(context.system)

  // subscribe to cluster changes, MemberUp
  // re-subscribe when restart
  override def preStart(): Unit = {
    cluster.subscribe(self, classOf[MemberUp])
  }

  override def postStop(): Unit = {
    cluster.unsubscribe(self)
  }

  def receive = {
    case Add(num1, num2) =>
      println(s"I'm a backend with path: ${self} and I received add operation.")
    case MemberUp(member) =>
      if(member.hasRole("frontend")){
        context.actorSelection(RootActorPath(member.address) / "user" / "frontend") !
          BackendRegistration
      }
  }

}
