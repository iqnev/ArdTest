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

/**
 * The <code>ConnectionStatus</code> interface allows an application to receive
 * connection status events. This interface provides a method for notification
 * of the status is changed.
 * 
 * <p>
 * Each application which wants to receive connection status <b>MUST</b>
 * implementation <code>ConnectionStatus</code> interface and then in class
 * constructor <b>MUST</b> registration for that event
 * 
 * @author Ivelin Yanev <qnev89@gmail.com>
 * @since 2015
 */
public interface ConnectionStatus {

	/**
	 * This method called when a connection status is changed.
	 * 
	 * @param isConnected
	 *            the connection status.
	 */
	public void statusChanged(boolean isConnected);
}
