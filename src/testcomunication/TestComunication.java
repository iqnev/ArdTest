/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testcomunication;

import java.io.IOException;

/**
 *
 * @author iqnev
 */
public class TestComunication {
    public static void main(String[] args) throws IOException, Exception {
        SerialCommunication serialCommunication = SerialCommunication.getInstance();
        serialCommunication.connect("/dev/tty.usbserial-A9007UX1");
       serialCommunication.sendMessage("test");
       
       serialCommunication.close();
    }
}
