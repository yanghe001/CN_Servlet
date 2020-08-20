package com.db;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(urlPatterns = "/DatabaseAccess")
public class DatabaseAccess extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // JDBC 驱动名及数据库 相关信息
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/RUNOOB";
    static final String USER = "root";
    static final String PASS = "123456";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection con=null;
        Statement stmt=null;
        // 设置响应内容类型
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        String title = "Mysql 测试";
        String docType = "<!DOCTYPE html>\n";
        out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<h1 align=\"center\">" + title + "</h1>\n" +
                "<table border=\"1\" align=\"center\">\n" +
                "<tr bgcolor=\"#949494\">\n" +
                "<td>ID</td>" +
                "<td>站点名称</td>" +
                "<td>站点 URL</td>" +
                "<td>站点 country</td>" +
                "<td>站点 alexa</td>" +
                "</tr>");
        try{
            Class.forName("com.mysql.jdbc.Driver");
            //打开一个 链接
            con= DriverManager.getConnection(DB_URL,USER,PASS);

            //执行SQL查询
            stmt=con.createStatement();
            String sql="select * from websites";
            //获取数据集
            ResultSet rs=stmt.executeQuery(sql);

            while(rs.next()){
                out.println("" +
                        "<tr>\n" +
                        "<td>"+rs.getInt("id")+"</td>" +
                        "<td>"+rs.getString("name")+"</td>" +
                        "<td>"+rs.getString("url")+"</td>" +
                        "<td>"+rs.getString("country")+"</td>" +
                        "<td>"+rs.getInt("alexa")+"</td>" +
                        "</tr>"+
                        "");
            }
            out.println("</table></body></html>");
            //关闭数据库相关链接
            rs.close();
            stmt.close();
            con.close();
        }catch(SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch(Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 最后是用于关闭资源的块
            try{
                if(stmt!=null)
                    stmt.close();
                if(con!=null)
                    con.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
