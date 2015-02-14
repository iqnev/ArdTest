package gui;

import gnu.io.NoSuchPortException;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TooManyListenersException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import testcomunication.Command;
import testcomunication.GetSensorData;
import testcomunication.TestComunication;

public class CommunicationController implements ActionListener {

    private JFrame frame;
    private CommunicationView view;

    private TestComunication communication;

    public CommunicationController() throws IOException, TooManyListenersException {
        this.frame = new JFrame("Test Communication");
        this.frame.setSize(new Dimension(500, 300));
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLocationRelativeTo(null);
        this.view = new CommunicationView();
        this.view.addActionListener(this);

        // initialize connection here
        this.communication = new TestComunication();
        ArrayList<String> listPorts = this.communication.getPortIdentifiers();
 
        this.view.setListPorts(listPorts);
        this.frame.getContentPane().add(this.view);
        this.frame.setVisible(true);
    }

    private void sendCommand() throws IOException {
        Command cmd = new GetSensorData("hellow");
        this.communication.sendComand(cmd);

    }

    @Override
    public void actionPerformed(ActionEvent _event) {
        String actionCommand;

        actionCommand = _event.getActionCommand();

        if (actionCommand.equals(this.view.BUTTON_SEND)) {
            try {
                this.sendCommand();
            } catch (IOException ex) {
                Logger.getLogger(CommunicationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (actionCommand.equals(ViewConstants.BUTTON_CONNECT)) {
            String portName = this.view.getPortName();

            try {
                this.communication.openPort(portName);
            } catch (NoSuchPortException e) {
                    System.out.println("Error");
            } catch (TooManyListenersException e) {
                    System.out.println("Error");
            }

            //update seccsesfull
            System.out.println(portName);
        }

    }

}
