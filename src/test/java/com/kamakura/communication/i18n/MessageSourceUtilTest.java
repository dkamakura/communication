/**
 * MessageSourceUtilTest.java
 *
 * Copyright 2010-2010 Daniel de Aguiar Kamakura
 */
package com.kamakura.communication.i18n;

import static org.junit.Assert.assertEquals;

import java.util.Locale;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Daniel de Aguiar Kamakura
 *
 * @since 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/*.xml"})
public class MessageSourceUtilTest {
  @Test
  public void testGetMessage() {
    MessageSourceUtil.setLocale(new Locale("pt", "BR"));
    assertEquals("Erro do Teste de Localização.", MessageSourceUtil.getMessage("error.localized.test"));

    MessageSourceUtil.setLocale(new Locale("en"));
    assertEquals("Error Localized Test.", MessageSourceUtil.getMessage("error.localized.test"));
  }
}
