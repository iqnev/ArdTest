package gui;
import java.io.IOException;
import java.util.TooManyListenersException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;



public class CommunicationMain {
  
  public static void main(String[] _args) {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
          try {
              new CommunicationController();
          } catch (IOException ex) {
              Logger.getLogger(CommunicationMain.class.getName()).log(Level.SEVERE, null, ex);
          } catch (TooManyListenersException ex) {
              Logger.getLogger(CommunicationMain.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
    });
  }
 
}
