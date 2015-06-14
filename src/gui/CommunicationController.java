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
package gui;

import gnu.io.NoSuchPortException;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.TooManyListenersException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;

import packets.GetSensorData;
import command.Command;
import comunicationLayer.SerialClassConnection;
import comunicationLayer.TestComunication;

/**
 * @author Ivelin Yanev <qnev89@gmail.com>
 * @since 2015
 */
public class CommunicationController implements ActionListener {

	private JFrame frame;
	private CommunicationView view;

	private TestComunication communication;

	public CommunicationController() throws IOException,
			TooManyListenersException {
		this.frame = new JFrame("Test Communication");
		this.frame.setSize(new Dimension(500, 300));
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setLocationRelativeTo(null);
		this.view = new CommunicationView();
		this.view.addActionListener(this);

		// initialize connection here
		this.communication = new TestComunication();
		this.communication.addCannectionStatusListener(this.view);

		this.frame.getContentPane().add(this.view);
		this.frame.setVisible(true);
	}

	private void sendCommand() throws IOException {
		Command cmd = new GetSensorData("hellow");
		this.communication.sendComand(cmd);

	}

	@Override
	public void actionPerformed(ActionEvent _event) {
		String actionCommand;

		actionCommand = _event.getActionCommand();

		if (actionCommand.equals(ViewConstants.BUTTON_SEND)) {
			try {
				this.sendCommand();
			} catch (IOException ex) {
				Logger.getLogger(CommunicationController.class.getName()).log(
						Level.SEVERE, null, ex);
			}
		} else if (actionCommand.equals(ViewConstants.CONNECT_ACTION_COMAND)) {
			String portName = this.view.getPortName();

			try {
				this.communication.openPort(portName);
			} catch (NoSuchPortException e) {
				System.out.println("Error");
			} catch (TooManyListenersException e) {
				System.out.println("Error");
			}

		} else if (actionCommand.equals(ViewConstants.DISCONNECT_ACTION_COMAND)) {
			SerialClassConnection.getInstance().close();
		}

	}

}
