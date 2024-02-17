package ifTG.travelPlan.aop;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class StartFunctionLogAspect {

    @Before("@annotation(StartFunctionLog)")
    public void startFunctionLog(JoinPoint jp){
        log.info("{} <{}> start at {}", jp.getTarget().getClass(), jp.getSignature().getName(), LocalDateTime.now());
    }

}
