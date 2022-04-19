package demo.actor.scala.life_cycle;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.util.Timeout;
import scala.Option;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

class DemoActor extends UntypedActor {

    public void onReceive(Object message) throws Throwable {
        if (message instanceof String) {
            System.out.println("Message Received by " + getSelf().path().name() + " : " + message);
        }
        // code to generate exception. so that preRestart() and postRestart() will be invoked for self healing process.
        int i = 10 / 0;

    }

    public void preStart() throws Exception {
        super.preStart();
        System.out.println("preStart() is called! " + getSelf().path().name() + " is going to start.");
    }

    public void postStop() throws Exception {
        super.postStop();
        System.out.println("postStop() is called! " + getSelf().path().name() + " is stopped.");
    }

    public void preRestart(Throwable reason, Option<Object> message) throws Exception {
        super.preRestart(reason, message);
        System.out.println("preRestart() is called.");
        for (ActorRef ref : getContext().getChildren())
            getContext().stop(ref);
        postStop();
    }

    public void postRestart(Throwable reason) throws Exception {
        super.postRestart(reason);
        System.out.println("postRestart() is called.");
    }
}

/**
 * Driver Program for Actor Life Cycle Demo
 *
 * @author Kumar Gauraw
 */
public class ActorLifeCycleDemo {

    private static Timeout TIMEOUT = new Timeout(Duration.create(30, TimeUnit.SECONDS));

    public static void main(String[] args) throws Exception {

        // Create Actor System
        ActorSystem system = ActorSystem.create("ActorSystem");

        // Create Actor and Get Actor Reference
        ActorRef demoActor = system.actorOf(Props.create(DemoActor.class), "demoActor");

        // Send Message to the actor using tell()
        demoActor.tell("Hello Actor..", ActorRef.noSender());

        delay(10000);

        // Shutdown Actor System
        System.out.println("Shutting Down Actor System..");
        system.terminate();
    }

    /**
     * @param time
     */
    private static void delay(long time) {
        try {
            Thread.sleep(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
