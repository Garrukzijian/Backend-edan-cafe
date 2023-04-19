package com.whut.springbootshiro.aspect;

import cn.hutool.json.JSONUtil;

import com.whut.springbootshiro.annotation.MyLog;
import com.whut.springbootshiro.mapper.SysLogMapper;
import com.whut.springbootshiro.model.SysLog;
import com.whut.springbootshiro.shiro.ActiveUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author Lei
 * aop的切面类，主要是设置这个切面的位置写在这个地方设置这个其他的地方
 * @create 2021-08-19 23:19
 */
@Aspect
@Component
public class SysLogAspect {

    @Resource
    private SysLogMapper sysLogMapper;


    //定义切点 @Pointcut
    //在注解的位置切入代码
    @Pointcut("@annotation(com.whut.springbootshiro.annotation.MyLog)")
    public void logPoinCut() {
    }

    /**
     * @Author Lei
     * @Description 这个是执行的是在退出执行方法之前进行执行的，然后进行设置的
     * @Date 20:49 2021/9/6
     * @Param [joinPoint]
     * @return void
     **/
    @Before("execution(* com.whut.springbootshiro.controller.SysUserController.logout(..))")
    public void saveLogOutLog(JoinPoint joinPoint){
        //设置这个日志的接口
        SysLog sysLog = new SysLog();
        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();
        //设置这个操作的方法名称
        sysLog.setOperation("用户退出");
        //获取请求的类名
        String className = joinPoint.getTarget().getClass().getName();
        //获取请求的方法名
        String methodName = method.getName();
        sysLog.setMethod(className + "." + methodName);
        //请求的参数
        Object[] args = joinPoint.getArgs();
        //将参数所在的数组转换成json
        String params = JSONUtil.toJsonStr(args);
        sysLog.setParams(params);
        sysLog.setCreatedate(new Date());
        //获取用户名
        Subject subject = SecurityUtils.getSubject();
        ActiveUser principal = (ActiveUser) subject.getPrincipal();
        sysLog.setRealname(principal.getRealname());
        sysLog.setLoginname(principal.getSysUser().getLoginName());
        sysLog.setIp(subject.getSession().getHost());
        sysLogMapper.insertSelective(sysLog);
    }

    @Before("execution(* com.whut.springbootshiro.controller.SysUserController.updatePassword(..))")
    public void savePasswordLog(JoinPoint joinPoint){
        //设置这个日志的接口
        SysLog sysLog = new SysLog();
        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();
        //设置这个操作的方法名称
        sysLog.setOperation("用户退出");
        //获取请求的类名
        String className = joinPoint.getTarget().getClass().getName();
        //获取请求的方法名
        String methodName = method.getName();
        sysLog.setMethod(className + "." + methodName);
        //请求的参数
        Object[] args = joinPoint.getArgs();
        //将参数所在的数组转换成json
        String params = JSONUtil.toJsonStr(args);
        sysLog.setParams(params);
        sysLog.setCreatedate(new Date());
        //获取用户名
        Subject subject = SecurityUtils.getSubject();
        ActiveUser principal = (ActiveUser) subject.getPrincipal();
        sysLog.setRealname(principal.getRealname());
        sysLog.setLoginname(principal.getSysUser().getLoginName());
        sysLog.setIp(subject.getSession().getHost());
        sysLogMapper.insertSelective(sysLog);
    }


    /**
     * @Author Lei
     * @Description 执行这个接入点之后的方法，然后进行执行方法
     * @Date 20:50 2021/9/6
     * @Param [joinPoint]
     * @return void
     **/
    @AfterReturning("logPoinCut()")
    public void saveSysLog(JoinPoint joinPoint) {
        //生成一个实体
        SysLog sysLog = new SysLog();
        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();

        //获取操作
        MyLog myLog = method.getAnnotation(MyLog.class);
        if (myLog != null) {
            String value = myLog.value();
            sysLog.setOperation(value);//保存获取的操作
        }

        //获取请求的类名
        String className = joinPoint.getTarget().getClass().getName();
        //获取请求的方法名
        String methodName = method.getName();
        sysLog.setMethod(className + "." + methodName);

        //请求的参数
        Object[] args = joinPoint.getArgs();
        //将参数所在的数组转换成json
        String params = JSONUtil.toJsonStr(args);
        sysLog.setParams(params);
        sysLog.setCreatedate(new Date());
        //获取用户名
        Subject subject = SecurityUtils.getSubject();
        ActiveUser principal = (ActiveUser) subject.getPrincipal();
        sysLog.setRealname(principal.getRealname());
        sysLog.setLoginname(principal.getSysUser().getLoginName());
        sysLog.setIp(subject.getSession().getHost());
        sysLogMapper.insertSelective(sysLog);
    }
}