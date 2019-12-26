package com.cur_mana;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

/**
 * @className: com.cur_mana->CurServlet
 * @description: 此处对类的功能进行简要概述
 * @author: 李迎光
 * @createDate: 2019-12-26 10:55
 * @version: 1.0
 * @todo: 此处添加后续继续开发的功能
 */
public class CurServlet extends HttpServlet {
    public void service(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException
    {
        String commend=req.getParameter("commend");
        System.out.println(commend);
        int i;
        i=(int)(Math.random()*10);
        System.out.println(i);
        String result=Integer.toString(i);
        System.out.println(result);
        res.getWriter().println(result);
    }
}
