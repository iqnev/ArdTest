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

import java.io.IOException;
import java.util.TooManyListenersException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

/**
 * 
 * @author Ivelin Yanev <qnev89@gmail.com>
 * @since 2015
 */
public class CommunicationMain {

	public static void main(String[] args) { 
	
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					new CommunicationController();
				} catch (IOException ex) {
					Logger.getLogger(CommunicationMain.class.getName()).log(
							Level.SEVERE, null, ex);
				} catch (TooManyListenersException ex) {
					Logger.getLogger(CommunicationMain.class.getName()).log(
							Level.SEVERE, null, ex);
				}
			}
		});
	}

}
