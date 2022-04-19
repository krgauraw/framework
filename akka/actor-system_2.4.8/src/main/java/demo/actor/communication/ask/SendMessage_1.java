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

/**
 * An Actor, Which will be invoked using ask.
 */
class AskDemoActor1 extends UntypedActor {
    @Override
    public void onReceive(Object message) throws Throwable {
        System.out.println("Message Received : " + message + " , Actor Name : " + getSelf().path().name());
        System.out.println("Sender Name : " + getSender().path().name());
    }
}

/**
 * Driver Program which shows actor communication using ask.
 * Ask Timeout Will happen as No implementation provided for Reply back from AskDemoActor1
 * @author Kumar Gauraw
 */
public class SendMessage_1 {

    static Timeout timeout = new Timeout(Duration.create(30, TimeUnit.SECONDS));

    public static void main(String[] args) throws Exception {
        ActorSystem system = ActorSystem.create("ActorSystem");
        ActorRef askDemoActor = system.actorOf(Props.create(AskDemoActor1.class), "AskDemoActor1");
        Future<Object> future = Patterns.ask(askDemoActor, "Hello Actor", 2000);
        Object actor1Response = Await.result(future, timeout.duration());
        System.out.println("Actor Response : " + actor1Response);
        system.terminate();
    }
}
