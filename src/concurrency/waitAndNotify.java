package concurrency;
class Precessor{
    public void produce() throws InterruptedException{

        synchronized (this){
            System.out.println("We are in the producer method");

            wait(10000);
            System.out.println("Again producer method");

        }
    }

    public void consume() throws InterruptedException{
        Thread.sleep(1000);
        synchronized (this){

            System.out.println("Consumer method");
           notify();
        }
    }
}

public class waitAndNotify {
    public static void main(String[] args) {

        Precessor precessor=new Precessor();
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    precessor.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    precessor.consume();
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
