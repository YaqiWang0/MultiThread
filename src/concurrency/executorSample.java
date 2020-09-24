package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class executorSample {
    public static void main(String[] args) {
        ExecutorService executorService= Executors.newCachedThreadPool();
        for(int i=0;i<5;i++){
            executorService.submit(new WorkerAgain());
            
        }
    }

}

class WorkerAgain implements Runnable{

    @Override
    public void run() {
        for(int i=0;i<10;i++){
            System.out.println(i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}