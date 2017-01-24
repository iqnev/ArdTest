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

package com.fortran.arduino.transportlayer;

import com.fortran.arduino.impl.command.Command;
import com.fortran.arduino.impl.connection.Connection;
import com.fortran.arduino.impl.packets.Packet;

/**
 * 
 * Frame structure:
 * StartByte|CommenadIndeficator|PayloadLength|Payload|CRC|EndFrame
 *
 * @author Ivelin Yanev <bgfortran@gmail.com>
 * @since 2015
 */
public interface DataLinkLayer {
	/**
	 * Converts a command into a byte stream that matches the format of the
	 * frame.
	 *
	 * @param _command
	 *            the {@link Command} to be send.
	 * @return the frame as a byte array.
	 */
	public byte[] getRequestPackage(Command _command);

	/**
	 * Parses the incoming byte stream from the physical-layer into packets.
	 *
	 * @param _connection
	 *            represents the physical-layer; implementation of
	 *            {@code IOConnection} e.g Bluetooth, Serial, etc.
	 * @return the received {@code Packet}.
	 */
	public Packet parsePacket(Connection _connection);

}
