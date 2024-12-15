class Sequential {
  public static void main(String[] args) {
    System.out.println("[Thread " + Thread.currentThread().threadId() + "] Starting computation...");
    long startTime = System.currentTimeMillis();
    longComputation();
    computation();
    long endTime = System.currentTimeMillis();
    System.out.println(
        "[Thread " + Thread.currentThread().threadId() + "] computation took: " + (endTime - startTime) + " ms");
  }

  public static int longComputation() {
    System.out.println("[Thread " + Thread.currentThread().threadId() + "] Processing long computation started...");
    try {
      Thread.sleep(12000);
    } catch (InterruptedException e) {
      System.err.println("Interrupted while sleeping: " + e.getMessage());
    }
    System.out.println("[Thread " + Thread.currentThread().threadId() + "] Processing long computation ended...");
    return 12000;
  }

  public static int computation() {
    System.out.println("[Thread " + Thread.currentThread().threadId() + "] Processing computation started...");
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      System.err.println("Interrupted while sleeping: " + e.getMessage());
    }
    System.out.println("[Thread " + Thread.currentThread().threadId() + "] Processing computation ended...");
    return 5000;
  }
}