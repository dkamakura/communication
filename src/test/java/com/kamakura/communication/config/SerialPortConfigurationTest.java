/**
 * SerialPortConfigurationTest.java
 *
 * Copyright 2010-2010 Daniel de Aguiar Kamakura
 */
package com.kamakura.communication.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.comm.SerialPort;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;

import com.kamakura.communication.test.BaseSpringTestCase;

/**
 * @author Daniel de Aguiar Kamakura
 *
 * @since 1.0
 */
public class SerialPortConfigurationTest extends BaseSpringTestCase {

	@Autowired
	SerialPortConfiguration serialPortConfiguration;
	
	@Test
	public void testDefaultSerialPortConfiguration() {
		assertNotNull(serialPortConfiguration);
		assertEquals("/dev/ttyUSB0", serialPortConfiguration.getPortName());
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
	public void testAvailableDevices() {
		List<String> availableDevices = serialPortConfiguration.getAvailableDevices();
		assertNotNull(availableDevices);
		assertTrue(availableDevices.contains("/dev/ttyUSB0"));
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
		assertEquals("/dev/ttyUSB0", serialPortConfiguration.getPortName());
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
	public void testToString() {
		assertEquals("{readTimeout=15000, parity=0, stopBits=1, dataBits=8, flowControlIn=0, baudRate=9600, receiveTimeout=15000, class=class com.kamakura.communication.config.SerialPortConfiguration, flowControlOut=0, availableDevices=/dev/ttyUSB0, portName=/dev/ttyUSB0}", serialPortConfiguration.toString());
	}
}
