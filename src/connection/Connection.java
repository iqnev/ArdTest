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
package connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Ivelin Yanev <qnev89@gmail.com>
 * @since 2015
 */
public abstract class Connection {

	private ArrayList<ConnectionStatus> listeners;
	protected boolean isConnected;

	/**
	 * 
	 */
	protected Connection() {
		this.listeners = new ArrayList<>();
	}

	/**
     *
     */
	public void notifyListeners() {
		for (ConnectionStatus listener : listeners) {
			listener.statusChanged(this.isConnected);
		}
	}

	/**
	 *
	 * @return true if the connection is connected
	 */
	public boolean isConnected() {
		return this.isConnected;
	}

	/**
	 *
	 * @param obj
	 */
	public void registerChangedListener(ConnectionStatus obj) {
		if (obj != null) {
			this.listeners.add(obj);
		} else {
			throw new NullPointerException("Null Observer");
		}
	}

	/**
	 *
	 * @return
	 */
	public abstract boolean isDataAvailable();

	/**
	 *
	 * @return @throws IOException
	 */
	public abstract int getAvailableBytes() throws IOException;

	/**
	 *
	 * @param num
	 * @return
	 * @throws IOException
	 */
	public abstract byte[] readBlocked(int num) throws IOException;

	/**
	 *
	 * @param bytes
	 * @throws IOException
	 */
	public abstract void write(byte[] bytes) throws IOException;

	public abstract boolean close();

	public abstract void test() throws IOException;

	public abstract BufferedReader inputeBlock() throws IOException;
}
