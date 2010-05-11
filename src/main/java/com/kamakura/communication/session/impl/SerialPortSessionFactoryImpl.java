package com.kamakura.communication.session.impl;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kamakura.communication.config.SerialPortConfiguration;
import com.kamakura.communication.session.SerialPortSession;
import com.kamakura.communication.session.SerialPortSessionFactory;

@Component
public class SerialPortSessionFactoryImpl implements SerialPortSessionFactory {

  Logger logger = Logger.getLogger(SerialPortSessionImpl.class);

  @Autowired
  private SerialPortConfiguration serialPortConfiguration;

  private SerialPort serialPort;

  private Reader in;

  private Writer out;

  @Override
  public SerialPortSession openSession() {
    logger.debug("Opening session");
    try {
      logger.debug("Getting serial port");
      CommPortIdentifier portId = CommPortIdentifier.getPortIdentifier(serialPortConfiguration.getPortName());

      logger.debug("Open serial port");
      serialPort = (SerialPort) portId.open("Datalogger", serialPortConfiguration.getReadTimeout());

      logger.debug("Set serial port parameters");
      serialPort.setSerialPortParams(serialPortConfiguration.getBaudRate(), serialPortConfiguration.getDataBits(), serialPortConfiguration.getStopBits(), serialPortConfiguration.getParity());
      serialPort.setFlowControlMode(serialPortConfiguration.getFlowControlIn() | serialPortConfiguration.getFlowControlOut());
      serialPort.enableReceiveTimeout(serialPortConfiguration.getReceiveTimeout());

      logger.debug("Opening streams");
      in = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
      out = new BufferedWriter(new OutputStreamWriter(serialPort.getOutputStream()));
    } catch (NoSuchPortException ex) {
      throw new RuntimeException("error.opening.serial.port", ex);
    } catch (PortInUseException ex) {
      throw new RuntimeException("error.serial.port.in.use", ex);
    } catch (UnsupportedCommOperationException ex) {
      throw new RuntimeException("error.configuring.serial.port", ex);
    } catch (IOException exception) {
      throw new RuntimeException("error.opening.streams", exception);
    }

    logger.debug("Session open");
    return new SerialPortSessionImpl(in, out);
  }

  @Override
  public void closeSession() {
    logger.debug("Closing session");
    try {
      if(in != null) {
        in.close();
      }
    } catch (IOException ex) {
      logger.warn("Error closing input stream.", ex);
    }
    try {
      if(out != null) {
        out.close();
      }
    } catch (IOException ex) {
      logger.warn("Error closing output stream.", ex);
    }
    if(serialPort != null) {
      serialPort.close();
    }
    logger.debug("Session closed");
  }
}
