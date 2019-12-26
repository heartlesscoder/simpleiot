package com.cur_mana;

import com.port_mana.PortMain;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

import static com.port_mana.PortMain.BAUDRATE;
import static com.port_mana.PortMain.DATA_HEX;

/**
 * @className: com.cur_mana->CurServlet
 * @description: 此处对类的功能进行简要概述
 * @author: 李迎光
 * @createDate: 2019-12-26 10:55
 * @version: 1.0
 * @todo: 此处添加后续继续开发的功能
 */
public class CurServlet extends HttpServlet {
    public static PortMain portMain= new PortMain();
    public void service(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException
    {
        String commend=req.getParameter("commend");
        System.out.println(commend);

        //读取传感器数据




//        //延迟关串口
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(12000);
//                    portMain.closeSerialPort();
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//        }).start();

        String result=portMain.mData;
        String temperature = "";
        String hemity = "";
        int temp = 0;
        int hem = 0;

        System.out.println("result:"+result);
        if(result.equals(""))
        {
            System.out.println("kong");
        }
        else
        {
            temperature=result.substring(3,5);
            hemity = result.substring(8,10);

            temp = ((int)temperature.charAt(0) - 48) * 16  + ((int)temperature.charAt(1)-48);
            hem = ((int)hemity.charAt(0) - 48) * 16  + ((int)hemity.charAt(1) - 48);

            System.out.println("温度:"+temp + "℃");
            System.out.println("湿度:"+hem + "%");
        }

        res.getWriter().println(temp);
    }
}
