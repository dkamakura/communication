/**
 * SerialPortConfigurationTest.java
 *
 * Copyright 2010-2010 Daniel de Aguiar Kamakura
 */
package com.kamakura.communication.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import gnu.io.SerialPort;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Daniel de Aguiar Kamakura
 *
 * @since 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/*.xml"})
public class SerialPortConfigurationTest {

	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private SerialPortConfiguration serialPortConfiguration;
	
	@Test
	public void testDefaultSerialPortConfiguration() {
		assertNotNull(serialPortConfiguration);
		assertNotNull(serialPortConfiguration.getPortName());
		assertEquals(new Integer(15000), serialPortConfiguration.getReadTimeout());
		assertEquals(new Integer(15000), serialPortConfiguration.getReceiveTimeout());
		assertEquals(new Integer(9600), serialPortConfiguration.getBaudRate());
		assertEquals(new Integer(SerialPort.FLOWCONTROL_NONE), serialPortConfiguration.getFlowControlIn());
		assertEquals(new Integer(SerialPort.FLOWCONTROL_NONE), serialPortConfiguration.getFlowControlOut());
		assertEquals(new Integer(SerialPort.DATABITS_8), serialPortConfiguration.getDataBits());
		assertEquals(new Integer(SerialPort.STOPBITS_1), serialPortConfiguration.getStopBits());
		assertEquals(new Integer(SerialPort.PARITY_NONE), serialPortConfiguration.getParity());
	}

	@Test
	public void testListAvailableSerialPorts() {
		List<String> availableSerialPorts = serialPortConfiguration.listAvailableSerialPorts();
		assertNotNull(availableSerialPorts);
		assertFalse(availableSerialPorts.isEmpty());
	}

	@Test
	@DirtiesContext
	public void testNewSerialPortConfiguration() {
		SerialPortConfiguration serialPortConfiguration = new SerialPortConfiguration().setPortName("/dev/ttyS1").setBaudRate(1200).setFlowControlIn(SerialPort.FLOWCONTROL_XONXOFF_IN).setFlowControlOut(SerialPort.FLOWCONTROL_XONXOFF_OUT).setDataBits(SerialPort.DATABITS_6).setStopBits(SerialPort.STOPBITS_1_5).setParity(SerialPort.PARITY_EVEN);
		assertNotNull(serialPortConfiguration);
		assertEquals("/dev/ttyS1", serialPortConfiguration.getPortName());
		assertEquals(new Integer(15000), serialPortConfiguration.getReadTimeout());
		assertEquals(new Integer(15000), serialPortConfiguration.getReceiveTimeout());
		assertEquals(new Integer(1200), serialPortConfiguration.getBaudRate());
		assertEquals(new Integer(SerialPort.FLOWCONTROL_XONXOFF_IN), serialPortConfiguration.getFlowControlIn());
		assertEquals(new Integer(SerialPort.FLOWCONTROL_XONXOFF_OUT), serialPortConfiguration.getFlowControlOut());
		assertEquals(new Integer(SerialPort.DATABITS_6), serialPortConfiguration.getDataBits());
		assertEquals(new Integer(SerialPort.STOPBITS_1_5), serialPortConfiguration.getStopBits());
		assertEquals(new Integer(SerialPort.PARITY_EVEN), serialPortConfiguration.getParity());
	}

	@Test
	@DirtiesContext
	public void testSerialPortConfigurationReinjection() {
		assertNotNull(serialPortConfiguration);
		assertNotNull(serialPortConfiguration.getPortName());
		assertEquals(new Integer(15000), serialPortConfiguration.getReadTimeout());
		assertEquals(new Integer(15000), serialPortConfiguration.getReceiveTimeout());
		assertEquals(new Integer(9600), serialPortConfiguration.getBaudRate());
		assertEquals(new Integer(SerialPort.FLOWCONTROL_NONE), serialPortConfiguration.getFlowControlIn());
		assertEquals(new Integer(SerialPort.FLOWCONTROL_NONE), serialPortConfiguration.getFlowControlOut());
		assertEquals(new Integer(SerialPort.DATABITS_8), serialPortConfiguration.getDataBits());
		assertEquals(new Integer(SerialPort.STOPBITS_1), serialPortConfiguration.getStopBits());
		assertEquals(new Integer(SerialPort.PARITY_NONE), serialPortConfiguration.getParity());
		
		serialPortConfiguration.setPortName("/dev/ttyS1");
		serialPortConfiguration.setParity(SerialPort.PARITY_MARK);
		
		SerialPortConfiguration newSerialPortConfiguration = applicationContext.getBean(SerialPortConfiguration.class);
		assertNotNull(newSerialPortConfiguration);
		assertEquals("/dev/ttyS1", newSerialPortConfiguration.getPortName());
		assertEquals(new Integer(15000), newSerialPortConfiguration.getReadTimeout());
		assertEquals(new Integer(15000), newSerialPortConfiguration.getReceiveTimeout());
		assertEquals(new Integer(9600), newSerialPortConfiguration.getBaudRate());
		assertEquals(new Integer(SerialPort.FLOWCONTROL_NONE), newSerialPortConfiguration.getFlowControlIn());
		assertEquals(new Integer(SerialPort.FLOWCONTROL_NONE), newSerialPortConfiguration.getFlowControlOut());
		assertEquals(new Integer(SerialPort.DATABITS_8), newSerialPortConfiguration.getDataBits());
		assertEquals(new Integer(SerialPort.STOPBITS_1), newSerialPortConfiguration.getStopBits());
		assertEquals(new Integer(SerialPort.PARITY_MARK), newSerialPortConfiguration.getParity());
		
	}
	
	@Test
	@DirtiesContext
	public void testToString() {
		List<String> availableDevices = serialPortConfiguration.listAvailableSerialPorts();
		assertEquals("{readTimeout=15000, parity=0, stopBits=1, dataBits=8, flowControlIn=0, baudRate=9600, receiveTimeout=15000, class=class com.kamakura.communication.config.SerialPortConfiguration, flowControlOut=0, portName=" + availableDevices.get(0) + "}", serialPortConfiguration.toString());
	}
}
