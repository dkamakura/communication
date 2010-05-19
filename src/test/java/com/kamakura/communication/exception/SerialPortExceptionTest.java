/**
 * SerialPortExceptionTest.java
 *
 * Copyright 2010-2010 Daniel de Aguiar Kamakura
 */
package com.kamakura.communication.exception;

import static org.junit.Assert.assertEquals;

import java.util.Locale;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kamakura.communication.i18n.MessageResourceUtil;

/**
 * @author Daniel de Aguiar Kamakura
 *
 * @since 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/*.xml"})
public class SerialPortExceptionTest {
  @Test
  public void testLocalizedMessagePtBR() {
    MessageResourceUtil.setLocale(new Locale("pt", "BR"));

    try {
      throw new SerialPortException("error.localized.test");
    } catch(SerialPortException ex) {
      assertEquals("Erro do Teste de Localização.", ex.getLocalizedMessage());
      assertEquals("error.localized.test", ex.getMessage());
    }
  }

  @Test
  public void testLocalizedMessageEn() {
    MessageResourceUtil.setLocale(new Locale("en"));

    try {
      throw new SerialPortException("error.localized.test");
    } catch(SerialPortException ex) {
      assertEquals("Error Localized Test.", ex.getLocalizedMessage());
      assertEquals("error.localized.test", ex.getMessage());
    }
  }

  @Test
  public void testParameterizedLocalizedMessagePtBR() {
    MessageResourceUtil.setLocale(new Locale("pt", "BR"));
    try {
      Object[] params = {"Parametrizado"};
      throw new SerialPortException("error.parameterized.localized.test", params);
    } catch(SerialPortException ex) {
      assertEquals("Erro do Teste de Localização Parametrizado.", ex.getLocalizedMessage());
      assertEquals("error.parameterized.localized.test", ex.getMessage());
    }
  }

  @Test
  public void testParameterizedLocalizedMessageEn() {
    MessageResourceUtil.setLocale(new Locale("en"));
    try {
      Object[] params = {"Parameterized"};
      throw new SerialPortException("error.parameterized.localized.test", params);
    } catch(SerialPortException ex) {
      assertEquals("Error Parameterized Localized Test.", ex.getLocalizedMessage());
      assertEquals("error.parameterized.localized.test", ex.getMessage());
    }
  }
}
