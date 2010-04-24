/**
 * SerialPortSessionFactory.java
 *
 * Copyright 2010-2010 Daniel de Aguiar Kamakura
 */
package com.kamakura.communication.session;


/**
 * @author Daniel de Aguiar Kamakura
 *
 * @since 1.0
 */
public interface SerialPortSessionFactory {
	
	public SerialPortSession openSession();
	
	public void closeSession();
}
