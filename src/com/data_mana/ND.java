package com.data_mana;

import java.util.concurrent.LinkedBlockingQueue;

public class ND {
    private static int capacity = 1;
    static LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<String>();

    public static void update(String str) throws InterruptedException {
        while(queue.size()>capacity){
            queue.take();
        }
        if(queue.size()==capacity){
            queue.take();
            queue.add(str);
        }
        else{
            queue.add(str);
        }
    }

    public static boolean setCapacity(int capa){
        if(capa <= 1){
            return false;
        }
        else{
            capacity = capa;
            return true;
        }
    }

    public static int getCapacity(){
        return capacity;
    }

    public static LinkedBlockingQueue<String> myqueue(){
        return queue;
    }

    public static Object[] newest_data(){
        return queue.toArray();
    }

    public static void print_newest() throws InterruptedException {
        Object[] array = queue.toArray();

        for(int i=0; i<array.length; i++) {
            // 输出数据
            System.out.print("最新：");
            System.out.print(array[i]);
            System.out.print("\n");
        }
    }
}
