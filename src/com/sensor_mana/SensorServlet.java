package com.sensor_mana;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @className: com.sensor_mana->SensorServlet
 * @description: 此处对类的功能进行简要概述
 * @author: 李迎光
 * @createDate: 2019-12-22 10:20
 * @version: 1.0
 * @todo: 此处添加后续继续开发的功能
 */
public class SensorServlet extends HttpServlet {
    public void service(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException
    {/*
        String type = (String)req.getParameter("type");

        if(type.equals("control")){
            System.out.println((String)req.getParameter("name1"));
            String forward ="index.jsp";
            RequestDispatcher df=req.getRequestDispatcher(forward);
            df.forward(req,res);
        }*/

        String username=(String)req.getParameter("username");
        System.out.println("username:"+username);
        String sensorname=(String)req.getParameter("sensor");
        System.out.println(sensorname);
        if(sensorname.equals("red"))
        {
            String msg="red already closed";
            res.getWriter().println(msg);
        }
        else{
            String msg="super already closed";
            res.getWriter().println(msg);
        }

    }

}
