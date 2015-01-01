/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testcomunication;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.TooManyListenersException;
import static sun.security.krb5.Confounder.bytes;

/**
 *
 * @author iqnev
 */
public class TestComunication implements SerialPortEventListener{

    private Connection connection;
    //private CommPortIdentifier port;
    private static final String PORT_NAMES[] = {
        "/dev/tty.usbserial-A9007UX1", // Mac OS X
        "/dev/ttyACM0", // Raspberry Pi
        "/dev/ttyUSB0", // Linux
        "COM3", // Windows
        "/dev/tty.usbmodem621",
        "/dev/tty.usbmodem411"
    };

    public TestComunication() throws IOException, TooManyListenersException {

        SerialClassConnection serialClassConnection = null;
        CommPortIdentifier port = null;

        serialClassConnection = SerialClassConnection.getInstance();
        Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

        //First, Find an instance of serial port as set in PORT_NAMES.
        while (portEnum.hasMoreElements()) {
            CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
            for (String portName : PORT_NAMES) {
                if (currPortId.getName().equals(portName)) {
                    port = currPortId;
                    break;
                }
            }
        }

        if (port == null) {
            System.out.println("Could not find COM port.");
            return;
        }
        System.out.println(port.getName());

        serialClassConnection.openPort(port);

        this.connection = serialClassConnection;
      //  serialClassConnection.addSerialEventListener((SerialPortEventListener) this.connection);
      //  this.manageData(this.connection);
    }

    private void manageData(Connection conn) throws IOException {

        Connection connection;
        int availableBytes;
        byte[] inBytes;

        connection = conn;
        // listen forever for incoming data

        while (true) {
            if (connection.isDataAvailable()) {
                connection.test();
                    inBytes = connection.readBlocked(11);
                    String text = new String(inBytes, "UTF-8");
                    System.out.println(text);
            }

        }
    }

    public static void main(String[] args) throws IOException, TooManyListenersException {

        new TestComunication();
        Thread t = new Thread() {
            public void run() {
                //the following line will keep this app alive for 1000 seconds,
                //waiting for events to occur and responding to them (printing incoming messages to console).
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ie) {
                }
            }
        };
        t.start();
        System.out.println("Started");

    }

    @Override
    public void serialEvent(SerialPortEvent spe) {
        if (spe.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
                try {
                    BufferedReader inputLine = this.connection.inputeBlock();
                    final String test = inputLine.readLine();
                    System.out.println(test);
                } catch (Exception e) {
                    System.err.println(e.toString());
                }
            }
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
