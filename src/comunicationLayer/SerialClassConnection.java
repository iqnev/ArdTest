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

import com.sun.jmx.snmp.SnmpDataTypeEnums;

import connection.Connection;
import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;
import helpers.Utilities;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.TooManyListenersException;

/**
 * 
 * @author Ivelin Yanev <qnev89@gmail.com>
 * @since 2015
 */
public class SerialClassConnection extends Connection {

	private static final int DEFAULT_TIME_OUT = 2000;
	private static final int DEFAULT_DATA_RATE = 9600;
	private static SerialClassConnection instance;

	private SerialPort serialPort;
	private DataInputStream inputStream;
	private OutputStream outputStream;
	private int timeOut;
	private int dataRate;

	/**
     *
     */
	private SerialClassConnection() {
		this.timeOut = DEFAULT_TIME_OUT;
		this.dataRate = DEFAULT_DATA_RATE;
	}

	/**
	 * set dataRade of the serial connection
	 *
	 * @param dataRate
	 */
	public void setDataRate(int dataRate) {
		this.dataRate = dataRate;
	}

	/**
	 * set timeOut of serial connection
	 *
	 * @param timeOut
	 */
	public void setTimeOut(int timeOut) {
		this.timeOut = timeOut;
	}

	public static SerialClassConnection getInstance() {
		if (instance == null) {
			instance = new SerialClassConnection();
		}
		return instance;
	}

	public boolean openPort(CommPortIdentifier commId)
			throws TooManyListenersException {
		if (commId == null) {
			throw new NullPointerException("Com port null");
		}

		if (commId.isCurrentlyOwned()) {
			throw new IllegalStateException("Com port in use");
		}

		try {
			this.serialPort = (SerialPort) commId.open(
					SerialClassConnection.class.getName(), this.timeOut);

			this.serialPort.setSerialPortParams(this.dataRate,
					SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);

			this.serialPort.notifyOnDataAvailable(true);
			this.serialPort.notifyOnOutputEmpty(true);
		} catch (UnsupportedCommOperationException | PortInUseException _e) {
			_e.printStackTrace();
		}
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

	@Override
	public BufferedReader inputeBlock() throws IOException {
		return new BufferedReader(new InputStreamReader(
				this.serialPort.getInputStream()));
	}

	public void addSerialEventListener(TestComunication _listener)
			throws TooManyListenersException {
		if (_listener == null) {
			throw new NullPointerException("Listener is null"); //$NON-NLS-1$
		}
		this.serialPort.addEventListener(_listener);
	}

	@Override
	public boolean isDataAvailable() {
		try {
			if (this.inputStream.available() != 0) {
				return true;
			} else {
				return false;
			}
		} catch (IOException ex) {
			ex.printStackTrace();
			return false;
			// Logger.getLogger(SerialClassConnection.class.getName()).log(Level.SEVERE,
			// null, ex);
		}
	}

	@Override
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

	@Override
	public byte[] readBlocked(int num) throws IOException {
		byte[] buff;
		buff = new byte[num];

		this.inputStream.readFully(buff, 0, num);

		return buff;
	}

	@Override
	public void write(byte[] bytes) throws IOException {
		if (bytes == null) {
			throw new NullPointerException("Byte buffer is empty");
		}

		this.outputStream.write(bytes);
	}

	@Override
	public boolean close() {
		if (!this.isConnected()) {
			throw new IllegalStateException("Connection already closed");
		}
		this.serialPort.removeEventListener();
		this.serialPort.close();

		Utilities.close(this.inputStream);
		Utilities.close(this.outputStream);

		this.isConnected = false;
		this.notifyListeners();

		return true;
	}

}
