

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class countDownLatch {
    public static void main(String[] args) {
        ExecutorService executorService= Executors.newSingleThreadExecutor();

        CountDownLatch latch=new CountDownLatch(5);
        for(int i=0;i<4;i++){
            executorService.execute(new Workerhi(i+1,latch));
        }
        try {
            latch.await();
            System.out.println("All the prerequisites are done");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        executorService.shutdown();
    }
}

class Workerhi implements Runnable{

    private  int id;
    private CountDownLatch countDownLatch;


    public Workerhi(int id, CountDownLatch countDownLatch) {
        this.id = id;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        doWork();
        countDownLatch.countDown();
        System.out.println("countdown are done");
    }

    private void doWork() {
        System.out.println("Thread with ID "+this.id+"starts working");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}