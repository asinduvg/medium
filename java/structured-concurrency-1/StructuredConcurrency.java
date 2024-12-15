import java.util.concurrent.*;

class StructuredConcurrency {
    public static void main(String[] args) {
        System.out.println("[Thread " + Thread.currentThread().threadId() + "] Starting computation...");
        long startTime = System.currentTimeMillis();

        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            scope.fork(() -> longComputation());
            scope.fork(() -> computation());
            scope.join(); // Wait for all tasks to complete
            scope.throwIfFailed(); // Propagate exceptions if any task failed
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        long endTime = System.currentTimeMillis();
        System.out.println("[Thread " + Thread.currentThread().threadId() + "] computation took: "
                + (endTime - startTime) + " ms");
    }

    public static int longComputation() {
        System.out.println("[Thread " + Thread.currentThread().threadId() + "] Processing long computation started...");
        try {
            Thread.sleep(12000);
        } catch (InterruptedException e) {
            System.err.println("Interrupted while sleeping: " + e.getMessage());
        }
        System.out.println("[Thread " + Thread.currentThread().threadId() + "] Processing long computation ended...");
        return 12;
    }

    public static int computation() {
        System.out.println("[Thread " + Thread.currentThread().threadId() + "] Processing computation started...");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.err.println("Interrupted while sleeping: " + e.getMessage());
        }
        System.out.println("[Thread " + Thread.currentThread().threadId() + "] Processing computation ended...");
        return 5;
    }
}
