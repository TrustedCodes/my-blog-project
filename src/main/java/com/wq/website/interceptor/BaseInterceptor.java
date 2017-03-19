package com.wq.website.interceptor;

import com.wq.website.constant.WebConst;
import com.wq.website.dto.Types;
import com.wq.website.modal.Vo.UserVo;
import com.wq.website.service.IUserService;
import com.wq.website.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义拦截器
 * Created by BlueT on 2017/3/9.
 */
@Component
public class BaseInterceptor implements HandlerInterceptor {
    private static final Logger LOGGE = LoggerFactory.getLogger(BaseInterceptor.class);
    private static final String USER_AGENT = "user-agent";

    @Resource
    private IUserService userService;

    private MapCache cache = MapCache.single();

    @Resource
    private Commons commons;

    @Resource
    private AdminCommons adminCommons;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String uri = request.getRequestURI();

        LOGGE.info("UserAgent: {}", request.getHeader(USER_AGENT));
        LOGGE.info("用户访问地址: {}, 来路地址: {}", uri, IPKit.getIpAddrByRequest(request));

//        排除拦截的地址
        if (!WebConst.INSTALL && !uri.startsWith("/install")) {
            response.sendRedirect(request.getServletContext().getContextPath() + "/install");
            return false;
        }

        if (WebConst.INSTALL) {
            UserVo user = TaleUtils.getLoginUser(request);
            if (null == user) {
                Integer uid = TaleUtils.getCookieUid(request);
                if (null != uid) {
                    user = userService.queryUserById(uid);
                    request.getSession().setAttribute(WebConst.LOGIN_SESSION_KEY, user);
                }
            }
            if (uri.startsWith("/admin") && !uri.startsWith("/admin/login") && null == user) {
                response.sendRedirect(request.getContextPath()+"/admin/login");
                return false;
            }
        }
//        设置get请求的token
        if (request.getMethod().equals("GET")) {
            String csrf_token = UUID.UU64();
            // 默认存储30分钟
            cache.hset(Types.CSRF_TOKEN.getType(), csrf_token, uri, 30 * 60);
            request.setAttribute("_csrf_token", csrf_token);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        httpServletRequest.setAttribute("commons",commons);
        httpServletRequest.setAttribute("adminCommons",adminCommons);
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
