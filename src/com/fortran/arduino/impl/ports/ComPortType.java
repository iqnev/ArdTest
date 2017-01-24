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
package com.fortran.arduino.impl.ports;

import gnu.io.CommPortIdentifier;

/**
 * The <code>ComPortType</code> Provides a mechanism for works with different
 * types of communication protocols.
 * <ul>
 * <li>RS-232 is a standard for serial communication transmission of data.</li>
 * <li>IEEE 1284 is a standard that defines bi-directional parallel
 * communications</li>
 * <li>I2C Protocol is a protocol intended to allow multiple “slave” digital
 * integrated circuits (“chips”) to communicate with one or more “master” chip.</li>
 * <li>RS-485 enables the configuration of inexpensive local networks and
 * multidrop communications links.</li>
 * </ul>
 * 
 * @author Ivelin Yanev <bgfortran@gmail.com>
 * @since 2015
 */
public enum ComPortType {

	/**
	 * Represents Serial (RS232) COM Port.
	 */
	SERIAL(CommPortIdentifier.PORT_SERIAL),

	/**
	 * Represents a parralel (Centronics) Port.
	 */
	PARALLEL(CommPortIdentifier.PORT_PARALLEL),

	/**
	 * Represents I2C (Inter-Integrated Circuit) Port.
	 */
	I2C(CommPortIdentifier.PORT_I2C),

	/**
	 * Represent RS485 Port.
	 */
	RS485(CommPortIdentifier.PORT_RS485),

	/**
	 * Unknown type.
	 */
	UNKNOWN(555);

	private int type;

	/**
	 * A constructor with a parameter.
	 * 
	 * @param type
	 *            the COM Port name.
	 */
	private ComPortType(final int type) {
		this.type = type;
	}

	/**
	 * Returns the id representing this {@link ComPortType}.
	 * 
	 * @return the id.
	 */
	public int getInt() {
		return this.type;
	}

	/**
	 * Returns a {@link ComPortType} representing the specified type.
	 * 
	 * @param type
	 *            the value type.
	 * @return the {@link ComPortType}.
	 */
	public static ComPortType getComPortFromInt(final int pType) {
		ComPortType comPortType = ComPortType.UNKNOWN;
		for (ComPortType type : ComPortType.values()) {
			if (type.getInt() == pType) {
				comPortType = type;
			}
		}

		return comPortType;
	}
}
