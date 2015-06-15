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
package transportLayer;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

import packets.Packet;
import command.Command;
import connection.Connection;

/**
 * 
 * @author Ivelin Yanev <qnev89@gmail.com>
 * @since 2015
 */
public class TestDataLink implements DataLinkLayer {

	@Override
	public byte[] getRequestPackage(Command _command) {
		int frameLenght = 7;
		int dataLenght;
		dataLenght = _command.getData().length;
		frameLenght += dataLenght;

		ByteBuffer buffer = ByteBuffer.allocate(frameLenght);

		// Create MessageDigest instance for MD5

		// MessageDigest md = MessageDigest.getInstance("MD5");

		// Add command bytes to digest
		// md.update(_command.getCommandIndeficator().getByte());
		buffer.put(FrameDelimeters.START);
		buffer.put(_command.getCommandIndificator().getByte());
		buffer.putInt(dataLenght);
		buffer.put(_command.getData());
		// buffer.put(md.digest());
		buffer.put(FrameDelimeters.END);

		return buffer.array();
	}

	@Override
	public Packet parsePacket(Connection _connection) {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

}
