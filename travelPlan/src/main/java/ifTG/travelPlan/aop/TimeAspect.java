package ifTG.travelPlan.aop;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;

@Slf4j
@Aspect
@Component
public class TimeAspect {

    @Around("@annotation(Time)")
    public Object timeLog(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.nanoTime();
        log.info("start : {} - {} START", pjp.getTarget(), pjp.getSignature().getName());
        try{
            return pjp.proceed();
        }finally {
            long end = System.nanoTime();
            log.info("end : {} - {} END, {} ms", pjp.getTarget(), pjp.getSignature().getName(), (end-start)/1_000_000);
        }

    }
}
