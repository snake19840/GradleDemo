package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


@SpringBootApplication
@ServletComponentScan
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    /**
     * 注册Servlet
     * @return
     */
/*
     @Bean
    public ServletRegistrationBean getServletRegistrationBean(){
        ServletRegistrationBean bean = new ServletRegistrationBean(new FirstServlet());
        bean.addUrlMappings("/ServletTest");
        return bean;
    }
*/

    /**
     * 注册Filter
     * @return
     */

 /*   public FilterRegistrationBean getFilterRegistrationBean(){
        FilterRegistrationBean bean =new FilterRegistrationBean(new UTF_Filter());
        bean.addUrlPatterns("/second");
        return bean;
    }*/

    /**
     * 注册Listener
     */
/*
@Bean
    public ServletListenerRegistrationBean <ListenerTest> getServletListenerRegistrationBean(){
    ServletListenerRegistrationBean <ListenerTest> bean=new ServletListenerRegistrationBean <ListenerTest>(new ListenerTest());
    return bean;
}
*/

}
