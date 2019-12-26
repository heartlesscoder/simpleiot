package com.sql_mana;

import com.data_mana.ND;
import com.port_mana.PortMain;
import com.thread_mana.ST;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.port_mana.PortMain.BAUDRATE;
import static com.port_mana.PortMain.DATA_HEX;


public class DB {

    public Connection getconnection(){
        String driver = "com.mysql.jdbc.Driver";
        String user = "root";
        String passwd = "zgdzdx126";
        String className = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/iotdata?user=root&password=zgdzdx126&useSSL=false&allowPublicKeyRetrieval=true&characterEncoding=utf-8";
        Connection conn ;
        Statement stmt ;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url);
            return conn;
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public void print_table(){
        Connection conn ;
        Statement stmt ;
        try
        {
            conn = getconnection();
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM sensor_1";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                // 通过字段检索
                String id  = rs.getString("id");
                String time = rs.getString("time");
                String data = rs.getString("data");
                ND.update(data);

                // 输出数据
                System.out.print("打印：");
                System.out.print("ID: " + id);
                System.out.print(" , time: " + time);
                System.out.print(" , data: " + data);
                System.out.print("\n");
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException | InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    //获取当前时间
    public String get_now_time(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateNowStr = sdf.format(date);
        return dateNowStr;
    }

    //将记录写入数据库
    public void write_to_database(Connection conn, String data, String sname) throws InterruptedException {
        String time = get_now_time();
        String sql = "INSERT INTO " + sname + "(`time`, `data`) VALUES ('" + time + "', '" + data + "');";
        ND.update(data);
        PreparedStatement preStmt = null;
        try {
            preStmt = conn.prepareStatement(sql);
            preStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void print_newest(String table_name){
        Connection conn ;
        Statement stmt ;
        try
        {
            int i = 0;
            conn = getconnection();
            stmt = conn.createStatement();
            String sql = "select * from " + table_name + " order by id desc limit "+String.valueOf(ND.getCapacity());
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                // 通过字段检索
                String id  = rs.getString("id");
                String time = rs.getString("time");
                String data = rs.getString("data");

                // 输出数据
                System.out.print("最新：");
                System.out.print("ID: " + id);
                System.out.print(" , time: " + time);
                System.out.print(" , data: " + data);
                System.out.print("\n");
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] arg) throws InterruptedException {
        PortMain portMain = new PortMain();

        portMain.mDataHexChoice = DATA_HEX;
        String commName = "COM5";
        String data = new String("01");   //要发的数据
        portMain.openSerialPort(commName, BAUDRATE);

        //延迟关串口
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(12000);
                    portMain.closeSerialPort();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
        //portMain.sendData(data);   //发数据

        DB db = new DB();


        System.out.println("未读取时数据库中原有数据                                    ");
        db.print_table();
        ND.print_newest();

        ST st = new ST();
        st.portMain = portMain;
        st.auto_init("sensor_1");//init mythread1
        System.out.println("初始化自动读取并写入线程 5秒 后数据                          ");

        Thread.sleep(5000);//等5秒打印
        db.print_table();
        ND.print_newest();

        st.auto_exit(st.mythread1);//stop mythread1
        System.out.println("退出读取并写入线程 5秒 后数据                            ");

        Thread.sleep(5000);//等5秒打印
        db.print_table();
        ND.print_newest();

        st.auto_init("sensor_1");//continue mythread1
        System.out.println("继续自动读取并写入线程 5秒 后数据                            ");

        Thread.sleep(5000);//等5秒打印
        db.print_table();
        ND.print_newest();

        st.auto_exit(st.mythread1);
        System.out.print("正常结束");
    }

    /*public void test() throws InterruptedException {
        DB db = new DB();

        System.out.println("未读取时数据库中原有数据                                    ");
        db.print_table();//打印
        db.print_newest();

        ST st = new ST();
        st.auto_init(st.mythread1);//init mythread1
        System.out.println("初始化自动读取并写入线程 5秒 后数据                          ");

        Thread.sleep(5000);//等5秒打印
        db.print_table();
        db.print_newest();

        st.auto_stop(st.mythread1);//stop mythread1
        System.out.println("暂停自动读取并写入线程 5秒 后数据                            ");

        Thread.sleep(5000);//等5秒打印
        db.print_table();
        db.print_newest();

        st.auto_continue(st.mythread1);//continue mythread1
        System.out.println("继续自动读取并写入线程 5秒 后数据                            ");

        Thread.sleep(5000);//等5秒打印
        db.print_table();
        db.print_newest();
        st.auto_stop(st.mythread1);
        System.out.println("暂停自动读取并写入线程");
    }*/
}
