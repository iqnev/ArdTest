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
package com.fortran.arduino.impl.command;

/**
 * The <code>Command</code> represents a command structure. Each command has
 * <b>id</b> example{@link CommandIndeficator#Motor}.
 * 
 * @author Ivelin Ynev <qnev89@gmail.com>
 * @since 2015
 */
public abstract class Command {

	/**
	 * The command indificator.
	 */
	private CommandIndificator ind;

	/**
	 * A constructor.
	 * 
	 * @param ind
	 *            the id of the command.
	 */
	protected Command(CommandIndificator ind) {
		this.ind = ind;
	}

	/**
	 * Returns command indificator.
	 * 
	 * @return the id of the command.
	 */
	public CommandIndificator getCommandIndificator() {
		return this.ind;
	}

	/**
	 * Returns byte for command data.
	 * 
	 * @return byte of data.
	 */
	public abstract byte[] getData();

}
