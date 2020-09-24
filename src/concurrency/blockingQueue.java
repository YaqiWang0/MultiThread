package concurrency;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class FirstWorker1 implements Runnable{

    private BlockingQueue<Integer> blockingQueue;

    public FirstWorker1(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        int counter=0;
        while(true){

            try {
                blockingQueue.offer(counter);
                System.out.println("putting items to the queue "+counter+"queue"+blockingQueue.size());
                counter++;
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class SecondWorker2 implements Runnable{

    private BlockingQueue<Integer> blockingQueue;

    public SecondWorker2(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {

        while(true){

            try {
                int number=blockingQueue.take();
                System.out.println("taking item from the queue" +number);

                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class blockingQueue {
    public static void main(String[] args) {

        BlockingQueue<Integer> queue=new ArrayBlockingQueue<>(10);
        FirstWorker1 firstWorker=new FirstWorker1(queue);
        SecondWorker2 secondWorker=new SecondWorker2(queue);

        new Thread(firstWorker).start();
        new Thread(secondWorker).start();
    }
}
