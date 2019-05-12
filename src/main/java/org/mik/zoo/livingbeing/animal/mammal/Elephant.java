package org.mik.zoo.livingbeing.animal.mammal;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.mik.zoo.livingbeing.Livingbeing;
import org.mik.zoo.livingbeing.animal.AnimalType;

public class Elephant extends AbstractMammal {

	public static final String SCIENTIFIC_NAME = "loxodonta africana"; //$NON-NLS-1$
	public static final String IMAGE_URI = "https://d3p157427w54jq.cloudfront.net/uploads/2017/07/dumbo-619-386.jpg"; //$NON-NLS-1$
	public static final int NUMBER_OF_TEETH = 26;
	public static final int AVERAGE_WEIGHT = 5000;
	public static final int HAIR_LENGTH = 8;

	public Elephant() {
		this("");
	}

	public Elephant(ResultSet rs) throws SQLException {
		super(rs);
	}

	public Elephant(String instanceName) {
		super(null, SCIENTIFIC_NAME, instanceName, IMAGE_URI, 4, NUMBER_OF_TEETH, AVERAGE_WEIGHT, AnimalType.HERBIVOROUS,
				HAIR_LENGTH);
	}

	public static Livingbeing createFromResultSet(ResultSet rSet) throws SQLException {
		return new Elephant(rSet);
	}

	@Override
	public String toString() {
		return "Elephant:" + super.toString();
	}

}
