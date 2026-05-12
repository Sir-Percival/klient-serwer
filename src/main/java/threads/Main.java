package threads;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main
{
    static void main()
    {
        ExecutorService executor = Executors.newFixedThreadPool(10);

        for(int i=5; i<10; i++)
        {
            int temp = i;
            executor.execute(() -> printNumber(temp));
        }

        MyThread myThread = new MyThread();
        myThread.start();

        Thread myRunnable = new Thread(new MyRunnable());
        myRunnable.start();

        for(int i=0; i<5; i++)
        {
            System.out.print(" 0 ");
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        executor.shutdown();

    }

    private static void printNumber(int numberToPrint)
    {
        for(int i=0; i<5; i++)
        {
            System.out.printf(" %d ", numberToPrint);
            int timeToSleep = new Random().nextInt(300, 1000);
            try {
                Thread.sleep(timeToSleep);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
