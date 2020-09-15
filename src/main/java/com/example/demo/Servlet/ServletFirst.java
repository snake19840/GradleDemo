package com.example.demo.Servlet;



import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("/fs")
@SuppressWarnings("unchecked")
public class ServletFirst extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("servlet......................");
        String a= (String) request.getAttribute("a");
//        a="wqwqw";
        List list=new ArrayList();
        list.add(request.getLocale());
        list.add(request.getScheme());
        list.add(request.getContentType());
        list.add(request.getDispatcherType());
        list.add(request.getLocalName());
        list.add(request.getServerName());
        list.add(request.getContextPath());
        list.add(request.getServletPath());
        list.add(request.getPathInfo());
        list.add(request.getRemoteUser());
        list.add(request.getRequestURI());
        list.add(request.getLocalPort());
        list.add(request.getProtocol());
        Gson gson=new Gson();
        a=gson.toJson(list);
//        a=gson.toJson(a);
        System.out.println(a);

        PrintWriter out=response.getWriter();
        out.println("<h1>"+a+"</h1>");

    }

}



