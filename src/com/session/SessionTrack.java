package com.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * HttpSession 接口，该接口提供了一种跨多个页面请求或访问网站时识别用户以及存储有关用户信息的方式。
 *
 */
@WebServlet(urlPatterns = "/SessionTrack")
public class SessionTrack extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //如果不存在session会话，则创建一个session对象
        HttpSession session=req.getSession(true);
        //获取 session 创建时间
        Date createTime=new Date(session.getCreationTime());
        //获取该网页的最后一次访问时间
        Date lastAccessTime=new Date(session.getLastAccessedTime());
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Integer visitCount = new Integer(0);
        String visitCountKey = new String("visitCount");
        String userIDKey = new String("userID");
        String userID = new String("0000007");

        if(session.getAttribute(visitCountKey)==null){
            session.setAttribute(visitCountKey, new Integer(0));
        }

        // 检查网页上是否有新的访问者
        if(session.isNew()){
            session.setAttribute(userIDKey,userID);
        }else{
            visitCount = (Integer)session.getAttribute(visitCountKey);
            visitCount = visitCount + 1;
            userID = (String)session.getAttribute(userIDKey);
        }
        session.setAttribute(visitCountKey,visitCount);

        // 设置响应内容类型
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        String docType = "<!DOCTYPE html>\n";
        out.println(docType +
                "<html>\n" +
                "<head><title>Session演示</title></head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<h1 align=\"center\">Session演示</h1>\n" +
                "<h2 align=\"center\">Session 信息</h2>\n" +
                "<table border=\"1\" align=\"center\">\n" +
                "<tr bgcolor=\"#949494\">\n" +
                "  <th>Session 信息</th><th>值</th></tr>\n" +
                "<tr>\n" +
                "  <td>id</td>\n" +
                "  <td>" + session.getId() + "</td></tr>\n" +
                "<tr>\n" +
                "  <td>创建时间</td>\n" +
                "  <td>" +  df.format(createTime) +
                "  </td></tr>\n" +
                "<tr>\n" +
                "  <td>最后访问时间</td>\n" +
                "  <td>" + df.format(lastAccessTime) +
                "  </td></tr>\n" +
                "<tr>\n" +
                "  <td>用户 ID</td>\n" +
                "  <td>" + userID +
                "  </td></tr>\n" +
                "<tr>\n" +
                "  <td>访问统计：</td>\n" +
                "  <td>" + visitCount + "</td></tr>\n" +
                "</table>\n" +
                "</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}

