//package org.company.resume.filter;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebFilter(filterName = "JSPFileFilter", urlPatterns = {"*.jsp"})
//public class JspFilter implements Filter {
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
//        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
//        try {
//            httpServletResponse.sendRedirect("error?msg=not found");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//}
