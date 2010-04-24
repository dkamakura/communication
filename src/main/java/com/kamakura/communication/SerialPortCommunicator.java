/**
 * SerialPortCommunicator.java
 *
 * Copyright 2010-2010 Daniel de Aguiar Kamakura
 */
package com.kamakura.communication;

import com.kamakura.communication.session.SerialPortSession;

/**
 * @author Daniel de Aguiar Kamakura
 *
 * @since 1.0
 */
public interface SerialPortCommunicator {

  public void setSerialPortSession(SerialPortSession serialPortSession);
  
  public String read();
  
  public void write(String value);

}