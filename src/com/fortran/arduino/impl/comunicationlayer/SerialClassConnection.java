/**
 * Copyright (c) 2015 Ivelin Yanev <bgfortran@gmail.com>.
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
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.TooManyListenersException;

import com.fortran.arduino.impl.connection.Connection;
import com.fortran.arduino.impl.utilities.Utilities;

/**
 * The <code>SerialClassConnection</code> class representation <b>Serial
 * Communication</b>. Serial communication is used for all long-haul
 * communication and most computer networks, where the cost of cable and
 * synchronization difficulties make parallel communication impractical.
 * 
 * @author Ivelin Yanev <bgfortran@gmail.com>
 * @since 2015
 */
public class SerialClassConnection extends Connection {

	/**
	 * The value with instance.
	 */
	private static SerialClassConnection instance;

	/**
	 * The filed with {@link SerialPort} object.
	 */
	protected SerialPort serialPort;

	/**
	 * The filed with {@link DataInputStream} object.
	 */
	protected DataInputStream inputStream;

	/**
	 * The filed with {@link OutputStream} object.
	 */
	protected OutputStream outputStream;

	/**
	 * The filed with a TimeOut value.
	 */
	protected int timeOut;

	/**
	 * The filed with a DataRate value.
	 */
	protected int dataRate;

	/**
	 * A private constructor.
	 */
	private SerialClassConnection() {
		this.timeOut = Connection.DEFAULT_TIME_OUT;
		this.dataRate = Connection.DEFAULT_DATA_RATE;
	}

	/**
	 * Returns an instance of the {@link SerialClassConnection} class.
	 * 
	 * @return the {@link SerialClassConnection} object.
	 */
	public static SerialClassConnection getInstance() {
		if (instance == null) {
			instance = new SerialClassConnection();
		}
		return instance;
	}

	/**
	 * Sets DataRade of the serial connection.
	 *
	 * @param dataRate
	 *            the DataRade value.
	 */
	public void setDataRate(final int dataRate) {
		this.dataRate = dataRate;
	}

	/**
	 * Sets TimeOut of serial connection
	 *
	 * @param timeOut
	 *            the TimeOut value.
	 */
	public void setTimeOut(final int timeOut) {
		this.timeOut = timeOut;
	}

	/**
	 * Opens a CommPort using {@link CommPortIdentifier} object. If the port is
	 * owned by some other application the method throws {@link IOException}.
	 * 
	 * @param portId
	 *            the {@link CommPortIdentifier} object.
	 * @return <code>true<code> if CommPort is opened; <code>false</code>
	 *         otherwise.
	 * @throws IOException
	 *             if {@link IOException} occurs.
	 */
	public boolean openPort(final CommPortIdentifier portId) throws IOException {
		if (portId == null) {
			throw new NullPointerException("Com port null");
		}

		if (portId.isCurrentlyOwned()) {
			throw new IllegalStateException("Com port in use");
		}

		// Get the port's ownership
		try {
			this.serialPort = (SerialPort) portId.open(
					SerialClassConnection.class.getName(), this.timeOut);

			setSerialPortParameters();
		} catch (final PortInUseException e) {
			throw new IOException(e.getMessage());
		}

		this.serialPort.notifyOnDataAvailable(true);
		this.serialPort.notifyOnOutputEmpty(true);

		try {
			this.inputStream = new DataInputStream(
					this.serialPort.getInputStream());
			this.outputStream = this.serialPort.getOutputStream();

		} catch (IOException e) {
			Utilities.close(this.inputStream);
			Utilities.close(this.outputStream);

			return false;
		}

		this.isConnected = true;
		this.notifyListeners();

		return true;
	}

	/**
	 * Sets the serial port parameters.
	 */
	private void setSerialPortParameters() throws IOException {
		try {
			this.serialPort.setSerialPortParams(this.dataRate,
					SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);
		} catch (UnsupportedCommOperationException e) {
			throw new IOException("Unsupported serial port parameter");
		}
	}

	public BufferedReader inputeBlock() throws IOException {
		return new BufferedReader(new InputStreamReader(
				this.serialPort.getInputStream()));
	}

	/**
	 * Adds the specified TwoWayComunication listener to receive
	 * {@link SerialClassConnection} events. If listener deviceListener is null,
	 * no exception is thrown and no action is performed.
	 * 
	 * @param listener
	 *            the {@link TwoWayComunication} object.
	 * @throws TooManyListenersException
	 *             if {@link TooManyListenersException} occurs.
	 */
	public void addSerialEventListener(final TwoWayComunication listener)
			throws TooManyListenersException {
		this.serialPort.addEventListener(listener);
	}

	public boolean isDataAvailable() {
		boolean flag = false;
		try {
			if (this.inputStream.available() != 0) {
				flag = true;
			}
		} catch (IOException ex) {

		}

		return flag;
	}

	public int getAvailableBytes() throws IOException {
		return this.inputStream.available();
	}

	@Override
	public void test() throws IOException {
		/* private */
		BufferedReader inputTest;
		String inputLine;

		inputTest = new BufferedReader(new InputStreamReader(
				this.serialPort.getInputStream()));
		System.out.println(inputTest.readLine());
	}

	public byte[] readBlocked(int num) throws IOException {
		byte[] buff = new byte[num];

		this.inputStream.readFully(buff, 0, num);

		return buff;
	}

	public void writeBlock(byte[] bytes) throws IOException {
		if (bytes == null) {
			throw new NullPointerException("Byte buffer is empty");
		}
		System.out.println("SerialClassConnection.writeBlock() bytes SIZE >>>>>>>>>>>>" + bytes.length);
		this.outputStream.write(bytes);
	}

	public void close() {
		if (!this.isConnected()) {
			throw new IllegalStateException("Connection already closed");
		}
		this.serialPort.removeEventListener();
		this.serialPort.close();

		Utilities.close(this.inputStream);
		Utilities.close(this.outputStream);

		this.isConnected = false;
		this.notifyListeners();
	}

}
