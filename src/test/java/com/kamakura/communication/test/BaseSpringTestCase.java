/**
 * BaseSpringTestCase.java
 *
 * Copyright 2010-2010 Daniel de Aguiar Kamakura
 */

package com.kamakura.communication.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * @author Daniel de Aguiar Kamakura
 *
 * @since 1.0
 */
@ContextConfiguration(locations = {"classpath:/spring/*.xml"})
public class BaseSpringTestCase extends AbstractJUnit4SpringContextTests {
  @Test
  public void testApplicationContext() {
    assertNotNull(applicationContext);
  }
}
