/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testcomunication;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

/**
 *
 * @author iqnev
 */
public class ComPort {
     private static ComPort instance;
  
  /**
   * @return the Singleton instance of {@code ComPortFactory}.
   */
  public static ComPort getInstance() {
    if (ComPort.instance == null) {
      ComPort.instance = new ComPort();
    }
    
    return ComPort.instance;
  }
  
  private ComPort() {
    
  }
  
	/**
	 * @return a {@code List} of {@code CommPortIdentifier}s representing the 
	 *     available COM Ports.
	 */
	public static List<CommPortIdentifier> getAvailableComPorts() {
		Enumeration<?>           foundIds;
		List<CommPortIdentifier> identifiers;
		
		foundIds     = CommPortIdentifier.getPortIdentifiers();
		identifiers  = (List<CommPortIdentifier>) Collections.list(foundIds);
		
		return identifiers;
	}
	
	/**
	 * @return a {@code List} of {@code String}s representing the names of 
	 *     the available COM Ports.
	 */
	public static List<String> getAvailableComPortNames() {
	  List<CommPortIdentifier> comPorts;
	  List<String>             comPortNames;
	  
	  comPorts     = ComPort.getAvailableComPorts();
	  comPortNames = new ArrayList<String>();
	  
	  for (CommPortIdentifier portId : comPorts) {
	    comPortNames.add(portId.getName());
	  }
	  
	  return comPortNames;
	}
	
	/**
	 * @param _portType the {@code ComPortType} to filter with.
	 * @return a {@code List} of {@code CommPortIdentifier}s which are of the
	 *     specified type.
	 */
	public List<CommPortIdentifier> getAvailableComPorts(ComPortType _portType) {
	  List<CommPortIdentifier> comPorts;
	  ComPortType              type;
	  
	  if (_portType == null) {
	    throw new NullPointerException("Port type is null"); //$NON-NLS-1$
	  }
	  
	  comPorts = ComPort.getAvailableComPorts();
	  
	  for (CommPortIdentifier port : comPorts) {
	    
	    type = ComPortType.fromInt(port.getPortType());
	    if (type != _portType) {
	      comPorts.remove(port);
	    }
	  }
	  
	  return comPorts;
	}
	
	/**
	 * Creates a {@code ComPortIdentifier} from the specified COM Port name.
	 * 
	 * @param _comName name of the COM Port.
	 * @return the {@code ComPortIdentifier} object.
	 */
	@SuppressWarnings("nls")
  public static CommPortIdentifier createComPortIdentifier(String _comName) {
		
		if (_comName == null || _comName.length() == 0) {
			throw new IllegalArgumentException("Comport name is null or empty");
		}
		try {
      return CommPortIdentifier.getPortIdentifier(_comName);
    }
    catch (NoSuchPortException _e) {
     return null;
    }
	}

}
