/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testcomunication;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author iqnev
 */
public class TestDataLink implements DataLinkLayer {
   
    @Override
    public byte[] getRequestPackage(Command _command) {
        int frameLenght =7;
        int dataLenght;
        dataLenght = _command.getData().length;
        frameLenght += dataLenght;
        
        
        ByteBuffer buffer = ByteBuffer.allocate(frameLenght);

           // Create MessageDigest instance for MD5
     
           // MessageDigest md = MessageDigest.getInstance("MD5");
            
            //Add command bytes to digest
           // md.update(_command.getCommandIndeficator().getByte());
            buffer.put(FrameDelimeters.START);
            buffer.put(_command.getCommandIndeficator().getByte());
            buffer.putInt(dataLenght);
            buffer.put(_command.getData());
           // buffer.put(md.digest());
            buffer.put(FrameDelimeters.END);
  
        return buffer.array();
    }

    @Override
    public Packet parsePacket(Connection _connection) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
