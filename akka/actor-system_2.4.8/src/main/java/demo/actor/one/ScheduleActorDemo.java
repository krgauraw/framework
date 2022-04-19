package demo.actor.one;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;
import scala.concurrent.duration.FiniteDuration;

import java.util.concurrent.TimeUnit;

/**
 * An Actor, Which will schedule its execution every 5 second.
 */
class ScheduledActor extends UntypedActor {

    private final FiniteDuration SCHEDULE = new FiniteDuration(5, TimeUnit.SECONDS);

    @Override
    public void onReceive(Object message) throws Throwable {
        System.out.println("onReceive called by " + getSender().path().name() + " , Message : " + message);
        getContext().system().scheduler().scheduleOnce(SCHEDULE, getSelf(), "self-schedule", getContext().dispatcher(), ActorRef.noSender());
    }
}

/**
 * Driver Program to invoke an actor which will schedule itself for every 5 second.
 *
 * @author Kumar Gauraw
 */
public class ScheduleActorDemo {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("ActorSystem");
        ActorRef actor1 = system.actorOf(Props.create(ScheduledActor.class), "ScheduledActor");
        actor1.tell("Hello Actor", actor1);
    }
}
