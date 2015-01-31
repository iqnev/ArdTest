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
public class GetSensorData extends Command{
    
    private byte data = 0;
    
    public GetSensorData() {
        super(CommandIndeficator.Sensor);
    }

    @Override
    public byte getData() {
       return data;
    }
    
    /**
     * set data which you want to sent together with command
     * @param data 
     */
    public void setData(byte data) {
        this.data = data;
    } 
    
}
