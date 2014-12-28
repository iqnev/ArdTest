/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testcomunication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author iqnev
 */
public abstract class Connection {
    private ArrayList<ConnectionStatus> listeners;
    private boolean isConnected;
    
    /**
     * 
     */
    protected Connection() {
        this.listeners = new ArrayList<>();
    }
    
    /**
     * 
     */
    public void notifyListeners() {
        for(ConnectionStatus listener : listeners) {
            listener.statusChanged(this.isConnected);
        }
    }
    
    /**
     * 
     * @return true if the connection is connected
     */
    public boolean isConnected() {
    return this.isConnected;
  }
    
    /**
     * 
     * @param obj 
     */
    public void registerChangedListener(ConnectionStatus obj) {
        if(obj != null) {
            this.listeners.add(obj);
        } else {
            throw new NullPointerException("Null Observer");
        }
    }
    
    /**
     * 
     * @return 
     */
    public abstract boolean isDataAvailable();
    
    /**
     * 
     * @return
     * @throws IOException 
     */
    public abstract int getAvailableBytes() throws IOException;
    
    /**
     * 
     * @param num
     * @return
     * @throws IOException 
     */
    public abstract byte[] readBlocked(int num) throws IOException;
    
    /**
     * 
     * @param bytes
     * @throws IOException 
     */
    public abstract void write(byte[] bytes) throws IOException; 
    
    public abstract boolean close();
}
