package com.iccd.storage.server.common.aspect;

import com.iccd.storage.cache.core.constant.CacheConstants;
import com.iccd.storage.core.response.R;
import com.iccd.storage.core.response.ResponseCode;
import com.iccd.storage.core.utils.JwtUtil;
import com.iccd.storage.server.common.utils.ShareIdUtil;
import com.iccd.storage.server.common.utils.UserIdUtil;
import com.iccd.storage.server.modules.share.constants.ShareConstants;
import com.iccd.storage.server.modules.user.constants.UserConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
@Slf4j
public class ShareCodeAspect {

    private static final String SHARE_CODE_AUTH_PARAM_NAME = "shareToken";
    private static final String SHARE_CODE_REQUEST_HEADER_NAME = "Share-Token";

    private final static String POINT_CUT = "@annotation(com.iccd.storage.server.common.annotation.NeedShareCode)";

    @Pointcut(value = POINT_CUT)
    public void shareCodeAuth() {

    }


    @Around("shareCodeAuth()")
    public Object shareCodeAuthAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String requestURI = request.getRequestURI();
        log.info("成功拦截到请求，URI为：{}", requestURI);
        if (!checkAndSaveShareId(request)) {
            log.warn("成功拦截到请求，URI为：{}. 检测到用户无分享码，将跳转至分享码页面", requestURI);
            return R.fail(ResponseCode.NEED_LOGIN);
        }
        log.info("成功拦截到请求，URI为：{}，请求通过", requestURI);

        return proceedingJoinPoint.proceed();
    }

    private boolean checkAndSaveShareId(HttpServletRequest request) {
        String shareToken = request.getHeader(SHARE_CODE_REQUEST_HEADER_NAME);

        if (StringUtils.isBlank(shareToken)) {
            shareToken = request.getParameter(SHARE_CODE_AUTH_PARAM_NAME);
        }
        if (StringUtils.isBlank(shareToken)) {
            return false;
        }

        Long shareId = (Long) JwtUtil.analyzeToken(shareToken, ShareConstants.SHARE_ID);


        ShareIdUtil.set(shareId);

        return true;
    }
}
