import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;



public class CommunicationView extends JPanel {
  
  public static final String BUTTON_SEND = "BTN_SEND";
  
  private JButton sendButton;
  
  
  public CommunicationView() {
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    this.initPane();
    
  }
  
  private void initPane() {
    this.sendButton = new JButton("Send Command");
    this.add(this.sendButton);
  }
  
  public void addActionListener(ActionListener _listener) {
    this.sendButton.addActionListener(_listener);
    this.sendButton.setActionCommand(BUTTON_SEND);
  }

}
