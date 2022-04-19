package demo.actor.one;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;

/**
 * Simple Actor, Which Print the message.
 */
class Actor1 extends UntypedActor {
    @Override
    public void onReceive(Object message) throws Throwable {
        System.out.println("Message Received : " + message + " , Actor Name : " + getSelf().path().name());
    }

    public void postStop() throws Exception {
        super.postStop();
        System.out.println(getSelf().path().name() + " actor stopped.");
    }
}


/**
 * Driver Program which shows that, how to stop an actor
 *
 * @author Kumar Gauraw
 */
public class StopActorDemo {

    public static void main(String[] args) {
        //Creating Actor System
        ActorSystem system = ActorSystem.create("ActorSystem");
        // Creating Actor Instance
        ActorRef actor1 = system.actorOf(Props.create(Actor1.class), "Actor1");
        //Sending a message to the actor
        actor1.tell("Hello Actor1", actor1);

        // stop actor
        system.stop(actor1);

        // shutdown actor system
        system.terminate();
    }
}
