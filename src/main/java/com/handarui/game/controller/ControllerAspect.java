package com.handarui.game.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.handarui.game.biz.bean.ResultEnum;
import com.zhexinit.ov.common.bean.RequestBean;
import com.zhexinit.ov.common.bean.ResponseBean;
import com.zhexinit.ov.common.exception.CommonException;
import com.zhexinit.ov.common.util.ResponseUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class ControllerAspect {

    private Logger log = LoggerFactory.getLogger(getClass());

    // 申明一个切点 里面是 execution表达式
    @Pointcut("execution(public com.zhexinit.ov.common.bean.ResponseBean com.handarui.game.controller..*.*(..))")
    private void controllerAspect() {
    }

    // 请求method前打印内容
    @Around(value = "controllerAspect()")
    public ResponseBean methodBefore(ProceedingJoinPoint joinPoint) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        StringBuilder sb = new StringBuilder();
        long beginTime = System.currentTimeMillis();
        // 打印请求内容
        sb.append("\n请求地址：").append(request.getRequestURL().toString()).append("\n");
        sb.append("请求方式：").append(request.getMethod()).append("\n");
        sb.append("请求类方法：").append(joinPoint.getSignature().toString()).append("\n");
        Object[] args = joinPoint.getArgs();
        sb.append("请求类方法参数：").append(JSONArray.toJSONString(args)).append("\n");
        RequestBean requestBean = new RequestBean();
        for (Object arg : args) {
            if (arg instanceof RequestBean) {
                requestBean = (RequestBean) arg;
                break;
            }
        }
        ResponseBean responseBean = null;
        try {
            Object result = joinPoint.proceed();
            if (result instanceof ResponseBean) {
                responseBean = (ResponseBean) result;
                responseBean.setReqId(requestBean.getReqId());
            }
        } catch (Throwable throwable) {
            if (throwable instanceof CommonException) {
                responseBean = ResponseUtil.fail(requestBean.getReqId(), (CommonException) throwable);
            } else {
                log.error("异常捕获：", throwable);
                responseBean = ResponseUtil.fail(requestBean.getReqId(), ResultEnum.SystemError);
            }
        }
        long endTime = System.currentTimeMillis();
        sb.append("耗时：").append(endTime - beginTime).append("\n");
        sb.append("返回结果：").append(JSON.toJSONString(responseBean));
        log.info(sb.toString());
        return responseBean;
    }
}
