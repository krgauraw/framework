package demo.actor.scala.one

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

/**
  * Simple Actor Which will print a String message.
  */
class HelloActor extends Actor { // Extending actor trait
    //  Receiving message
    def receive = {
        case msg: String => println("Message Received : " + msg + ", Sender Name : " + self.path.name)
        case _ => println("Unknown message") // Default case
    }
}

/**
  * Driver Program to Invoke An Actor
  *
  * @author Kumar Gauraw
  */
object HelloActorDemo {

    def main(args: Array[String]): Unit = {
        // Creating ActorSystem
        val actorSystem = ActorSystem("ActorSystem")
        //Creating actor
        val actor = actorSystem.actorOf(Props[HelloActor], "HelloActor")

        // Sending messages by using ! // ! or tell both can be used for tell
        actor ! "Hello Akka"
        actor ! 100.52
        //Sending messages by using tell
        actor.tell("Hello Actor!", ActorRef.noSender)

        //shutdown actor system
        actorSystem.terminate();
    }
}
