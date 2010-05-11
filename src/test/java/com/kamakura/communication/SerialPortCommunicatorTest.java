/**
 * SerialPortCommunicatorTest.java
 *
 * Copyright 2010-2010 Daniel de Aguiar Kamakura
 */
package com.kamakura.communication;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Daniel de Aguiar Kamakura
 *
 * @since 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/*.xml"})
public class SerialPortCommunicatorTest {

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
