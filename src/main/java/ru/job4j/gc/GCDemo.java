package ru.job4j.gc;

public class GCDemo {

    private static final long KB = 1000;
    private static final long MB = KB * KB;
    private static final Runtime ENVIRONMENT = Runtime.getRuntime();

    public static void info() {
        final long freeMemory = ENVIRONMENT.freeMemory();
        final long totalMemory = ENVIRONMENT.totalMemory();
        final long maxMemory = ENVIRONMENT.maxMemory();
        System.out.println("=== Environment state ===");
        System.out.printf("Free: %d%n", freeMemory / MB);
        System.out.printf("Total: %d%n", totalMemory / MB);
        System.out.printf("Max: %d%n", maxMemory / MB);
    }

    /*
    Ключ -Xmx4m -Xms2m
    При i < 4000 виртуальная машины вызывает сборщик мусора самостоятельно
    При i < 19000 срабатывает Exception: java.lang.OutOfMemoryError
     */
    public static void main(String[] args) {
        info();
        for (int i = 0; i < 19000; i++) {
            new User(22, "Sergey" + i);
        }
        info();
    }

}
