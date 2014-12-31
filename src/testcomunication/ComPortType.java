/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testcomunication;

import gnu.io.CommPortIdentifier;

/**
 *
 * @author iqnev
 */
public enum ComPortType {
  
  /**
   * Represents Serial (RS232) COM Port.
   */
  SERIAL(CommPortIdentifier.PORT_SERIAL),
  
  /**
   * Represents a parralel (Centronics) Port.
   */
  PARALLEL(CommPortIdentifier.PORT_PARALLEL),
  
  /**
   * Represents I2C (Inter-Integrated Circuit) Port.
   */
  I2C(CommPortIdentifier.PORT_I2C),
  
  /**
   * Represent RS485 Port.
   */
  RS485(CommPortIdentifier.PORT_RS485),
  
  /**
   * Unknown type.
   */
  UNKNOWN(555);
  
  private int type;
  
  private ComPortType(int _type) {
    this.type = _type;
  }
  
  /**
   * @return the {@code int} representing this {@code ComPortType.}
   */
  public int getInt() {
    return this.type;
  }
  
  /**
   * @param _type an {@code int} value.
   * @return the {@code ComPortType} representing the specified {@code int}.
   */
  public static ComPortType fromInt(int _type) {
    for (ComPortType type : ComPortType.values())  {
      if (type.getInt() == _type) {
        return type;
      }
    }
    
    return ComPortType.UNKNOWN;
  }
}
