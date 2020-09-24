package concurrency;
class Runner1 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; ++i) {
            System.out.println("Runner 1:"+i);
        }
    }
}

class Runner2 extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 10; ++i) {
            System.out.println("Runner 2:"+i);
        }
    }
}
public class App {
    public static void main(String[] args) throws InterruptedException {
        Runner1 t1=new Runner1();
        Runner2 t2=new Runner2();


        t1.start();
        t2.start();

//        t1.join();
//        t2.join();

        System.out.println("finish");


    }
}
