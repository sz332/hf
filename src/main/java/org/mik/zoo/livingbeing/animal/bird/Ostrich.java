package org.mik.zoo.livingbeing.animal.bird;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.mik.zoo.livingbeing.animal.AnimalType;

public class Ostrich extends AbstractBird {

    public static final String SCIENTIFIC_NAME = "Struthio camelus"; //$NON-NLS-1$
    public static final String IMAGE_URI="https://s.hswstatic.com/gif/ostrich.jpg";
    private static final int AVERAGE_WEIGHT = 150;
    private static final int WING_LENGTH = 200;
    

    public Ostrich() {
        super(null, SCIENTIFIC_NAME, "", IMAGE_URI, 0, 0, 
          	  AVERAGE_WEIGHT, AnimalType.HERBIVOROUS, WING_LENGTH);            
    }

    public Ostrich(ResultSet rs) throws SQLException {
            super(rs);
    }
    
    public Ostrich(String instanceName) {
        super(null, SCIENTIFIC_NAME, instanceName, IMAGE_URI, 0, 0, 
        	  AVERAGE_WEIGHT, AnimalType.HERBIVOROUS, WING_LENGTH);
    }
    
    public static Ostrich createFromResultSet(ResultSet rSet)  throws SQLException {
    	return new Ostrich(rSet);
    }
    
	@Override
	public String toString() {
		return "Ostrich:" + super.toString();
	}

}
