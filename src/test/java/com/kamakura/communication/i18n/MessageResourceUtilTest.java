/**
 * MessageResourceUtilTest.java
 *
 * Copyright 2010-2010 Daniel de Aguiar Kamakura
 */
package com.kamakura.communication.i18n;

import static org.junit.Assert.assertEquals;

import java.util.Locale;

import org.junit.Test;

import com.kamakura.communication.test.BaseSpringTestCase;

/**
 * @author Daniel de Aguiar Kamakura
 *
 * @since 1.0
 */
public class MessageResourceUtilTest extends BaseSpringTestCase {
  @Test
  public void testGetMessage() {
    MessageResourceUtil.setLocale(new Locale("pt", "BR"));
    assertEquals("Erro do Teste de Localização.", MessageResourceUtil.getMessage("error.localized.test"));

    MessageResourceUtil.setLocale(new Locale("en"));
    assertEquals("Error Localized Test.", MessageResourceUtil.getMessage("error.localized.test"));
  }
}
