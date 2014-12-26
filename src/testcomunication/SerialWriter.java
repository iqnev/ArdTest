/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testcomunication;

import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author iqnev
 */
public class SerialWriter implements Runnable {
    OutputStream out;
    String message;

    public SerialWriter(OutputStream out) {
        this.out = out;
    }

    public void setMessage(String msg) {
        this.message = msg;
    }

    public void run() {
        try {
            if(message != null) {
                this.out.write(message.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}