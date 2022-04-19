package demo.actor.scala.one

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

/**
  * A Simple Actor
  */
class PropsDemoActor extends Actor {
    //  Receiving message
    def receive = {
        case msg: String => println("Message Received : " + msg + ", Sender Name : " + self.path.name)
        case _ => println("Unknown message") // Default case
    }
}

/**
  * This Program Shows that How to Create an actor using Props
  *
  * @author Kumar Gauraw
  */
object CreateActorUsingProps {
    def main(args: Array[String]): Unit = {
        val actorSystem: ActorSystem = ActorSystem("ActorSystem")
        // create props instance
        val props = Props[PropsDemoActor];
        // create actors using props
        val actor1 = actorSystem.actorOf(props);  // passed pops reference of PropsDemoActor
        val actor2 = actorSystem.actorOf(Props[PropsDemoActor],"Actor2")  // Provided pops and explicitly giving name to the actor
        val actor3 = actorSystem.actorOf(Props(classOf[PropsDemoActor]),"Actor3")  // Provided actor class name by using classOf
        val actor4 = actorSystem.actorOf(Props[PropsDemoActor], name = "Actor4")  // Name Variable Used Here
        val actor5 = actorSystem.actorOf(Props(new PropsDemoActor()), name = "Actor5") // This approach is not recommended
        actor1 ! "Hello"
        actor2 ! "Hello"
        actor3 ! "Hello"
        actor4 ! "Hello"
        actor5 ! "Hello"

        //terminate actor system
        actorSystem.terminate()
    }
}
