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
package com.fortran.arduino.impl.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import com.fortran.arduino.connection.ConnectionStatus;

/**
 * The <code>Connection</code> abstract class allows an application to
 * registering and unregistering listeners. To support listeners, an object
 * needs to maintain a list of registered listeners, provide a means of
 * registering and unregistering listeners, and call each listener when the
 * appropriate event occurs.
 * 
 * This class provides a mechanism for reading and writing bytes from/to Arduino
 * board.
 * 
 * @author Ivelin Yanev <qnev89@gmail.com>
 * @since 2015
 */
public abstract class Connection {

	/**
	 * The value of TIME OUT constant.
	 */
	public static final int DEFAULT_TIME_OUT = 2000;

	/**
	 * The value data rate.
	 */
	public static final int DEFAULT_DATA_RATE = 9600;

	/**
	 * The all registered events.
	 */
	private ArrayList<ConnectionStatus> listeners;

	/**
	 * The field with connection status.
	 */
	protected boolean isConnected;

	/**
	 * A default constructor with initialized {@link Connection#isConnected}
	 * variable.
	 */
	protected Connection() {
		this.listeners = new ArrayList<>();
	}

	/**
	 * Notifies all observers, which is registered.
	 */
	public void notifyListeners() {
		for (ConnectionStatus listener : listeners) {
			listener.statusChanged(this.isConnected);
		}
	}

	/**
	 * Returns the boolean value <code>true</code> if connection is connected.
	 * 
	 * @return true if the connection is connected.
	 */
	public boolean isConnected() {
		return this.isConnected;
	}

	/**
	 * This method add the object by type {@link ConnectionStatus}. When the
	 * object is added, it is ready to receives events. If listener
	 * communicationListener is null, no exception is thrown and no action is
	 * performed.
	 * 
	 * @param cStatus
	 *            the object by type {@link ConnectionStatus}.
	 */
	public void registerChangedListener(final ConnectionStatus cStatus) {
		if (cStatus != null) {
			this.listeners.add(cStatus);
		}
	}

	/**
	 * Checks whether the data are available.
	 * 
	 * @return <code>true</code> if the data are available; <code>false</code>
	 *         otherwise
	 */
	public abstract boolean isDataAvailable();

	/**
	 * Gets all available data from input stream.
	 * 
	 * @return the available data.
	 * @throws IOException
	 *             if {@link IOException} occurs.
	 */
	public abstract int getAvailableBytes() throws IOException;

	/**
	 * This method reads block of data with size.
	 * 
	 * @param size
	 *            the size of data.
	 * @return the bytes of array.
	 * @throws IOException
	 *             if {@link IOException} occurs.
	 */
	public abstract byte[] readBlocked(int size) throws IOException;

	/**
	 * Writes the specified byte to this byte array output stream.
	 * 
	 * @param bytes
	 *            the byte to be written.
	 * @throws IOException
	 *             if {@link IOException} occurs.
	 */
	public abstract void writeBlock(byte[] bytes) throws IOException;

	/**
	 * Closes this connection stream and releases any system resources
	 * associated with the stream.
	 * 
	 */
	public abstract void close();

	// TODO
	public abstract void test() throws IOException;

	/**
	 * Creates a buffering character-input stream.
	 * 
	 * @return the {@link BufferedReader} object.
	 * @throws IOException
	 *             if {@link IOException} occurs.
	 */
	public abstract BufferedReader inputeBlock() throws IOException;
}
