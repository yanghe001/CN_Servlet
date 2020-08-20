package com.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

/**
 * 读取Cookies
 * 读取一个现有的 cookie，并把它存储在 Cookie 对象中。
 * 使用 setMaxAge() 方法设置 cookie 的年龄为零，来删除现有的 cookie。
 * 把这个 cookie 添加到响应头。
 */
@WebServlet(urlPatterns = "/readCookies")
public class ReadCookies extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie=null;
        Cookie[] cookies = null;

        // 获取与该域相关的 Cookie 的数组
        cookies=req.getCookies();

        // 设置响应内容类型
        resp.setContentType("text/html;charset=UTF-8");

        PrintWriter out = resp.getWriter();
        String title = "Delete Cookie Example";
        String docType = "<!DOCTYPE html>\n";
        out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n" );
        if( cookies != null ){
            out.println("<h2>Cookie 名称和值</h2>");
            for (int i = 0; i < cookies.length; i++){
                cookie = cookies[i];
                if((cookie.getName( )).compareTo("name") == 0 ){
                    cookie.setMaxAge(0);
                    resp.addCookie(cookie);
                    out.print("已删除的 cookie：" +
                            cookie.getName( ) + "<br/>");
                }
                out.print("名称：" + cookie.getName( ) + "，");
                out.print("值：" +  URLDecoder.decode(cookie.getValue(), "utf-8") +" <br/>");
            }
        }else{
            out.println("<h2 class=\"tutheader\">No Cookie founds</h2>");
        }
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
