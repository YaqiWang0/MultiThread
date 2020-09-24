package concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Workers{
    private Lock lock=new ReentrantLock(true);
    private Condition condition=lock.newCondition();

    public void producers() throws InterruptedException{

        lock.lock();
        System.out.println("Producer method");

        condition.await();

        System.out.println("Producer again");
        lock.unlock();
    }

    public void consumer() throws InterruptedException{
        lock.lock();
        Thread.sleep(2000);
        System.out.println("consumer method");
        condition.signal();

        lock.unlock();
    }
}
class Processor{
    private List<Integer> list=new ArrayList<>();
    private final int LIMIT=5;
    private final int BOTTOM=0;
    private final Object lock=new Object();
    private  int value=0;
    public void produce() throws InterruptedException{

        synchronized (lock){
            while(true){
                if(list.size()==LIMIT){
                    System.out.println("Waiting for removing items from the list");
                    lock.wait();
                }
                else{
                    System.out.println("Adding:" +value);
                    list.add(value);
                    value++;
                    lock.notify();
                }

                Thread.sleep(500);
            }

        }
    }

    public void consume() throws InterruptedException{

        synchronized (lock){

            while(true){
                if(list.size()==BOTTOM){
                    System.out.println("Waiting for adding items to the list");
                    lock.wait();
                }
                else{
                    System.out.println("Remove:" +list.remove(--value));
                    lock.notify();
                }

                Thread.sleep(500);
            }


//            notify();
        }
    }
}

public class producerAndConsumer {

    public static void main(String[] args) {

        Processor processor=new Processor();
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();



    }
}

class producerAndConsumerLock {

    public static void main(String[] args) {

        Workers processor=new Workers();
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    processor.producers();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
