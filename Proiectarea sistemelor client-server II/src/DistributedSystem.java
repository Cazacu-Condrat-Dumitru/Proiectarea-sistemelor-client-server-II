import java.util.*;


public class DistributedSystem {
    public static void main(String[] args) {
        // Create Executors
        List<Executor> executors = Arrays.asList(
                new Executor("Executor1"),
                new Executor("Executor2"),
                new Executor("Executor3")
        );

        // Create Coordinator
        Coordinator coordinator = new Coordinator(executors);

        // Define Tasks
        List<String> tasks = Arrays.asList("Task1", "Task2", "Task3", "Task4");

        // Distribute Tasks
        coordinator.distributeTasks(tasks);
    }
}
