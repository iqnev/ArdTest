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
package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.PopupMenuListener;

import com.fortran.arduino.connection.ConnectionStatus;
import com.fortran.arduino.impl.utilities.ComboBoxListPortAdapter;

/**
 * 
 * @author Ivelin Yanev <bgfortran@gmail.com>
 * @since 2015
 */
public class CommunicationView extends JPanel implements ConnectionStatus {

	private JButton sendButton;
	private JButton connectButton;
	private JComboBox portsList;
	private ArrayList<String> listPorts;

	private JPanel commandsPanel;

	public CommunicationView() {
		this.add(Box.createRigidArea(new Dimension(0, 10)));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(this.createConnectionPanel());

		this.add(this.createCommandSendPanel());
		this.add(Box.createRigidArea(new Dimension(0, 100)));
	}

	private JPanel createCommandSendPanel() {
		Border border;
		String borderTitle;
		String buttonTitle;

		borderTitle = ViewConstants.COMMANDS_SEND_BORDER;
		buttonTitle = ViewConstants.BUTTON_SEND;
		commandsPanel = new JPanel();

		border = BorderFactory.createTitledBorder(borderTitle);

		commandsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
		commandsPanel.setPreferredSize(new Dimension(0, 70));
		commandsPanel.setBorder(border);
		commandsPanel.setVisible(false);

		this.sendButton = new JButton(buttonTitle);
		commandsPanel.add(this.sendButton);

		return commandsPanel;
	}

	private JPanel createConnectionPanel() {
		JPanel connectionPanel;
		Border border;
		String borderTitle;
		String buttonTitle;

		borderTitle = ViewConstants.CONNECTION_BORDER;
		buttonTitle = ViewConstants.BUTTON_CONNECT_LABEL;
		connectionPanel = new JPanel();

		border = BorderFactory.createTitledBorder(borderTitle);

		connectionPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0));
		connectionPanel.setBorder(border);

		this.portsList = new JComboBox();

		ComboBoxListPortAdapter adapter = new ComboBoxListPortAdapter(
				this.portsList);

		this.connectButton = new JButton(buttonTitle);
		this.connectButton.setPreferredSize(new Dimension(80, 20));

		this.portsList.addPopupMenuListener(adapter);
		this.portsList.setPreferredSize(new Dimension(180, 20));

		connectionPanel.add(this.portsList);
		connectionPanel.add(this.connectButton);

		return connectionPanel;
	}

	/*
	 * private void initPane() { this.sendButton = new JButton("Send Command");
	 * this.add(this.sendButton);
	 * 
	 * this.connectButton = new JButton("Connect");
	 * this.add(this.connectButton);
	 * 
	 * 
	 * this.portsList.setSelectedIndex(4); this.add(new
	 * JLabel("This is text that goes above the ComboBox:"));
	 * this.add(this.portsList); }
	 */
	public void addActionListener(ActionListener _listener) {
		this.sendButton.addActionListener(_listener);
		this.sendButton.setActionCommand(ViewConstants.BUTTON_SEND);

		this.connectButton.addActionListener(_listener);
		this.connectButton
				.setActionCommand(ViewConstants.CONNECT_ACTION_COMAND);

	}

	public void addComoBoxListener(PopupMenuListener _listener) {
		this.portsList.addPopupMenuListener(_listener);
		this.portsList.setActionCommand(ViewConstants.COMBOBOX_PORTS);
	}

	public String getPortName() {
		return (String) this.portsList.getSelectedItem();
	}

	public void setListPorts(ArrayList<String> ports) {
		this.listPorts = ports;
	}

	@Override
	public void statusChanged(boolean _connected) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				toggleConnection(_connected);
			}
		});

	}

	public void toggleConnection(boolean connection) {
		if (connection) {
			this.connectButton.setText(ViewConstants.BUTTON_CLOSE_LABEL);
			this.connectButton
					.setActionCommand(ViewConstants.DISCONNECT_ACTION_COMAND);
			this.commandsPanel.setVisible(true);
		} else {
			this.connectButton.setText(ViewConstants.BUTTON_CONNECT_LABEL);
			this.connectButton
					.setActionCommand(ViewConstants.CONNECT_ACTION_COMAND);
			this.commandsPanel.setVisible(false);
		}
	}
}
