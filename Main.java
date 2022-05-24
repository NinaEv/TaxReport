package jadv.task_3_2;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;

public class Main {

    private static final int BUY_COUNT = 10;
    private static final int DOWN_RATE = 10;
    private static final int HIGH_RATE = 1500;

    public static void main(String[] args) {
        try {
            ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
            LongAdder stat = new LongAdder();

            new Random().ints(BUY_COUNT, DOWN_RATE, HIGH_RATE).boxed().collect(Collectors.toList())
                    .forEach(i -> executorService.submit(() -> stat.add(i)));
            new Random().ints(BUY_COUNT, DOWN_RATE, HIGH_RATE).boxed().collect(Collectors.toList())
                    .forEach(i -> executorService.submit(() -> stat.add(i)));
            new Random().ints(BUY_COUNT, DOWN_RATE, HIGH_RATE).boxed().collect(Collectors.toList())
                    .forEach(i -> executorService.submit(() -> stat.add(i)));

            executorService.awaitTermination(3, TimeUnit.SECONDS);
            System.out.println("Общий итог " + stat.sum());
            executorService.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
