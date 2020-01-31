package com.lovecode.system.logs.aspect;

import com.lovecode.system.logs.annotation.DebugLog;
import com.lovecode.system.logs.repository.LogRepository;
import com.lovecode.system.logs.model.Log;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSON;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Aspect
@Component
@Order(100)
public class DebugLogAspect {

    @Autowired
    private LogRepository logRepository;

    private static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<>();

    private static final String START_TIME = "startTime";

    private static final String REQUEST_PARAMS = "requestParams";

    @Pointcut("execution(* com.lovecode.system.controller..*.*(..))")
    public void debugLogPointcut() {
    }

    @Before(value = "debugLogPointcut()&& @annotation(debugLog)")
    public void doBefore(JoinPoint joinPoint, DebugLog debugLog) {
        // 开始时间。
        long startTime = System.currentTimeMillis();
        Map<String, Object> threadInfo = new HashMap<>();
        threadInfo.put(START_TIME, startTime);
        // 请求参数。
        StringBuilder requestStr = new StringBuilder();
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0) {
            for (Object arg : args) {
                requestStr.append(arg.toString());
            }
        }
        threadInfo.put(REQUEST_PARAMS, requestStr.toString());
        threadLocal.set(threadInfo);
        log.info("{}接口开始调用:requestData={}", debugLog.name(), threadInfo.get(REQUEST_PARAMS));
    }

    @AfterReturning(value = "debugLogPointcut()&& @annotation(debugLog)", returning = "res")
    public void doAfterReturning(DebugLog debugLog, Object res) {
        Map<String, Object> threadInfo = threadLocal.get();
        long takeTime = System.currentTimeMillis() - (long) threadInfo.getOrDefault(START_TIME, System.currentTimeMillis());
        if (debugLog.intoDb()) {
            insertResult(debugLog.name(), (String) threadInfo.getOrDefault(REQUEST_PARAMS, ""),
                    JSON.toJSONString(res), takeTime);
        }
        threadLocal.remove();
        log.info("{}接口结束调用:耗时={}ms,result={}", debugLog.name(),
                takeTime, res);
    }

    @AfterThrowing(value = "debugLogPointcut()&& @annotation(controllerWebLog)", throwing = "throwable")
    public void doAfterThrowing(DebugLog debugLog, Throwable throwable) {
        Map<String, Object> threadInfo = threadLocal.get();
        if (debugLog.intoDb()) {
            insertError(debugLog.name(), (String) threadInfo.getOrDefault(REQUEST_PARAMS, ""),
                    throwable);
        }
        threadLocal.remove();
        log.error("{}接口调用异常，异常信息{}", debugLog.name(), throwable);
    }


    public void insertResult(String operationName, String requestStr, String responseStr, long takeTime) {
        Log log = new Log();
        log.setCreateTime(new Date());
        log.setError(false);
        log.setOperationName(operationName);
        log.setRequest(requestStr);
        log.setResponse(responseStr);
        log.setTakeTime(takeTime);
        logRepository.save(log);
    }


    public void insertError(String operationName, String requestStr, Throwable throwable) {
        Log log = new Log();
        log.setCreateTime(new Date());
        log.setError(true);
        log.setOperationName(operationName);
        log.setRequest(requestStr);
        log.setStack(throwable.getStackTrace().toString());
        logRepository.save(log);
    }
}
