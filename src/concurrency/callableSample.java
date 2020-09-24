package concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

class Prosessor implements Callable<String>{

    private  int id;

    public Prosessor(int id) {
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(1000);
        return "ID: "+id;
    }
}

public class callableSample {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        List<Future<String>> list = new ArrayList<>();

        for (int i = 0; i < 5; ++i) {
            Future<String> future = executorService.submit(new Prosessor(i + 1));
            list.add(future);
        }

        for (Future<String> future : list) {
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            executorService.shutdown();
        }

    }
}
