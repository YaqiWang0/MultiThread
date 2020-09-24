package concurrency;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class delayQueue {
    public static void main(String[] args) {
        BlockingQueue<delayedWorker> queue=new DelayQueue<>();
        try {
            queue.put(new delayedWorker(1000,"This is the first message"));
            queue.put(new delayedWorker(10000,"This is the second message"));
            queue.put(new delayedWorker(4000,"This is the third message"));
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        while(!queue.isEmpty()){
            try {
                System.out.println(queue.take());
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}

class delayedWorker implements Delayed{

    private long duration;
    private String message;

    public long getDuration() {
        return duration;
    }

    public delayedWorker(long duration, String message) {
        this.duration = System.currentTimeMillis()+duration;
        this.message = message;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(duration-System.currentTimeMillis(),TimeUnit.MICROSECONDS);
    }

    @Override
    public int compareTo(Delayed otherDelayed) {
        if(this.duration<((delayedWorker)otherDelayed).getDuration()){
            return -1;
        }
        else if(this.duration>((delayedWorker)otherDelayed).getDuration())
            return 1;
        else
            return 0;
    }

    @Override
    public String toString() {
        return  message;
    }
}
