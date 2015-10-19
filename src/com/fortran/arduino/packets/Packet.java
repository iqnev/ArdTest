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
package com.fortran.arduino.packets;

/**
 * The <code>Packet</code> interface provides support for packing a series of
 * Ids and other kind of data. The resulting an object is used as an
 * intermediate object between <b>Communication Layer</b> and <b>Transport
 * Layer</b>. This is a way to achieve the necessary compatibility of the
 * outgoing and incoming data. This interface allows an application to not care
 * about how to order data that was submitted and its mapping. The interface
 * provides four methods that are described below.
 * <p>
 * For example how you can create a package with parameters:
 * 
 * <pre>
 * 	 TODO example
 * </pre>
 * 
 * @author Ivelin Yanev <qnev89@gmail.com>
 * @since 2015
 */
public interface Packet {

	/**
	 * Returns the error code, which can occurs during the communication between
	 * the <b>Driver</b> and the <b>Arduino</b>.
	 * 
	 * @return the error code.
	 */
	public int getErrorCode();

	/**
	 * Return the type of the message. These constants are defined above. Also
	 * this code determined, which event will be sent to the upper layer.
	 * 
	 * @return the type of the message.
	 */
	public int getTypePackage();

	/**
	 * Returns the message id. This id is a unique identifier for every message,
	 * which was sent by the <b>Driver</b>.
	 * 
	 * @return the message id.
	 */
	public long getMessageId();
}
