/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testcomunication;

/**
 *
 * @author iqnev
 */
public class SerialCommunication {
    
    private SerialCommunication() {
    }
    
    public static SerialCommunication getInstance() {
        return SerialCommunicationHolder.INSTANCE;
    }
    
    private static class SerialCommunicationHolder {

        private static final SerialCommunication INSTANCE = new SerialCommunication();
    }
}
