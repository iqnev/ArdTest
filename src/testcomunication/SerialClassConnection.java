/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testcomunication;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.TooManyListenersException;

/**
 *
 * @author iqnev
 */
public class SerialClassConnection extends Connection {

    private static final int DEFAULT_TIME_OUT = 2000;
    private static final int DEFAULT_DATA_RATE = 9600;
    private static SerialClassConnection instance;

    private SerialPort serialPort;
    private DataInputStream inputStream;
    private OutputStream outputStream;
    private int timeOut;
    private int dataRate;

    /**
     *
     */
    private SerialClassConnection() {
        this.timeOut = DEFAULT_TIME_OUT;
        this.dataRate = DEFAULT_DATA_RATE;
    }

    /**
     * set dataRade of the serial connection
     *
     * @param dataRate
     */
    public void setDataRate(int dataRate) {
        this.dataRate = dataRate;
    }

    /**
     * set timeOut of serial connection
     *
     * @param timeOut
     */
    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    public static SerialClassConnection getInstance() {
        if (instance == null) {
            instance = new SerialClassConnection();
        }
        return instance;
    }

    public boolean openPort(CommPortIdentifier commId) throws TooManyListenersException {
        if (commId == null) {
            throw new NullPointerException("Com port null");
        }

        if (commId.isCurrentlyOwned()) {
            throw new IllegalStateException("Com port in use");
        }

        try {
            this.serialPort = (SerialPort) commId.open(SerialClassConnection.class.getName(), this.timeOut);

            this.serialPort.setSerialPortParams(this.dataRate, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            
          
            this.serialPort.notifyOnDataAvailable(true);
            this.serialPort.notifyOnOutputEmpty(true);
        } catch (UnsupportedCommOperationException | PortInUseException _e) {
            _e.printStackTrace();
        }
        try {
            this.inputStream = new DataInputStream(this.serialPort.getInputStream());
            this.outputStream = this.serialPort.getOutputStream();
            this.addSerialEventListener((SerialPortEventListener) this.serialPort);

        } catch (IOException e) {
            return false;
        }

        this.notifyListeners();

        return true;
    }
    
    @Override
     public BufferedReader inputeBlock() throws IOException {
         return new BufferedReader(new InputStreamReader(this.serialPort.getInputStream()));
     }

    public void addSerialEventListener(SerialPortEventListener _listener) throws TooManyListenersException {
        if (_listener == null) {
            throw new NullPointerException("Listener is null"); //$NON-NLS-1$
        }
        this.serialPort.addEventListener(_listener);
    }

    @Override
    public boolean isDataAvailable() {
        try {
            if (this.inputStream.available() != 0) {
                return true;
            } else {
                return false;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
            // Logger.getLogger(SerialClassConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getAvailableBytes() throws IOException {
        return this.inputStream.available();
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
       @Override
    public void test() throws IOException {
        /*private*/ 
        BufferedReader inputTest;
        String inputLine;
        
        inputTest = new BufferedReader(new InputStreamReader(this.serialPort.getInputStream()));                           
                System.out.println(inputTest.readLine());
    }

    @Override
    public byte[] readBlocked(int num) throws IOException {
        byte[] buff;
        buff = new byte[num];
        
       this.inputStream.readFully(buff, 0, num);

        return buff;
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void write(byte[] bytes) throws IOException {
        if (bytes == null) {
            throw new NullPointerException("Byte buffer is empty");
        }

        this.outputStream.write(bytes);

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean close() {
        if (!this.isConnected()) {
            throw new IllegalStateException("Connection already closed");
        }
        this.serialPort.removeEventListener();
        this.serialPort.close();

        Utilities.closeConnection(this.inputStream);
        Utilities.closeConnection(this.outputStream);
        this.notifyListeners();
        return true;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
   
 

}
