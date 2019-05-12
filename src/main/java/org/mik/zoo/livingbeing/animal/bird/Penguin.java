package org.mik.zoo.livingbeing.animal.bird;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.mik.zoo.livingbeing.animal.AnimalType;

public class Penguin extends AbstractBird {

	public static final String IMAGE_URI = "https://kep.cdn.indexvas.hu/1/0/1254/12544/125447/12544735_4196b47b49e8f79f1d5ad90d5c5ef694_wm.jpg"; 
    private static final int AVERAGE_WEIGHT = 20;
    public static final String SCIENTIFIC_NAME = "Aptenodytes patagonicus";//$NON-NLS-1$
    private static final int WING_LENGTH = 50;
    

    public Penguin() {
            this(""); //$NON-NLS-1$
    }

    public Penguin(ResultSet rs) throws SQLException {
            super(rs);
    }

    public Penguin(String instanceName) {
            super(null, SCIENTIFIC_NAME, instanceName, IMAGE_URI, 
            		0,0, AVERAGE_WEIGHT, AnimalType.PREDATOR,  WING_LENGTH);
    }
    
    public static Penguin createFromResultSet(ResultSet rSet)  throws SQLException {
    	return new Penguin(rSet);
    }

	@Override
	public String toString() {
		return "Penguin:" + super.toString();
	}

}
