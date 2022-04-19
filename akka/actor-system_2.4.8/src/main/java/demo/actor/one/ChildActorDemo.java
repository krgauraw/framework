package demo.actor.one;


import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;

/**
 * Parent Actor, will be invoked by Driver Program
 */
class ParentActor extends UntypedActor {

    public void onReceive(Object message) throws Throwable {
        if (message instanceof String) {
            System.out.println("Message Received: " + message + " , Actor Name : " + self().path().name());
        }
        //Creating Child Actor using implicit context
        ActorRef childActor = getContext().actorOf(Props.create(ChildActor.class), "childActor");
        childActor.tell("Hello Child", ActorRef.noSender());
    }
}

/**
 * Child Actor, will be created and invoked by Parent Actor
 */
class ChildActor extends UntypedActor {

    public void onReceive(Object message) throws Throwable {
        if (message instanceof String) {
            System.out.println("Message Received: " + message + " , Actor Name : " + self().path().name());
        }
    }
}


/**
 * Driver Program for Child Actor Demonstration.
 * This Program shows that how to create a child actor.
 *
 * @author Kumar Gauraw
 */
public class ChildActorDemo {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("actor-system");
        ActorRef parentActor = system.actorOf(Props.create(ParentActor.class), "parentActor");
        parentActor.tell("Hello Parent", ActorRef.noSender());
        system.terminate();
    }
}
