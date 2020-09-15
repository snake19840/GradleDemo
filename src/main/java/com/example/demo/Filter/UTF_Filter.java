package com.example.demo.Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.awt.*;
import java.io.IOException;


@WebFilter(filterName = "UTF_Filter",urlPatterns = {"/*"})
public class UTF_Filter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("进入Filter");
       resp.setContentType("Text/html");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
//        System.out.println("pd:"+ req.getParameter("a").equals("你"));
//        if (req.getParameter("a").equals("你")){
//
//        }

//        resp.getWriter().print("wwqwqwwwq");
        chain.doFilter(req,resp);
//        System.out.println("离开Filter");
    }


    public void init(FilterConfig config) throws ServletException {

    }

}
