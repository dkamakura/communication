/**
 * SerialPortCommunicatorTest.java
 *
 * Copyright 2010-2010 Daniel de Aguiar Kamakura
 */
package com.kamakura.communication;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.kamakura.communication.test.BaseSpringTestCase;

/**
 * @author Daniel de Aguiar Kamakura
 *
 * @since 1.0
 */
public class SerialPortCommunicatorTest extends BaseSpringTestCase {

  @Autowired
  private SerialPortCommunicator serialPortCommunicator;
  
  @Test
  public void testWrite() {
    serialPortCommunicator.write("Test");
  }
  
  @Test
  public void testRead() {
    assertEquals("", serialPortCommunicator.read());
  }
  
}
