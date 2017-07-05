package com.boxfish.tsp.filters;

import com.boxfish.tsp.common.exception.InvalidInputResponseBuilder;
import com.boxfish.tsp.permission.UserInfo;
import com.boxfish.tsp.permission.UserInfoValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.util.StringUtils.isEmpty;

@WebFilter(urlPatterns = {"/tsp-service/ts/*"})
public class AuthorFilter extends OncePerRequestFilter {

    private Logger logger = LoggerFactory.getLogger(AuthorFilter.class);

    @Autowired
    private UserInfoValidator userInfoValidator;
    public static final String ACCESS_CONTROL_ALLOW_ORIGIN = "Access-Control-Allow-Origin";
    public static final String ACCESS_CONTROL_ALLOW_METHODS = "Access-Control-Allow-Methods";
    public static final String ACCESS_CONTROL_ALLOW_HEADERS = "Access-Control-Allow-Headers";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        response.addHeader(ACCESS_CONTROL_ALLOW_METHODS, "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
        response.addHeader(ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        response.addHeader(ACCESS_CONTROL_ALLOW_HEADERS, "x-be-product,Content-Type");
        //fishcard/*获取"access_token"
        //students/*获取"BoxFishAccessToken"
        //没有token的直接返回
        //@Pay Attention Please!!! 其他的路径拦截,请在这里加上判断
        String accessToken = "";
        if (request.getRequestURI().contains("fishcard")) {
            accessToken = request.getParameter("access_token");
        } else if (request.getRequestURI().contains("students")) {
            accessToken = request.getParameter("BoxFishAccessToken");
        }

        if (isEmpty(accessToken)) {
            logger.info("@checkToken - 非法登陆");
            InvalidInputResponseBuilder.unauthorized(response, "非法登陆,传入参数不能为空.");
            return;
        }

        //校验access_token
        UserInfo userInfo = this.userInfoValidator.checkToken(accessToken);
        if (null == userInfo || "".equals(userInfo)) {
            InvalidInputResponseBuilder.unauthorized(response, "无效的token,请勿越权使用.");
            return;
        }

        //需要把用户信息放进request
        request.setAttribute("userInfo", userInfo);
        filterChain.doFilter(request, response);
    }

}
