import javax.swing.SwingUtilities;



public class CommunicationMain {
  
  public static void main(String[] _args) {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        new CommunicationController();
      }
    });
  }
 
}
