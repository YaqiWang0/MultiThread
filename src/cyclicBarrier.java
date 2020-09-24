import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class cyclicBarrier {
    public static void main(String[] args) {
        ExecutorService executorService=Executors.newFixedThreadPool(5);
        CyclicBarrier cyclicBarrier=new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("All the tasks are finished");
            }
        });

        for(int i=0;i<5;++i){
            executorService.execute(new Work(cyclicBarrier,i+1));
        }
        executorService.shutdown();
    }
}


class Work implements Runnable{

    private  int id;
    private Random random;
    private CyclicBarrier cyclicBarrier;

    public Work(CyclicBarrier cyclicBarrier,int id) {
        this.cyclicBarrier = cyclicBarrier;
        this.random=new Random();
        this.id=id;
    }

    @Override
    public void run() {
        dowork();
    }

    private void dowork() {
        System.out.println("thread with id "+id +"starts the task");
        try {
            Thread.sleep(random.nextInt(3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Thread with id " +id+" finished" );

        try {
            cyclicBarrier.await();
            System.out.println("After await...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public String toString(){
        return ""+this.id;
    }
}
