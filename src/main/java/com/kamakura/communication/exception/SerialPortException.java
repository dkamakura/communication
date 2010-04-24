/**
 * SerialPortException.java
 *
 * Copyright 2010-2010 Daniel de Aguiar Kamakura
 */
package com.kamakura.communication.exception;

import com.kamakura.communication.i18n.MessageResourceUtil;

/**
 * @author Daniel de Aguiar Kamakura
 *
 * @since 1.0
 */
public class SerialPortException extends RuntimeException {
  /**
   * 
   */
  private static final long serialVersionUID = 5847508983130618065L;
  
  private Object[] params;

  public SerialPortException(String message) {
    super(message);
  }

  public SerialPortException(String message, Throwable cause) {
    super(message, cause);
  }

  public SerialPortException(String message, Object[] params) {
    super(message);
    this.params = params;
  }

  public SerialPortException(String message, Object[] params, Throwable cause) {
    super(message, cause);
    this.params = params;
  }

  @Override
  public String getLocalizedMessage() {
    return MessageResourceUtil.getMessage(this.getMessage(), params);
  }
}
