/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testcomunication;

import gnu.io.CommPortIdentifier;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Enumeration;
import static sun.security.krb5.Confounder.bytes;

/**
 *
 * @author iqnev
 */
public class TestComunication {

    private Connection connection;
    //private CommPortIdentifier port;
    private static final String PORT_NAMES[] = {
        "/dev/tty.usbserial-A9007UX1", // Mac OS X
        "/dev/ttyACM0", // Raspberry Pi
        "/dev/ttyUSB0", // Linux
        "COM3", // Windows
        "/dev/tty.usbmodem621"
    };

    public TestComunication() throws IOException {
      //  System.setProperty("gnu.io.rxtx.SerialPorts", "/dev/ttyACM0");
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
        
        this.manageData(this.connection);
    }

    private void manageData(Connection conn) throws IOException {

        Connection connection;
        int availableBytes;
        byte[] inBytes;

        connection = conn;
        // listen forever for incoming data
      
     //   System.out.println(tekst.getBytes());
        while (true) {
            if (connection.isDataAvailable()) {
                // data is available and you can read now.
                availableBytes = connection.getAvailableBytes();
            //    if(availableBytes == 12) {
                     inBytes = connection.readBlocked(11);
       
                String text = new String(inBytes, "UTF-8");
                System.out.println(text);
                }
               
           // }
        }
    }

    public static void main(String[] args) throws IOException {

        new TestComunication();
        
        /*  SerialCommunication serialCommunication = SerialCommunication.getInstance();
         serialCommunication.connect("/dev/tty.usbserial-A9007UX1");
         serialCommunication.sendMessage("test");
       
         serialCommunication.close(); */
    }
}
