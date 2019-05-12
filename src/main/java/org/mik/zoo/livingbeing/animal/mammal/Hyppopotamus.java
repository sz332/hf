package org.mik.zoo.livingbeing.animal.mammal;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.mik.zoo.livingbeing.Livingbeing;
import org.mik.zoo.livingbeing.animal.AnimalType;

public class Hyppopotamus extends AbstractMammal {

    public final static String SCIENTIFIC_NAME = "Hippopotamus amphibius"; //$NON-NLS-1$
    private final static int NUMBER_OF_TEETH = 32;
    private final static int AVERAGE_WEIGHT = 1700;
    private final static int HAIR_LENGTH = 0;
	public static final String IMAGE_URI = "http:// "; //$NON-NLS-1$

	public Hyppopotamus() {
	}

	public Hyppopotamus(ResultSet rs) throws SQLException {
		super(rs);
	}

	public Hyppopotamus(Integer id, String instanceName) {
		super(id, SCIENTIFIC_NAME, instanceName, IMAGE_URI, 4, NUMBER_OF_TEETH, AVERAGE_WEIGHT, AnimalType.HERBIVOROUS,
				HAIR_LENGTH);
	}

	public static Livingbeing createFromResultSet(ResultSet rSet) throws SQLException {
		return new Hyppopotamus(rSet);
	}

	@Override
	public String toString() {
		return "Hyppopotamus:" + super.toString();
	}

    
}
