package com.wondersgroup.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

/**
 * @author chenlin
 * @create 2019-05-30 12:36
 * @description: 配置servlet的方式一
 * @version：1.0
 **/
@WebServlet(name = "firstServlet", urlPatterns = "/first")
public class FirstServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("firstServlet .....");
        Writer out = resp.getWriter();
        out.write("firstServlet user by @WebServlet");
    }


}
