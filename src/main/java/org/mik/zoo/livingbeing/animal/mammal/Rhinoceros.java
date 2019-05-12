package org.mik.zoo.livingbeing.animal.mammal;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.mik.zoo.livingbeing.Livingbeing;
import org.mik.zoo.livingbeing.animal.AnimalType;

public class Rhinoceros extends AbstractMammal {

	public static final String SCIENTIFIC_NAME = "hippopotamus"; //$NON-NLS-1$
	public static final String IMAGE_URI = "https://assets.nst.com.my/images/articles/elrhino3.jpg_1496558109.jpg"; //$NON-NLS-1$
	public static final int NUMBER_OF_TEETH = 28;
	public static final int AVERAGE_WEIGHT = 2500;
	public static final int HAIR_LENGTH = 3;

	
	public Rhinoceros() {
		this("");
	}

	public Rhinoceros(ResultSet rs) throws SQLException {
		super(rs);
	}
	
	public Rhinoceros(String instanceName) {
		super(null, SCIENTIFIC_NAME, instanceName, IMAGE_URI, 4, 
				NUMBER_OF_TEETH, AVERAGE_WEIGHT, 
				AnimalType.HERBIVOROUS, HAIR_LENGTH);
	}

	public static Livingbeing createFromResultSet(ResultSet rSet) throws SQLException {
		return new Rhinoceros(rSet);
	}

	@Override
	public String toString() {
		return "Rhinoceros:" + super.toString();
	}

}
