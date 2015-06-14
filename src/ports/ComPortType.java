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
package ports;

import gnu.io.CommPortIdentifier;

/**
 * 
 * @author Ivelin Yanev <qnev89@gmail.com>
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

	private ComPortType(int _type) {
		this.type = _type;
	}

	/**
	 * @return the {@code int} representing this {@code ComPortType.}
	 */
	public int getInt() {
		return this.type;
	}

	/**
	 * @param _type
	 *            an {@code int} value.
	 * @return the {@code ComPortType} representing the specified {@code int}.
	 */
	public static ComPortType fromInt(int _type) {
		for (ComPortType type : ComPortType.values()) {
			if (type.getInt() == _type) {
				return type;
			}
		}

		return ComPortType.UNKNOWN;
	}
}
