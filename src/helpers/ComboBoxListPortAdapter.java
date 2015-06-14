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
package helpers;

import gnu.io.CommPortIdentifier;
import gui.ViewConstants;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

/**
 * 
 * @author Ivelin Yanev <qnev89@gmail.com>
 * @since 2015
 */
public class ComboBoxListPortAdapter implements PopupMenuListener {

	/**
	 * Default constructor that initializes with the ComboBox which events this
	 * {@code PopupMenuListener} will be listening for.
	 *
	 * @param _comboBox
	 */
	public ComboBoxListPortAdapter(JComboBox _comboBox) {
		if (_comboBox == null) {
			throw new NullPointerException("Combobox null");
		}
		_comboBox.setEditable(true);
		_comboBox.setFont(new Font("sansserif", Font.PLAIN, 12));
		this.populateComboBox(_comboBox);
		if (_comboBox.getItemCount() > 0) {
			_comboBox.setSelectedIndex(0);
		} else {
			_comboBox.setSelectedItem("COM Port");
		}

		_comboBox.invalidate();
	}

	@Override
	public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
		this.populateComboBox((JComboBox) e.getSource());
	}

	@Override
	public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {

	}

	@Override
	public void popupMenuCanceled(PopupMenuEvent e) {

	}

	private void populateComboBox(JComboBox _comboBox) {
		ComboBoxModel model;
		String[] comPortNames = null;

		Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();
		ArrayList<String> ports = new ArrayList<String>();

		while (portEnum.hasMoreElements()) {
			CommPortIdentifier currPortId = (CommPortIdentifier) portEnum
					.nextElement();
			ports.add(currPortId.getName());
		}

		model = new DefaultComboBoxModel(ports.toArray());

		if (ports.size() == 0) {
			model = new DefaultComboBoxModel(
					new String[] { ViewConstants.NO_COMM_PORT });
		}
		_comboBox.setModel(model);
	}

}
