package com.sql;

import com.mysql.jdbc.PreparedStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @className: com.sql->DB
 * @description: 此处对类的功能进行简要概述
 * @author: 李迎光
 * @createDate: 2019-12-21 16:27
 * @version: 1.0
 * @todo: 此处添加后续继续开发的功能
 */
public class DB {
    public static boolean login() throws SQLException {
        Connection conn=null;
        PreparedStatement stmt=null;
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            String user = "root";
            String passwd = "123456";
            String sql = "SELECT * FROM sensor_1 ";
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/iotdata?user=root&password=123456&useSSL=false&characterEncoding=utf-8");
            stmt = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet result = stmt.executeQuery();
            System.out.println("开始判断");
            if(result.next()){
                System.out.println("1:"+result.getString(1));
                System.out.println("2:"+result.getString(2));
            }
        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            if(conn!=null)
                conn.close();
        }
        return false;
    }
    public static void main(String []argc)
    {
        try {
            DB.login();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
