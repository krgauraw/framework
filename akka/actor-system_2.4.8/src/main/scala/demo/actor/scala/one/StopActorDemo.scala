package demo.actor.scala.one

import akka.actor.{Actor, ActorSystem, Props}

/**
  * Simple Actor, Which Print the message.
  */
class Actor1 extends Actor {

    override def receive: Receive = {
        case msg: String => println("Message Received : " + msg + ", Sender Name : " + self.path.name)
        case _ => println("Unknown message") // Default case
    }

    @throws[Exception]
    override def postStop(): Unit = {
        super.postStop()
        println(self.path.name + " actor stopped.")
    }


}

/**
  * Driver Program which shows that, how to stop an actor
  *
  * @author Kumar Gauraw
  */
object StopActorDemo {
    def main(args: Array[String]): Unit = {
        //Creating Actor System
        val system = ActorSystem.create("ActorSystem")
        // Creating Actor Instance
        val actor1 = system.actorOf(Props.create(classOf[Actor1]), "Actor1")
        //Sending a message to the actor
        actor1.tell("Hello Actor1", actor1)

        // stop actor
        system.stop(actor1)
        // shutdown actor system
        system.terminate
    }
}
