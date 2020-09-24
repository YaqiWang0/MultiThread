

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

class firstWorker implements Runnable{

    private BlockingQueue<person> blockingQueue;

    public firstWorker(BlockingQueue<person> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        try{
            blockingQueue.put(new person(12,"Adam"));
            blockingQueue.put(new person(45,"joe"));
            blockingQueue.put(new person(78,"Daniel"));
            Thread.sleep(1000);
            blockingQueue.put(new person(32,"Noel"));
            Thread.sleep(1000);
            blockingQueue.put(new person(34,"Kevin"));
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}


class secondWorker implements Runnable{

    private BlockingQueue<person> blockingQueue;

    public secondWorker(BlockingQueue<person> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }


    @Override
    public void run() {
        try{
            Thread.sleep(5000);
            System.out.println(blockingQueue.take());
            Thread.sleep(1000);
            System.out.println(blockingQueue.take());
            Thread.sleep(1000);
            System.out.println(blockingQueue.take());
            System.out.println(blockingQueue.take());
            System.out.println(blockingQueue.take());
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

class person implements Comparable<person>{

    private  int age;
    private  String name;

    public person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + "-"+age;
    }

    @Override
    public int compareTo(person o) {
        return Integer.compare(this.age,o.getAge());
    }
}
public class priorityQueue {

    public static void main(String[] args) {
        BlockingQueue<person> queue=new PriorityBlockingQueue<>();
        new Thread(new firstWorker(queue)).start();
        new Thread(new secondWorker(queue)).start();
    }
}
