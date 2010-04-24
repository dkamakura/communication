/**
 * MessageResourceUtil.java
 *
 * Copyright 2010-2010 Daniel de Aguiar Kamakura
 */
package com.kamakura.communication.i18n;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

/**
 * @author Daniel de Aguiar Kamakura
 * 
 * @since 1.0
 */

@Component
public class MessageResourceUtil {

  private static ThreadLocal<Locale> threadLocalLocale = new ThreadLocal<Locale>();
  
  private static ResourceBundleMessageSource messageSource; 

  @Autowired(required=true)
  public void setMessageSource(ResourceBundleMessageSource messageSource) {
    MessageResourceUtil.messageSource = messageSource;
  }

  public static void setLocale(Locale locale) {
    threadLocalLocale.set(locale);
  }

  private static Locale getLocale() {
    Locale locale = threadLocalLocale.get();
    if(locale == null) {
      locale = Locale.getDefault();
    }
    return locale;
  }
  
  public static String getMessage(String message) {
    return getMessage(message, null);
  }
  
  public static String getMessage(String message, Object[] params) {
    return messageSource.getMessage(message, params, getLocale());
  }
  
  public static void removeLocale() {
    threadLocalLocale.remove();
  }
}
