package gui;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class CommunicationView extends JPanel {
  
  public static final String BUTTON_SEND = "BTN_SEND";
   public static final String BUTTON_CONNECT = "BTN_CONNECT";
  public static final String BOX_LIST_PORT = "BOX_LIST_PORT";
  
  private JButton sendButton;
  private JButton connectButton;
  private JComboBox portsList;
  
  
  public CommunicationView() {
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    this.initPane();
    
  }
  
  private void initPane() {
    this.sendButton = new JButton("Send Command");
    this.add(this.sendButton);
    
    this.connectButton = new JButton("Connect");
    this.add(this.connectButton);
    
    String[] petStrings = { "Bird", "Cat", "Dog", "Rabbit", "Pig" };
    
    this.portsList = new JComboBox(petStrings);
    this.portsList.setSelectedIndex(4);
    this.add(new JLabel("This is text that goes above the ComboBox:"));
    this.add(this.portsList);
  }
  
  public void addActionListener(ActionListener _listener) {
    this.sendButton.addActionListener(_listener);
    this.sendButton.setActionCommand(BUTTON_SEND);
    
    this.connectButton.addActionListener(_listener);
    this.connectButton.setActionCommand(BUTTON_CONNECT);
 
  }
  
  public String getPortName() {
      return (String) this.portsList.getSelectedItem();
  }

}
