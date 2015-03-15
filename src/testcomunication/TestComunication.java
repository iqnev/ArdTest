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
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.TooManyListenersException;
import static sun.security.krb5.Confounder.bytes;

/**
 *
 * @author iqnev
 */
public class TestComunication implements SerialPortEventListener{

    private Connection connection;
    private SerialClassConnection serialClassConnection;

    public TestComunication() throws IOException, TooManyListenersException {
        this.serialClassConnection = SerialClassConnection.getInstance();    
    }
    
    //register connection change status observer
    public void addCannectionStatusListener(ConnectionStatus connectionStatus) {
        this.serialClassConnection.registerChangedListener(connectionStatus);
    }
    
    public boolean openPort(String portName) throws NoSuchPortException, TooManyListenersException {     
        if (portName == null || portName.isEmpty()) {
            throw new NullPointerException("Portname is null");
        }
        CommPortIdentifier port =  CommPortIdentifier.getPortIdentifier(portName);
        
        this.serialClassConnection.openPort(port);
        this.serialClassConnection.addSerialEventListener(this);
        return true;
    }
    
    public void sendComand(Command cmd) throws IOException {
        byte[] cmdData;
        cmdData = cmd.getData();
        this.serialClassConnection.write(cmdData);
    }

    //Example how you can make command and send it
   /* public static void main(String[] args) throws IOException, TooManyListenersException {     
        
        TestComunication testComunication = new TestComunication();
        
        Command cmd = new GetSensorData("hellow");
        
        testComunication.sendComand(cmd);
    

    } */

    @Override
    public void serialEvent(SerialPortEvent spe) {
        if (spe.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
                try {
                    BufferedReader inputLine = this.serialClassConnection.inputeBlock();
                    final String test = inputLine.readLine();
                    System.out.println(test);
                } catch (Exception e) {
                    System.err.println(e.toString());
                }
            }
     
    }
}
