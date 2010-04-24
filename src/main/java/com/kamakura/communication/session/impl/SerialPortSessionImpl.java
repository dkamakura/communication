/**
 * SerialPortSessionImpl.java
 *
 * Copyright 2010-2010 Daniel de Aguiar Kamakura
 */
package com.kamakura.communication.session.impl;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import org.apache.log4j.Logger;

import com.kamakura.communication.session.SerialPortSession;

/**
 * @author Daniel de Aguiar Kamakura
 * 
 * @since 1.0
 */
public class SerialPortSessionImpl implements SerialPortSession {
  private Logger logger = Logger.getLogger(SerialPortSessionImpl.class);

  private Reader in;

  private Writer out;

  public SerialPortSessionImpl(Reader in, Writer out) {
    this.in = in;
    this.out = out;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.kamakura.communication.SerialPortSession#read()
   */
  @Override
  public String read() {
    logger.debug("Starting reading data.");
    StringBuilder builder = new StringBuilder();
    try {
      char[] data = new char[1];
      while (in.ready()) {
        in.read(data);
        builder.append(data);
      }
    } catch (IOException ex) {
      throw new RuntimeException("error.reading.data", ex);
    }
    logger.debug("Finished reading data.");
    return builder.toString();
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.kamakura.communication.SerialPortSession#write(java.lang.String)
   */
  @Override
  public void write(String data) {
    logger.debug("Starting writing data.");
    try {
      out.write(data);
      out.flush();
    } catch (IOException ex) {
      throw new RuntimeException("error.writing.data", ex);
    }
    logger.debug("Finished writing data.");
  }
}
