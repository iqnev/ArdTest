package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class CommunicationView extends JPanel {

    //TODO move in ViewConstants
    public static final String BUTTON_SEND = "BTN_SEND";
    public static final String BUTTON_CONNECT = "BTN_CONNECT";
    public static final String BOX_LIST_PORT = "BOX_LIST_PORT";

    private JButton sendButton;
    private JButton connectButton;
    private JComboBox portsList;

    public CommunicationView() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(this.createConnectionPanel());
        this.add(Box.createRigidArea(new Dimension(0, 300)));
    }

    private JPanel createConnectionPanel() {
        JPanel connectionPanel;
        Border border;
        String borderTitle;
        String buttonTitle;

        borderTitle = ViewConstants.CONNECTION_BORDER;
        buttonTitle = ViewConstants.BUTTON_CONNECT;
        connectionPanel = new JPanel();
       
        border = BorderFactory.createTitledBorder(borderTitle);

        connectionPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0));
        
        connectionPanel.setBorder(border);

        //TODO fill @portsList with port name
        String[] petStrings = {"Bird", "Cat", "Dog", "Rabbit", "Pig"};
        this.portsList = new JComboBox(petStrings);
        this.connectButton = new JButton(buttonTitle);

        connectionPanel.add(this.portsList);
        //connectionPanel.add(Box.createRigidArea(new Dimension(0, 0)));
        connectionPanel.add(this.connectButton);

        return connectionPanel;
    }

    /*
     private void initPane() {
     this.sendButton = new JButton("Send Command");
     this.add(this.sendButton);
    
     this.connectButton = new JButton("Connect");
     this.add(this.connectButton);
    
    
     this.portsList.setSelectedIndex(4);
     this.add(new JLabel("This is text that goes above the ComboBox:"));
     this.add(this.portsList);
     } */
    public void addActionListener(ActionListener _listener) {
       // this.sendButton.addActionListener(_listener);
       // this.sendButton.setActionCommand(BUTTON_SEND);

        this.connectButton.addActionListener(_listener);
        this.connectButton.setActionCommand(ViewConstants.BUTTON_CONNECT);

    }

    public String getPortName() {
        return (String) this.portsList.getSelectedItem();
    }

}
