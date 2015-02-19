/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 * @author iqnev
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
            CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
                ports.add(currPortId.getName());
         }
         
        model = new DefaultComboBoxModel(ports.toArray());

        if (ports.size() == 0) {
            model = new DefaultComboBoxModel(new String[]{ViewConstants.NO_COMM_PORT});
        }
        _comboBox.setModel(model);
    }

}
