/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testcomunication;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEventListener;
import java.io.InputStream;
import java.io.OutputStream;
import gnu.io.CommPort;
import java.io.IOException;

/**
 *
 * @author iqnev
 */
public class SerialCommunication {

    private static SerialCommunication instance = null;
    private static boolean coonected = false;
    private SerialPort serialPort;

    /**
     * Milliseconds to block while waiting for port open
     */
    private static final int TIME_OUT = 2000;
    /**
     * Default bits per second for COM port.
     */
    private static final int DATA_RATE = 9600;

    public static SerialCommunication getInstance() {
        if (instance == null) {
            instance = new SerialCommunication();
        }

        return instance;
    }

    public void connect(String portName) throws Exception {
        CommPortIdentifier portIdentifier = CommPortIdentifier
                .getPortIdentifier(portName);
        if (portIdentifier.isCurrentlyOwned()) {
            System.out.println("Error: Port is currently in use");
        } else {

            CommPort commPort = portIdentifier.open(this.getClass().getName(),
                    TIME_OUT);

            if (commPort instanceof SerialPort) {
                serialPort = (SerialPort) commPort;
                serialPort.setSerialPortParams(DATA_RATE,
                        SerialPort.DATABITS_8,
                        SerialPort.STOPBITS_1,
                        SerialPort.PARITY_NONE);

                // open the streams
                //     InputStream in = serialPort.getInputStream();
                //       OutputStream out = serialPort.getOutputStream();
                //    (new Thread(new SerialReader(in))).start();
                //  (new Thread(new SerialWriter(out))).start();
            } else {
                System.out
                        .println("Error: Only serial ports are handled by this example.");
            }
        }

    }

    /**
     *
     * @param msg
     * @throws IOException
     */
    public void sendMessage(String msg) throws IOException {
        SerialWriter writer = new SerialWriter(serialPort.getOutputStream());
        writer.setMessage(msg);
        (new Thread(writer)).start();
    }
    
    /**
     * 
     */
    public void close() {
        if (serialPort != null) {
            serialPort.removeEventListener();
            serialPort.close();
        }
    }
}
