/**
 * SerialPortConfiguration.java
 *
 * Copyright 2010-2010 Daniel de Aguiar Kamakura
 */
package com.kamakura.communication.config;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * @author Daniel de Aguiar Kamakura
 *
 * @since 1.0
 */
@Component
public class SerialPortConfiguration {
	Logger logger = Logger.getLogger(SerialPortConfiguration.class);
	
	private Integer readTimeout;
	private Integer receiveTimeout;
	private String portName;
	private Integer baudRate;
	private Integer flowControlIn;
	private Integer flowControlOut;
	private Integer dataBits;
	private Integer stopBits;
	private Integer parity;

	private List<String> availableSerialPorts = new ArrayList<String>();

	/**
	 * Default constructor. Set parameters to no port, 9600 baud rate, none flowControlIn, none flowControlOut, 8 data bit, 1 stop bit, none parity. 
	 */
	public SerialPortConfiguration() {
		this("", 15000, 15000, 9600, SerialPort.FLOWCONTROL_NONE, SerialPort.FLOWCONTROL_NONE, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
		if(!this.listAvailableDevices().isEmpty()) {
			this.setPortName(this.availableSerialPorts.get(0));
		}
	}
	
	/**
	 * Full constructor
	 * 
	 * @param portName
	 * @param readTimeout
	 * @param receiveTimeout
	 * @param baudRate
	 * @param flowControlIn
	 * @param flowControlOut
	 * @param dataBits
	 * @param stopBits
	 * @param parity
	 */
	public SerialPortConfiguration(String portName, Integer readTimeout, Integer receiveTimeout, Integer baudRate, Integer flowControlIn, Integer flowControlOut, Integer dataBits, Integer stopBits, Integer parity) {
		this.portName = portName;
		this.readTimeout = readTimeout;
		this.receiveTimeout = receiveTimeout;
		this.baudRate = baudRate;
		this.flowControlIn = flowControlIn;
		this.flowControlOut = flowControlOut;
		this.dataBits = dataBits;
		this.stopBits = stopBits;
		this.parity = parity;
	}

	/* (non-Javadoc)
	 * @see com.kamakura.comm.DeviceConfiguration#portName(java.lang.String)
	 */
	public SerialPortConfiguration setPortName(String portName) {
		this.portName = portName;
		return this;
	}

	public SerialPortConfiguration setReadTimeout(Integer readTimeout) {
		this.readTimeout = readTimeout;
		return this;
	}

	public SerialPortConfiguration setReceiveTimeout(Integer receiveTimeout) {
		this.receiveTimeout = receiveTimeout;
		return this;
	}

	public SerialPortConfiguration setBaudRate(Integer baudRate) {
		this.baudRate = baudRate;
		return this;
	}

	public SerialPortConfiguration setFlowControlIn(Integer flowControlIn) {
		this.flowControlIn = flowControlIn;
		return this;
	}

	public SerialPortConfiguration setFlowControlOut(Integer flowControlOut) {
		this.flowControlOut = flowControlOut;
		return this;
	}

	public SerialPortConfiguration setDataBits(Integer dataBits) {
		this.dataBits = dataBits;
		return this;
	}

	public SerialPortConfiguration setStopBits(Integer stopBits) {
		this.stopBits = stopBits;
		return this;
	}

	public SerialPortConfiguration setParity(Integer parity) {
		this.parity = parity;
		return this;
	}

	/**
	 * This method returns a list with all available device names
	 * @return
	 */
	public List<String> listAvailableDevices() {
		@SuppressWarnings("unchecked")
		List<CommPortIdentifier> availableDevices = Collections.list(CommPortIdentifier.getPortIdentifiers());
		
		for(CommPortIdentifier portId : availableDevices) {
			if(CommPortIdentifier.PORT_SERIAL == portId.getPortType()) {
				availableSerialPorts.add(portId.getName());
			}
		}
		return availableSerialPorts;
	}

	public Integer getReadTimeout() {
		return readTimeout;
	}

	public Integer getReceiveTimeout() {
		return receiveTimeout;
	}

	public String getPortName() {
		return portName;
	}

	public Integer getBaudRate() {
		return baudRate;
	}

	public Integer getFlowControlIn() {
		return flowControlIn;
	}

	public Integer getFlowControlOut() {
		return flowControlOut;
	}

	public Integer getDataBits() {
		return dataBits;
	}

	public Integer getStopBits() {
		return stopBits;
	}

	public Integer getParity() {
		return parity;
	}
	
	public String toString() {
		try {
			return BeanUtils.describe(this).toString();
		} catch (Exception ex) {
			logger.warn("Error converting serial port configuration to String", ex);
		}
		return super.toString();
	}
}

