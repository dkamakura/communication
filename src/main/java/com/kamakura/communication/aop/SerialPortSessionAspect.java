/**
 * SerialPortSessionAdvice.java
 *
 * Copyright 2010-2010 Daniel de Aguiar Kamakura
 */
package com.kamakura.communication.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kamakura.communication.SerialPortCommunicator;
import com.kamakura.communication.session.SerialPortSessionFactory;

/**
 * @author Daniel de Aguiar Kamakura
 *
 * @since 1.0
 */
@Component
@Aspect
public class SerialPortSessionAspect {
  Logger logger = Logger.getLogger(SerialPortSessionAspect.class);
  
  @Autowired
  private SerialPortSessionFactory serialPortSessionFactory;
  
  @Around("execution(@com.kamakura.communication.annotation.Communicate * *(..))")
  public Object initializeSerialPortSession(ProceedingJoinPoint pjp) throws Throwable {
    try {
      String methodName = pjp.getSignature().getName();
      logger.debug("Executing SerialPortSessionAspect in method " + methodName);
      SerialPortCommunicator serialPortCommunicator = (SerialPortCommunicator)pjp.getTarget();
      serialPortCommunicator.setSerialPortSession(serialPortSessionFactory.openSession());
      Object returnValue = pjp.proceed();
      logger.debug("Exiting SerialPortSessionAspect in method " + methodName);
      return returnValue;
    } finally {
      serialPortSessionFactory.closeSession();
    }
  }

  public void setSerialPortSessionFactory(SerialPortSessionFactory serialPortSessionFactory) {
    this.serialPortSessionFactory = serialPortSessionFactory;
  }
}
