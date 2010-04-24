/**
 * SerialPortSession.java
 *
 * Copyright 2010-2010 Daniel de Aguiar Kamakura
 */
package com.kamakura.communication.session;

/**
 * @author Daniel de Aguiar Kamakura
 * 
 * @since 1.0
 */
public interface SerialPortSession {

  public String read();

  public void write(String data);

}
