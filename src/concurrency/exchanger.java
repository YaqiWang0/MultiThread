package concurrency;


import java.math.BigInteger;
import java.util.concurrent.Exchanger;

class FirstThread implements Runnable{
    private  int counter;
    private Exchanger<Integer> exchanger;

    public FirstThread(Exchanger<Integer> exchanger) {
        this.exchanger = exchanger;
    }


    @Override
    public void run() {
        while(true){
            counter=counter+1;
            System.out.println("First thread,increment the counter: "+counter);

            try {
                counter=exchanger.exchange(counter);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            BigInteger a=new BigInteger(String.valueOf(3));

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}

class SecondThread implements Runnable{
    private  int counter;
    private Exchanger<Integer> exchanger;

    public SecondThread(Exchanger<Integer> exchanger) {
        this.exchanger = exchanger;
    }


    @Override
    public void run() {
        while(true){
            counter=counter-1;
            System.out.println("Second thread,decrement the counter: "+counter);

            try {
                counter=exchanger.exchange(counter);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class exchanger {
    public static void main(String[] args) {
        Exchanger<Integer> exchanger=new Exchanger<>();
        new Thread(new FirstThread(exchanger)).start();
        new Thread(new SecondThread(exchanger)).start();
    }

}
