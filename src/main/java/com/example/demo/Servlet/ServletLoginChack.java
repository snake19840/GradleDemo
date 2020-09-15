package com.example.demo.Servlet;

import com.example.demo.service.IpControl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ServletLoginChack")
public class ServletLoginChack extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IpControl ipControl =new IpControl();
        String a= ipControl.getIpAddr(request);
        System.out.println(a);
        PrintWriter out = response.getWriter();
        out.print("<h1>登陆IP:  "+a+"</h1>");
        out.close();

    }
}
