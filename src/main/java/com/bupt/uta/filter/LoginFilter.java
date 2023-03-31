package com.bupt.uta.filter;

import com.alibaba.fastjson.JSON;
import com.bupt.uta.common.BaseContext;
import com.bupt.uta.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebFilter(filterName = "loginFilter", urlPatterns = "/*")
public class LoginFilter implements Filter {

    public static final AntPathMatcher ANT_PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestURI = request.getRequestURI();
        String[] urls = new String[]{
                "/api/register/customer",
                "/api/register/admin",
                "/api/login/customer",
                "/api/login/admin",
                "/api/logout/customer",
                "/api/logout/admin",
                "/page/**"
        };

        //判断本次请求是否需要处理
        boolean check = this.check(urls, requestURI);

        log.info("拦截到请求：{}", requestURI);

//        if(requestURI.equals("/page/index.html")){
//            if(request.getSession().getAttribute("customer") == null){
//                response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
//                return;
//            }
//        }

        //若不需要，直接放行
        if (check) {
            log.info("请求无需登录：{}", requestURI);
            filterChain.doFilter(request, response);
            return;
        }
        //判断登陆状态，若已经登陆直接放行
        if (request.getSession().getAttribute("customer") != null) {
            log.info("用户已登录，username为：{}", request.getSession().getAttribute("customer"));

            String username = (String) request.getSession().getAttribute("customer");
            BaseContext.setCurrentUsername(username);
            long id = Thread.currentThread().getId();
            log.info("线程ID为：{}", id);
            filterChain.doFilter(request, response);
            return;
        }
        //若未登陆则返回未登陆结果
        log.info("请求未登录，不允许访问:{}", requestURI);
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        return;

    }
    public boolean check(String[] urls, String requestURI) {
        for (String url : urls) {
            boolean match = ANT_PATH_MATCHER.match(url, requestURI);
            if (match) {
                return true;
            }
        }
        return false;
    }
}
