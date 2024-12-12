import java.util.Random;

public class Executor {
    private final String name;

    public Executor(String name) {
        this.name = name;
    }

    public String executeTask(String task) throws InterruptedException {
        // Simulate task execution time (randomized)
        int executionTime = new Random().nextInt(5) + 1; // 1-5 seconds
        Thread.sleep(executionTime * 1000L);
        return "Executor " + name + " completed task: " + task;
    }

    public String getName() {
        return name;
    }
}
