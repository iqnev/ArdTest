import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;



public class CommunicationController implements ActionListener {
  
  private JFrame            frame;
  private CommunicationView view;
  
  //private TestCommunication communication;
  
  public CommunicationController() {
    this.frame = new JFrame("Test Communication");
    this.frame.setSize(new Dimension(300, 200));
    this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.view  = new CommunicationView();
    this.view.addActionListener(this);
    
    
    // initialize connection here
    //this.connection = new Connection()
    
    this.frame.getContentPane().add(this.view);
    this.frame.setVisible(true);
  }
  
  private void sendCommand() {
    // send command here
    System.out.println("SendCommand");
    // write the command bytes
    //this.connection.writeBytes()
  }

  @Override
  public void actionPerformed(ActionEvent _event) {
    String actionCommand;
    
    actionCommand = _event.getActionCommand();
    
    if (actionCommand.equals(this.view.BUTTON_SEND)) {
      this.sendCommand();
    }
   
  }

}
