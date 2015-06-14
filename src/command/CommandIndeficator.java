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
package command;

/**
 * 
 * @author Ivelin Ynev <qnev89@gmail.com>
 * @since 2015
 */
public enum CommandIndeficator {

	Sensor(0x10), // byte
	Motor(0x11); // byte

	private byte type;

	private CommandIndeficator(int _type) {
		this.type = (byte) _type;
	}

	/**
	 * @return the {@code int} representing this {@code ComPortType.}
	 */
	public byte getByte() {
		return this.type;
	}
}
