/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testcomunication;

import java.io.Closeable;
import java.io.IOException;

/**
 *
 * @author iqnev
 */
public class Utilities {

    public static void closeConnection(Closeable stream) {
        if (stream == null) {
            throw new NullPointerException("Null Inputstream");
        }

        try {
            stream.close();
        } catch (IOException _e) {}
    }
}
