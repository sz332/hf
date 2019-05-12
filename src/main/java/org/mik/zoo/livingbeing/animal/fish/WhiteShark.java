package org.mik.zoo.livingbeing.animal.fish;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.mik.zoo.livingbeing.animal.AnimalType;

public class WhiteShark extends AbstractFish {

	public static final String SCIENTIFIC_NAME = "Carcharodon carcharias"; //$NON-NLS-1$
	public static final String IMAGE_URI = "http://ipfactly.com/wp-content/uploads/2013/03/great_white_shark_main.jpg";
	private static final int ROWS_OF_TEETH = 5;
	private static final int NUMBER_OF_TEETH_PER_ROWS = 48;
	private static final int NUMBER_OF_TEETH = NUMBER_OF_TEETH_PER_ROWS * ROWS_OF_TEETH;
	private static final int AVERAGE_WEIGHT = 2000;
	private static final int MAX_DEEP = 900;

	public WhiteShark() {
		this(""); //$NON-NLS-1$
	}

	public WhiteShark(ResultSet rs) throws SQLException {
		super(rs);
	}

	public WhiteShark(String instanceName) {
		super(null, SCIENTIFIC_NAME, instanceName, IMAGE_URI, 0, 
			  NUMBER_OF_TEETH,AVERAGE_WEIGHT, AnimalType.PREDATOR, MAX_DEEP);
	}
	
	public static WhiteShark createFromResultSet(ResultSet rs) throws SQLException {
		return new WhiteShark(rs);
	}
	
	@Override
	public String toString() {
		return "White shark:" + super.toString();
	}
}
