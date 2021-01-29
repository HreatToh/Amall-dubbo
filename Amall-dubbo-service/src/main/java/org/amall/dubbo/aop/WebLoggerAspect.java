package org.amall.dubbo.aop;

import cn.hutool.core.util.ObjectUtil;
import com.google.gson.Gson;
import org.amall.dubbo.annotation.WebLogger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

@Aspect
@Component
public class WebLoggerAspect {

    private Logger logger = null;

    private String value = "";

    /**
     * 打印log方法
     */
    @Pointcut("@annotation(org.amall.dubbo.annotation.WebLogger)")
    public void log(){}


    /**
     * 初始化日志对象
     * @param joinPoint
     */
    public void init(JoinPoint joinPoint){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        WebLogger webLogger = method.getAnnotation(WebLogger.class);
        this.logger = LogManager.getLogger(joinPoint.getTarget().getClass());
        this.value = webLogger.value();
    }
    /**
     * 环绕通知
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("log()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        init(joinPoint);

        long startTime=System.currentTimeMillis();

        Object result=joinPoint.proceed();
        logger.info("Response:{}",new Gson().toJson(result));
        logger.info("耗时：{}",System.currentTimeMillis()-startTime);

        return result;
    }


    /**
     * 前置通知：
     * 1. 在执行目标方法之前执行，比如请求接口之前的登录验证;
     * 2. 在前置通知中设置请求日志信息，如开始时间，请求参数，注解内容等
     *
     * @param joinPoint
     * @throws Throwable
     */
    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        init(joinPoint);
        logger.info("=================Start=================");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (ObjectUtil.isNotNull(attributes)){
            HttpServletRequest request = attributes.getRequest();

            logger.info("URL:{}",request.getRequestURL().toString());
            logger.info("Description:{}",this.value);
            logger.info("Method:{}",request.getMethod().toString());

            //打印controller全路径及method
            logger.info("Class Method：{},{}",joinPoint.getSignature().getDeclaringTypeName(),joinPoint.getSignature().getName());
            logger.info("客户端IP：{}" , request.getRemoteAddr());
            logger.info("请求参数：{}" , new Gson().toJson(joinPoint.getArgs()));
        }else{
            logger.info("Description：{}",this.value);
            //打印controller全路径及method
            logger.info("Class：{}",joinPoint.getTarget().getClass());
            logger.info("Method：{}",joinPoint.getSignature().getName());
            logger.info("请求参数：{}" , new Gson().toJson(joinPoint.getArgs()));
        }

    }


    /**
     * 返回通知：
     * 1. 在目标方法正常结束之后执行
     * 1. 在返回通知中补充请求日志信息，如返回时间，方法耗时，返回值，并且保存日志信息
     *
     * @throws Throwable
     */
    @After("log()")
    public void doAfter() {
        if (ObjectUtil.isNull(logger)){
            logger = LogManager.getLogger(this.getClass());
        }
        logger.info("==================End==================");
    }

    /**
     * 异常通知：
     * 1. 在目标方法非正常结束，发生异常或者抛出异常时执行
     * 1. 在异常通知中设置异常信息，并将其保存
     *
     * @param throwable
     */
    @AfterThrowing(value = "log()", throwing = "throwable")
    public void doAfterThrowing(JoinPoint joinPoint,Throwable throwable) {
        init(joinPoint);
        // 保存异常日志记录
        logger.error("发生异常时间：{}" + LocalDateTime.now());
        logger.error("抛出异常：{}" + throwable.getMessage());
    }
}
