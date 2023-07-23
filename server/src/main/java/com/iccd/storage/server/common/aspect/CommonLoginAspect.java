package com.iccd.storage.server.common.aspect;

import com.iccd.storage.cache.core.constant.CacheConstants;
import com.iccd.storage.server.common.annotation.LoginIgnore;
import com.iccd.storage.server.common.utils.UserIdUtil;
import com.iccd.storage.core.response.R;
import com.iccd.storage.core.response.ResponseCode;
import com.iccd.storage.core.utils.JwtUtil;
import com.iccd.storage.server.modules.user.constants.UserConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Objects;

@Component
@Aspect
@Slf4j
public class CommonLoginAspect {
    private static final String LOGIN_AUTH_PARAM_NAME = "authorization";
    private static final String LOGIN_AUTH_REQUEST_HEADER_NAME = "Authorization";

    private final static String POINT_CUT = "execution(* com.iccd.storage.server.modules.*.controller..*(..))";

    @Autowired
    private CacheManager cacheManager;

    @Pointcut(value = POINT_CUT)
    public void loginAuth() {

    }

    /**
     * 1. 判断需不需要校验登录信息
     * 2. 校验登录信息
     * a. 从请求头或者参数中获取token
     * b. 从缓存中获取token进行比对
     * c. 解析token
     * d. 解析userId存入线程上下文，供下游使用
     *
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("loginAuth()")
    public Object loginAuthAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        if (checkNeedCheckLoginInfo(proceedingJoinPoint)) {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = servletRequestAttributes.getRequest();
            String requestURI = request.getRequestURI();
            log.info("成功拦截到请求，URI为：{}", requestURI);
            if (!checkAndSaveUserId(request)) {
                log.warn("成功拦截到请求，URI为：{}. 检测到用户未登录，将跳转至登录页面", requestURI);
                return R.fail(ResponseCode.NEED_LOGIN);
            }
            log.info("成功拦截到请求，URI为：{}，请求通过", requestURI);
        }
        return proceedingJoinPoint.proceed();
    }

    /**
     * 校验accessToken并提取UserID
     *
     * @param request
     * @return
     */
    private boolean checkAndSaveUserId(HttpServletRequest request) {
        String accessToken = request.getHeader(LOGIN_AUTH_REQUEST_HEADER_NAME);

        if (StringUtils.isBlank(accessToken)) {
            accessToken = request.getParameter(LOGIN_AUTH_PARAM_NAME);
        }
        if (StringUtils.isBlank(accessToken)) {
            return false;
        }

        Long userId = (Long) JwtUtil.analyzeToken(accessToken, UserConstants.LOGIN_USER_ID);
        Cache cache = cacheManager.getCache(CacheConstants.ICCD_STORAGE_CACHE_NAME);

        String cachedAccessToken = cache.get(UserConstants.USER_LOGIN_PREFIX + userId, String.class);

        if (!StringUtils.equals(accessToken, cachedAccessToken)) {
            return false;
        }

        UserIdUtil.set(userId);

        return true;
    }

    /**
     * 校验登录信息
     *
     * @param proceedingJoinPoint
     * @return
     */
    private boolean checkNeedCheckLoginInfo(ProceedingJoinPoint proceedingJoinPoint) {
        Signature signature = proceedingJoinPoint.getSignature();
        MethodSignature methodSignature = ((MethodSignature) signature);
        Method method = methodSignature.getMethod();


        return !method.isAnnotationPresent(LoginIgnore.class);
    }
}
