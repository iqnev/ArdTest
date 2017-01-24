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

/**
 * @author Ivelin Yanev <bgfortran@gmail.com>
 * @since 2015
 */
public interface Message {

	/**
	 * The constant, which is used to mark the beginning of each message header.
	 */
	public final static int START_OF_HEADING = 0x01;

	/**
	 * The constant, which is used to mark the ending of each message frame.
	 */
	public final static int END_OF_TRANSMISSION = 0x04;

	/**
	 * The constant with an Acknowledgment value.
	 */
	public final static int ACK = 0x06;

	/**
	 * The CRC calculations and validates a checksum.
	 * 
	 * @param fMessage
	 *            the message.
	 * @return <code>if</code> CRC is correct. <code>false</code> otherwise.
	 */
	public boolean CRC16(Message fMessage);
	
	//TODO
}
