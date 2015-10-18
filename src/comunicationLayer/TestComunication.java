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
package comunicationLayer;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.TooManyListenersException;

import command.Command;
import connection.Connection;
import connection.ConnectionStatus;
import static sun.security.krb5.Confounder.bytes;

/**
 * 
 * @author Ivelin Yanev <qnev89@gmail.com>
 * @since 2015
 */
public class TestComunication implements SerialPortEventListener {

	private Connection connection;
	private SerialClassConnection serialClassConnection;

	public TestComunication() throws IOException, TooManyListenersException {
		this.serialClassConnection = SerialClassConnection.getInstance();
	}

	// register connection change status observer
	public void addCannectionStatusListener(ConnectionStatus connectionStatus) {
		this.serialClassConnection.registerChangedListener(connectionStatus);
	}

	public boolean openPort(String portName) throws NoSuchPortException,
			TooManyListenersException {
		if (portName == null || portName.isEmpty()) {
			throw new NullPointerException("Portname is null");
		}
		CommPortIdentifier port = CommPortIdentifier
				.getPortIdentifier(portName);

		this.serialClassConnection.openPort(port);
		this.serialClassConnection.addSerialEventListener(this);
		return true;
	}

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

	@Override
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
