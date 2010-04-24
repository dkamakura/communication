/**
 * SerialPortCommunicatorImpl.java
 *
 * Copyright 2010-2010 Daniel de Aguiar Kamakura
 */
package com.kamakura.communication.impl;

import org.springframework.stereotype.Component;

import com.kamakura.communication.SerialPortCommunicator;
import com.kamakura.communication.annotation.Communicate;
import com.kamakura.communication.session.SerialPortSession;

/**
 * @author Daniel de Aguiar Kamakura
 *
 * @since 1.0
 */

@Component
public class SerialPortCommunicatorImpl implements SerialPortCommunicator {

  private SerialPortSession serialPortSession;
  
  /* (non-Javadoc)
   * @see com.kamakura.communication.dao.impl.SerialPortDao#teste()
   */
  @Communicate
  public String read() {
    return serialPortSession.read();
  }
  
  @Communicate
  public void write(String data) {
    serialPortSession.write(data);
  }

  public void setSerialPortSession(SerialPortSession serialPortSession) {
    this.serialPortSession = serialPortSession;
  }
}
