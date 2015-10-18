/**
 * Copyright (c) 2015 Ivelin Yanev <qnev89@gmail.com>.
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or (at your option) any later version. 
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110, USA
 * 
 */
package com.fortran.arduino.impl.comunicationlayer;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.TooManyListenersException;

import com.fortran.arduino.connection.ConnectionStatus;
import com.fortran.arduino.impl.command.Command;

/**
 * The class, which creates Serial connection and provided all communication
 * mechanism for send and arrive the data.
 * 
 * @author Ivelin Yanev <qnev89@gmail.com>
 * @since 2015
 */
public class TwoWayComunication implements SerialPortEventListener {

	/**
	 * The field with an {@link SerialClassConnection} object.
	 */
	private SerialClassConnection serialClassConnection;

	/**
	 * A default constructor.
	 */
	public TwoWayComunication() {
		this.serialClassConnection = SerialClassConnection.getInstance();
	}

	/**
	 * Registers connection change status observer.
	 * 
	 * @param connectionStatus
	 *            the {@link ConnectionStatus} object.
	 */
	public void addCannectionStatusListener(
			final ConnectionStatus connectionStatus) {
		this.serialClassConnection.registerChangedListener(connectionStatus);
	}

	/**
	 * Opens a CommPort by CommPort name.
	 * 
	 * @param portName
	 *            the name of CommPort.
	 * @throws IOException
	 *             if {@link IOException} occurs.
	 * @throws NoSuchPortException
	 *             if {@link NoSuchPortException} occurs.
	 * @throws TooManyListenersException
	 *             if {@link TooManyListenersException} occurs.
	 */
	public void openPort(String portName) throws IOException,
			NoSuchPortException, TooManyListenersException {
		if (portName == null || portName.isEmpty()) {
			throw new NullPointerException("Portname is null");
		}
		CommPortIdentifier port = CommPortIdentifier
				.getPortIdentifier(portName);

		this.serialClassConnection.openPort(port);
		this.serialClassConnection.addSerialEventListener(this);
	}
	
	//TODO
	public void sendComand(Command cmd) throws IOException {
		byte[] cmdData;
		cmdData = cmd.getData();
		this.serialClassConnection.writeBlock(cmdData);
	}

	// Example how you can make command and send it
	/*
	 * public static void main(String[] args) throws IOException,
	 * TooManyListenersException {
	 * 
	 * TestComunication testComunication = new TestComunication();
	 * 
	 * Command cmd = new GetSensorData("hellow");
	 * 
	 * testComunication.sendComand(cmd);
	 * 
	 * 
	 * }
	 */

	public void serialEvent(SerialPortEvent spe) {
		if (spe.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			try {
				BufferedReader inputLine = this.serialClassConnection
						.inputeBlock();
				final String test = inputLine.readLine();
				System.out.println(test);
			} catch (Exception e) {
				System.err.println(e.toString());
			}
		}

	}
}
