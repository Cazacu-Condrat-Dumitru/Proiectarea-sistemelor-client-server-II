import java.util.List;
import java.util.concurrent.*;

public class Coordinator {
    private final List<Executor> executors;
    private final ExecutorService executorService;

    public Coordinator(List<Executor> executors) {
        this.executors = executors;
        this.executorService = Executors.newCachedThreadPool();
    }

    public void distributeTasks(List<String> tasks) {
        for (String task : tasks) {
            boolean taskCompleted = false;
            for (Executor executor : executors) {
                Future<String> future = executorService.submit(() -> executor.executeTask(task));
                try {
                    // Timeout after 5 seconds
                    String result = future.get(5, TimeUnit.SECONDS);
                    System.out.println(result);
                    taskCompleted = true;
                    break;
                } catch (TimeoutException e) {
                    System.out.println("Executor " + executor.getName() + " timed out for task: " + task);
                    future.cancel(true);
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }

            if (!taskCompleted) {
                System.out.println("Task failed: " + task);
            }
        }
        shutdown();
    }

    private void shutdown() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }
}

