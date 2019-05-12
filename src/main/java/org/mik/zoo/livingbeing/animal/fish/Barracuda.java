package org.mik.zoo.livingbeing.animal.fish;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.mik.zoo.livingbeing.animal.AnimalType;

public class Barracuda extends AbstractFish {

    public static final String SCIENTIFIC_NAME = "Sphyraena barracuda"; //$NON-NLS-1$
    public static final String IMAGE_URI = "http://www.oceanwideimages.com/images/14123/large/great-barracuda-24M1005-03.jpg";
    private static final int AVERAGE_WEIGHT = 44;
    private static final int NUMBER_OF_TEETH = 24;
    private static final int MAX_DEEP = 100;

	public Barracuda() {
		super(null, SCIENTIFIC_NAME, "", IMAGE_URI, 0, 
				  NUMBER_OF_TEETH,AVERAGE_WEIGHT, AnimalType.PREDATOR, MAX_DEEP);
	}

	public Barracuda(ResultSet rs) throws SQLException {
		super(rs);
	}

	public Barracuda(String instanceName) {
		super(null, SCIENTIFIC_NAME, instanceName, IMAGE_URI, 0, 
			  NUMBER_OF_TEETH,AVERAGE_WEIGHT, AnimalType.PREDATOR, MAX_DEEP);
	}
	
	public static Barracuda createFromResultSet(ResultSet rs) throws SQLException {
		return new Barracuda(rs);
	}

	@Override
	public String toString() {
		return "Barracuda:" + super.toString();
	}

}
