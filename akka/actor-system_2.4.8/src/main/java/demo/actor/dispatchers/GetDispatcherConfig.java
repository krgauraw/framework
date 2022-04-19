package demo.actor.dispatchers;

import akka.actor.ActorSystem;
import com.typesafe.config.Config;

/**
 * This Class Print Default Dispatcher Configuration of Actor System.
 *
 * @author Kumar Gauraw
 */
public class GetDispatcherConfig {

    public static void main(String[] args) {

        // Create Actor System
        // Custom Name for System Can be passed here.
        // We can provide configuration for an actor system through typesafe config.
        ActorSystem system = ActorSystem.create("ActorSystem");

        // Access default dispatcher configuration
        Config config = system.settings().config().getConfig("akka.actor.default-dispatcher");

        System.out.println("Config : " + config);

        System.out.println("Akka Default Dispatcher configuration : ");
        System.out.println("type: " + config.getString("type"));
        String executor = config.getString("executor");
        System.out.println("executor: " + executor);
        String fallbackExecutor = config.getString(executor + ".fallback");
        System.out.println("executor fallback: " + fallbackExecutor); // fork-join-executor
        System.out.println("parallelism-min for " + fallbackExecutor + ": " + config.getString(fallbackExecutor + ".parallelism-min"));
        System.out.println("parallelism-max for " + fallbackExecutor + ": " + config.getString(fallbackExecutor + ".parallelism-max"));
        System.out.println("parallelism-factor for " + fallbackExecutor + ": " + config.getString(fallbackExecutor + ".parallelism-factor"));
        System.out.println("throughput: " + config.getString("throughput"));
    }
}
