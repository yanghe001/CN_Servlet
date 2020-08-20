package com.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * 根据规范建议的各种类型的过滤器：
 * 身份验证过滤器（Authentication Filters）。
 * 数据压缩过滤器（Data compression Filters）。
 * 加密过滤器（Encryption Filters）。
 * 触发资源访问事件过滤器。
 * 图像转换过滤器（Image Conversion Filters）。
 * 日志记录和审核过滤器（Logging and Auditing Filters）。
 * MIME-TYPE 链过滤器（MIME-TYPE Chain Filters）。
 * 标记化过滤器（Tokenizing Filters）。
 * XSL/T 过滤器（XSL/T Filters），转换 XML 内容。
 */
public class LogFilter implements Filter {

    /**
     * web 应用程序启动时，web 服务器将创建Filter 的实例对象，并调用其init方法，
     * 读取web.xml配置，完成对象的初始化功能，从而为后续的用户请求作好拦截的准备工作
     * filter对象只会创建一次，init方法也只会执行一次
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 获取初始化参数
        String site=filterConfig.getInitParameter("site");

        //输出初始化参数
        System.out.println("LogFilter 网站："+site);
    }

    /**
     * 完成实际的过滤操作。匹配到URL，Servlet容器将先调用过滤器的doFilter方法。FilterChain用户访问后续过滤器。
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        System.out.println("LogFilter 过滤器处理信息中");
        // 把请求传回过滤链
        filterChain.doFilter(servletRequest,servletResponse);
    }

    /**
     * Servlet容器在销毁过滤器实例前调用该方法，在该方法中释放Servlet过滤器占用的资源。
     */
    @Override
    public void destroy() {
        System.out.println("LogFilter 销毁过滤实例");
    }
}
