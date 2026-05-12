package threads;

import java.util.concurrent.TimeUnit;

public class MyRunnable implements Runnable
{

    @Override
    public void run() {
        for(int i=0; i<5; i++)
        {
            System.out.print(" 2 ");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
