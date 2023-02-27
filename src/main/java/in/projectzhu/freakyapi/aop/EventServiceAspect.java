package in.projectzhu.freakyapi.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class EventServiceAspect {

    private final Logger logger = LoggerFactory.getLogger(EventServiceAspect.class);

    @Before(value = "execution(* in.projectzhu.freakyapi.service.EventService.*(..))")
    public void beforeAdvice(JoinPoint joinPoint){
        logger.info("Before method : {}", joinPoint.getSignature());
        logger.info("Arguments are : {}", joinPoint.getArgs());
    }

}
