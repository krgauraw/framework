package demo.actor.one;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;

/**
 * HelloActor is an Actor, created using java implementation.
 */
class HelloActor extends UntypedActor { //extended UntypedActor
    // Implemented onReceive
    public void onReceive(Object message) throws Throwable {
        if (message instanceof String) {
            System.out.println(message);
        } else {
            System.out.println("Unknown Message");
        }
    }
}


/**
 * Driver Program which create an Actor System, Instantiate HelloActor Actor
 * and send some message using actor (HelloActor) reference.
 *
 * @author Kumar Gauraw
 */
public class HelloActorDemo {

    public static void main(String[] args) {

        // Creating ActorSystem
        ActorSystem system = ActorSystem.create("ActorSystem");

        //Creating actor
        ActorRef helloActor = system.actorOf(Props.create(HelloActor.class));

        // Sending messages using tell()
        helloActor.tell("Hello Akka!", ActorRef.noSender());
        helloActor.tell("How Are You?", ActorRef.noSender());
        helloActor.tell(100, ActorRef.noSender());

        //shut down actor system.
        //system.shutdown(); //this method is deprecated
        system.terminate();
    }
}
