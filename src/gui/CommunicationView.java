package gui;

import helpers.ComboBoxListPortAdapter;
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
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.PopupMenuListener;
import testcomunication.ConnectionStatus;

public class CommunicationView extends JPanel implements ConnectionStatus{

    private JButton sendButton;
    private JButton connectButton;
    private JComboBox portsList;
    private ArrayList<String> listPorts;

    public CommunicationView() {
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(this.createConnectionPanel());

        this.add(this.createCommandSendPanel());
        this.add(Box.createRigidArea(new Dimension(0, 100)));
    }

    private JPanel createCommandSendPanel() {
        JPanel commandsPanel;
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
        buttonTitle = ViewConstants.BUTTON_CONNECT;
        connectionPanel = new JPanel();

        border = BorderFactory.createTitledBorder(borderTitle);

        connectionPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0));
        connectionPanel.setBorder(border);

        this.portsList = new JComboBox();

        ComboBoxListPortAdapter adapter = new ComboBoxListPortAdapter(this.portsList);

        this.portsList.addPopupMenuListener(adapter);

        this.connectButton = new JButton(buttonTitle);
        this.portsList.setPreferredSize(new Dimension(80, 20));

        connectionPanel.add(this.portsList);
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
      //  this.sendButton.addActionListener(_listener);
        //   this.sendButton.setActionCommand(BUTTON_SEND);

        this.connectButton.addActionListener(_listener);
        this.connectButton.setActionCommand(ViewConstants.BUTTON_CONNECT);

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
           System.out.println(_connected);
    }
}
