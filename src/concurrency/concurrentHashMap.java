package concurrency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

class firstWorker1 implements Runnable{
    private ConcurrentMap<String,Integer> map;

    public firstWorker1(ConcurrentMap<String, Integer> map) {
        this.map = map;
    }


    @Override
    public void run() {

        try {
            map.put("B",1);
            map.put("H",2);
            Thread.sleep(1000);
            map.put("F",3);
            map.put("A",4);
            Thread.sleep(1000);
            map.put("A",5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}

class secondWorker2 implements Runnable{
    private ConcurrentMap<String,Integer> map;

    public secondWorker2(ConcurrentMap<String, Integer> map) {
        this.map = map;
    }


    @Override
    public void run() {

        try {
           Thread.sleep(5000);
            System.out.println(map.get("A"));
            Thread.sleep(1000);
            System.out.println(map.get("E"));
            System.out.println(map.get("C"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}

public class concurrentHashMap {
    public static void main(String[] args) {
        ConcurrentMap<String,Integer> map=new ConcurrentHashMap<>();
        new Thread(new firstWorker1(map)).start();
        new Thread(new secondWorker2(map)).start();
        List<String> list=new ArrayList<>();
        List<String> list2= Collections.synchronizedList(list);
    }
}
