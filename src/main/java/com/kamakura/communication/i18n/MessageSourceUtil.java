/**
 * MessageSourceUtil.java
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
public class MessageSourceUtil {

  private static ResourceBundleMessageSource messageSource; 

  @Autowired(required=true)
  public void setMessageSource(ResourceBundleMessageSource messageSource) {
    MessageSourceUtil.messageSource = messageSource;
  }

  public static void setLocale(Locale locale) {
    Locale.setDefault(locale);
  }

  private static Locale getLocale() {
    return Locale.getDefault();
  }
  
  public static String getMessage(String message) {
    return getMessage(message, null);
  }
  
  public static String getMessage(String message, Object[] params) {
    return messageSource.getMessage(message, params, getLocale());
  }
  
}
