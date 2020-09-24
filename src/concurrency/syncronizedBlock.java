package concurrency;

public class syncronizedBlock {
    private static int count1=0;
    private static int count2=0;

    private static Object lock1=new Object();
    private static Object lock2=new Object();
    public synchronized static  void add(){
       synchronized (lock1){
           count1++;
       }

    }

    public synchronized static  void addagain(){
        synchronized (lock2){
            count2++;
        }
    }

    public  static  void compute(){
        for(int i=0;i<10;i++){
            add();
            addagain();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                compute();
            }
        });

        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                compute();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(count1+"+"+count2);
    }
}
