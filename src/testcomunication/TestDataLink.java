/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testcomunication;

import java.nio.ByteBuffer;




/**
 *
 * @author iqnev
 */
public class TestDataLink implements DataLinkLayer{

    @Override
    public byte[] getRequestPackage(Command _command) {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put((byte)01); //Start of Heading
        buffer.put(_command.getCommandIndeficator());
        
        
        
        _command.getCommandIndeficator();
        //create  Frame
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Packet parsePacket(Connection _connection) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
