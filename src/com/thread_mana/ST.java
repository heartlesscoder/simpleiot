package com.thread_mana;

import com.port_mana.PortMain;
import com.sql_mana.DB;

import java.sql.*;

public class ST {
    public Mythread mythread1 = null;
    public Mythread mythread2 = null;
    public PortMain portMain = null;

    int sleep_time = 5000;

    //获取传感器数据
    public String get_sensor_data(){
        double data = 0;

        // 读取代码
        return portMain.mData;
    }

    //开启线程自动读取传感器数据并
    public void auto_init(String name){
        Mythread mythread = new Mythread();
        Thread t = new Thread(mythread);
        mythread.set_tag_true();
        mythread.set_value(sleep_time,name);
        t.start();
        if(name.equals("sensor_1")){
            mythread1 = mythread;
        }
        else if(name.equals("sensor_2"))
        {
            mythread2 = mythread;
        }
        else{
            System.out.println("fail!");
            return;
        }
    }

    //we don't need anymore
    /*
    public void auto_stop(Mythread mythread){
        mythread.set_tag_false();
    }

    public void auto_continue(Mythread mythread){
        mythread.set_tag_true();
    }*/

    public int auto_exit(Mythread mythread){
        if(mythread == null){
            return -1;
        }
        mythread.set_tag_false();
        return 0;
    }

    //重写thread run方法
     class Mythread implements Runnable {
        boolean tag;
        int stime;//读取传感器的间隔时间
        String sname;//表名

        public void set_value(int stime, String sname){
            this.stime = stime;
            this.sname = sname;
        }

        public void set_tag_true(){
            this.tag = true;
        }

        public void set_tag_false(){
            this.tag = false;
        }

        @Override
        public void run() {
            //double data = -0.1;//读取的数据
            DB db = new DB();
            Connection sensor_conn = db.getconnection();
            while(true){
                if(tag) {
                    String data = get_sensor_data();
                    if(!data.equals("")) {
                        try {
                            db.write_to_database(sensor_conn, data, sname);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        try {
                            Thread.sleep(stime);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                else break;
            }
        }
    }
}
