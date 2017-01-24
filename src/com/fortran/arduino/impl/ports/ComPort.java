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
import gnu.io.NoSuchPortException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

/**
 * The <code>ComPort</code> class allows an application to launch associated the
 * mandatory COM Ports of the current computer for our needs.
 * <p>
 * Supported operations include:
 * <ul>
 * <li>gets all available COM Port Identifiers</li>
 * <li>gets all available COM Port Names</li>
 * <li>creates COM Port by given an Identifier</li>
 * </ul>
 * 
 * @author Ivelin Yanev <bgfortran@gmail.com>
 * @since 2015
 */
public class ComPort {

	private static ComPort instance = null;

	/**
	 * Returns <code>ComPort</code> instance of the current object.
	 * 
	 * @return the Singleton instance of {@link ComPort} class.
	 */
	public static ComPort getInstance() {
		if (instance == null) {
			instance = new ComPort();
		}

		return instance;
	}

	/**
	 * The private default controller.
	 */
	private ComPort() {

	}

	/**
	 * Returns the {@link CommPortIdentifier} representing the available COM
	 * Ports.
	 * 
	 * @return the {@link List} of {@link CommPortIdentifier}.
	 */
	public static List<CommPortIdentifier> getAvailableComPorts() {
		List<CommPortIdentifier> identifiers = null;

		if (CommPortIdentifier.getPortIdentifiers() != null) {
			identifiers = Collections.list(CommPortIdentifier
					.getPortIdentifiers());
		}

		return identifiers;
	}

	/**
	 * Returns the representing the names of the available COM Ports.
	 * 
	 * @return the {@link List} of the {@link String}.
	 */
	public static List<String> getAvailableComPortNames() {
		List<CommPortIdentifier> comPorts;
		List<String> comPortNames = new ArrayList<String>();

		comPorts = getAvailableComPorts();

		for (CommPortIdentifier portId : comPorts) {
			comPortNames.add(portId.getName());
		}

		return comPortNames;
	}

	/**
	 * Returns the available ComPort by {@link ComPortType}. If the ComPort is
	 * not-existing the method throws the {@link NullPointerException}.
	 * 
	 * @param portType
	 *            the {@link ComPortType} to filter with.
	 * @return the {@link List} of {@link CommPortIdentifier} which are of the
	 *         specified type.
	 */
	public List<CommPortIdentifier> getAvailableComPorts(
			final ComPortType portType) {
		List<CommPortIdentifier> comPorts;
		ComPortType type;

		if (portType == null) {
			throw new NullPointerException("Port type is null");
		}

		comPorts = ComPort.getAvailableComPorts();

		for (CommPortIdentifier port : comPorts) {
			type = ComPortType.getComPortFromInt(port.getPortType());
			if (type != portType) {
				comPorts.remove(port);
			}
		}

		return comPorts;
	}

	/**
	 * Creates a {@code ComPortIdentifier} from the specified COM Port name.
	 *
	 * @param comName
	 *            the name of the COM Port.
	 * @return the {@link ComPortIdentifier} object.
	 */
	public static CommPortIdentifier createComPortIdentifier(
			final String comName) {
		CommPortIdentifier comPortIdentifier;
		if (comName == null || comName.length() == 0) {
			throw new IllegalArgumentException("Comport name is null or empty");
		}
		try {
			comPortIdentifier = CommPortIdentifier.getPortIdentifier(comName);
		} catch (NoSuchPortException _e) {
			comPortIdentifier = null;
		}

		return comPortIdentifier;
	}

}
