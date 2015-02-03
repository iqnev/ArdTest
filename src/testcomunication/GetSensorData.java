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
    
    /**
     * 
     */
    private String sensorType;
    
    public GetSensorData(String data) {
        super(CommandIndeficator.Sensor);
        this.sensorType = data;
    }

    @Override
    public byte[] getData() {
       return this.sensorType.getBytes();
    }

}
