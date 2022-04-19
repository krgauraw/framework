package demo.actor.communication.tell;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;

import java.util.UUID;

/**
 * Actor1
 */
class Actor1 extends UntypedActor {
    @Override
    public void onReceive(Object message) throws Throwable {
        System.out.println("Message Received : " + message + " , Actor Name : " + self().path().name());
        System.out.println("Sender Name : " + sender().path().name());
        // Creating a child actor and calling
        ActorRef actor2 = context().actorOf(Props.create(Actor2.class), "Actor-" + UUID.randomUUID().toString());
        actor2.tell("Hello Actor2", self());
        actor2.tell("Hello Actor2 Again", null);
        actor2.tell("Hello Actor2 Once Again", ActorRef.noSender());
    }
}

/**
 * Actor2
 */
class Actor2 extends UntypedActor {
    @Override
    public void onReceive(Object message) throws Throwable {
        System.out.println("Message Received : " + message + " , Actor Name : " + self().path().name() + "Sender Name : " + sender().path().name());
    }
}

/**
 * Driver Program to show actor communication using tell
 *
 * @author Kumar Gauraw
 */
public class SendMessage_1 {
    public static void main(String[] args) throws Exception {
        ActorSystem system = ActorSystem.create("ActorSystem");
        ActorRef actor1 = system.actorOf(Props.create(Actor1.class), "Actor1");
        actor1.tell("Hello Actor1", actor1);
        Thread.sleep(2000);
        System.out.println("-------------------------------------------------------");
        actor1.tell("Hello Actor1 Again", null);
        Thread.sleep(2000);
        System.out.println("-------------------------------------------------------");
        actor1.tell("Hello Actor1 Once Again", ActorRef.noSender());
        Thread.sleep(2000);
        system.terminate();
    }
}
