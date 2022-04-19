package demo.actor.communication.ask;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.pattern.Patterns;
import akka.util.Timeout;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

class AskDemoActor2 extends UntypedActor {
    @Override
    public void onReceive(Object message) throws Throwable {
        System.out.println("Message Received : " + message + " , Actor Nanme : " + getSelf().path().name());
        System.out.println("Sender Name : " + getSender().path().name());
        System.out.println("Replying to Sender ....");
        getSender().tell("Hello! I have Received your Message", getSelf());
    }
}

/**
 * Driver Program which shows actor communication using ask.
 *
 * @author Kumar Gauraw
 */
public class SendMessage_2 {

    static Timeout timeout = new Timeout(Duration.create(30, TimeUnit.SECONDS));

    public static void main(String[] args) throws Exception {
        ActorSystem system = ActorSystem.create("ActorSystem");
        ActorRef askDemoActor = system.actorOf(Props.create(AskDemoActor2.class), "AskDemoActor2");
        Future<Object> future = Patterns.ask(askDemoActor, "Hello Actor", 2000);
        Object actor1Response = Await.result(future, timeout.duration());
        System.out.println("Actor Response : " + actor1Response);
        system.terminate();
    }
}
