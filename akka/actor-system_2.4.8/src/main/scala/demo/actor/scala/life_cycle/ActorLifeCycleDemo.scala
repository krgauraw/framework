package demo.actor.scala.life_cycle

import akka.actor.{Actor, ActorRef, ActorSystem, Props}


class DemoActor extends Actor {
    def receive = {
        case msg: String => println("Message Received by " + self.path.name + " : " + msg)
            // code to generate exception. so that preRestart() and postRestart() will be invoked for self healing process.
            10 / 0;
    }

    override def preStart() {
        super.preStart();
        println("preStart method is called! " + self.path.name + " is going to start.");
    }

    override def postStop() {
        super.postStop();
        println("postStop method is called! " + self.path.name + " is stopped.");
    }

    override def preRestart(reason: Throwable, message: Option[Any]) {
        super.preRestart(reason, message);
        println("preRestart method is called!");
        println("Reason: " + reason);
    }

    override def postRestart(reason: Throwable) {
        super.postRestart(reason);
        println("postRestart is called");
        println("Reason: " + reason);
    }
}

/**
  * Driver Program for Actor Life Cycle Demo
  *
  * @author Kumar Gauraw
  */
object ActorLifeCycleDemo {
    def main(args: Array[String]): Unit = {
        val actorSystem: ActorSystem = ActorSystem("ActorSystem")
        val lifeCycleDemoActor: ActorRef = actorSystem.actorOf(Props[DemoActor], "lifeCycleDemoActor")
        lifeCycleDemoActor.tell("Hello", ActorRef.noSender)

        delay(10000)

        // Stop Actor
        println("Stopping Actor..")
        actorSystem.stop(lifeCycleDemoActor);

        // Shutdown Actor System
        println("Shutting Down Actor System..")
        actorSystem.terminate
    }

    private def delay(time: Long): Unit = {
        try {
            Thread.sleep(time)
        } catch {
            case e: Exception =>
                e.printStackTrace()
        }
    }
}


