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
package com.fortran.arduino.impl.packets;

import com.fortran.arduino.impl.command.Command;
import com.fortran.arduino.impl.command.CommandIndificator;

/**
 * 
 * @author Ivelin Yanev <bgfortran@gmail.com>
 * @since 2015
 */
public class GetSensorData extends Command {

	/**
     * 
     */
	private String sensorType;

	public GetSensorData(String data) {
		super(CommandIndificator.Sensor);
		this.sensorType = data;
	}

	@Override
	public byte[] getData() {
		return this.sensorType.getBytes();
	}

}
