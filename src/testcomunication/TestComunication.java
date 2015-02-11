/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testcomunication;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
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
    private  SerialClassConnection serialClassConnection;
    //private CommPortIdentifier port;
    private static final String PORT_NAMES[] = {
        "/dev/tty.usbserial-A9007UX1", // Mac OS X
        "/dev/ttyACM0", // Raspberry Pi
        "/dev/ttyUSB0", // Linux
        "COM3", // Windows
        "/dev/tty.usbmodem621",
       // "/dev/cu.usbmodem621",
        "/dev/tty.usbmodem411"
    };

    public TestComunication() throws IOException, TooManyListenersException {

       
        

        this.serialClassConnection = SerialClassConnection.getInstance();
        Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();
        
        
        serialClassConnection.addSerialEventListener(this);
        
        //First, Find an instance of serial port as set in PORT_NAMES.
      /*  while (portEnum.hasMoreElements()) {
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
     */ 
        
      
    }
    
    public boolean openPort(String portName) throws NoSuchPortException, TooManyListenersException {
        
        if (portName == null || portName.isEmpty()) {
            throw new NullPointerException("Portname is null");
        }
        CommPortIdentifier port =  CommPortIdentifier.getPortIdentifier(portName);
        
        this.serialClassConnection.openPort(port);
        
        return true;
    }
    
    public void sendComand(Command cmd) throws IOException {
        byte[] cmdData;
        cmdData = cmd.getData();
        this.connection.write(cmdData);
    }

    public static void main(String[] args) throws IOException, TooManyListenersException {     
        
        TestComunication testComunication = new TestComunication();
        
        Command cmd = new GetSensorData("hellow");
        
        testComunication.sendComand(cmd);
    

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
