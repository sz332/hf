package org.mik.zoo.livingbeing.animal.mammal;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.mik.zoo.livingbeing.Livingbeing;
import org.mik.zoo.livingbeing.animal.AnimalType;

public class AfricanBuffalo extends AbstractMammal {
    public final static String SCIENTIFIC_NAME = "Syncerus caffer";  //$NON-NLS-1$
    private final static int AVERAGE_WEIGHT = 600;
	public static final String IMAGE_URI = "https://notesfromcamelidcountry.files.wordpress.com/2013/09/img_1712.jpg"; //$NON-NLS-1$
    private final static int NUMBER_OF_TEETH = 46;
    private final static int HAIR_LENGTH = 5;
    
	public AfricanBuffalo() {
		this("");
	}

	public AfricanBuffalo(ResultSet rs) throws SQLException {
		super(rs);
	}

	public AfricanBuffalo(String instanceName) {
		super(null, SCIENTIFIC_NAME, instanceName, IMAGE_URI, 4, 
			  NUMBER_OF_TEETH, AVERAGE_WEIGHT, AnimalType.HERBIVOROUS, HAIR_LENGTH);
	}

	public static Livingbeing createFromResultSet(ResultSet rSet) throws SQLException {
		return new AfricanBuffalo(rSet);
	}

	@Override
	public String toString() {
		return "African buffalo:" + super.toString();
	}

}
