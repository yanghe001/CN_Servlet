package com.test;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * 最常用
 */
@WebServlet(
        name = "servletTest3",  //等价于<servlet-name>,若没有指定，则默认是类的全限定名
        urlPatterns = "/hello"  //等价于<url-pattern>
)
public class ServletTest3 extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("初始化Servlet实例");
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // 设置响应内容类型
        resp.setContentType("text/html;charset=UTF-8");

        PrintWriter out = resp.getWriter();
        String title = "HTTP Header 请求实例 - 菜鸟教程实例";
        String docType =
                "<!DOCTYPE html> \n";
        out.println(docType +
                "<html>\n" +
                "<head><meta charset=\"utf-8\"><title>" + title + "</title></head>\n"+
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<h1 align=\"center\">" + title + "</h1>\n" +
                "<table width=\"100%\" border=\"1\" align=\"center\">\n" +
                "<tr bgcolor=\"#949494\">\n" +
                "<th>Header 名称</th><th>Header 值</th>\n"+
                "</tr>\n");

        Enumeration headerNames = req.getHeaderNames();

        while(headerNames.hasMoreElements()) {
            String paramName = (String)headerNames.nextElement();
            out.print("<tr><td>" + paramName + "</td>\n");
            String paramValue = req.getHeader(paramName);
            out.println("<td> " + paramValue + "</td></tr>\n");
        }
        out.println("</table>\n</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("进入doPost方法");
        doGet(req,resp);
    }

    @Override
    public void destroy() {
        super.destroy();
        System.out.println("销毁Servlet实例");
    }
}

