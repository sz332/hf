package org.mik.zoo.livingbeing.animal.bird;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.mik.zoo.livingbeing.animal.AbstractAnimal;
import org.mik.zoo.livingbeing.animal.AnimalType;

public class AbstractBird extends AbstractAnimal implements Bird {

	private static List<Bird> allBirds = new ArrayList<>();

	private int wingLength;

	public AbstractBird() {
	}

	public AbstractBird(Integer id, String scientificName, String instanceName, String imageURI, int numberOfLegs,
			int numberOfTeeth, int weight, AnimalType animalType, int wingLength) {
		super(id, scientificName, instanceName, imageURI, numberOfLegs, numberOfTeeth, weight, animalType);
		this.wingLength = wingLength;
	}

	public AbstractBird(ResultSet rs) throws SQLException {
		super(rs);
		this.wingLength = rs.getInt(COL_WING_LENGTH);
		if (!allBirds.contains(this))
			allBirds.add(this);
	}

	@Override
	public int getWingLength() {
		return this.wingLength;
	}

	public void setWingLength(int wingLength) {
		this.wingLength = wingLength;
	}


	public int getNumberOfLegs() {
		return 2;
	}
	
	@Override
	public int getNumberOfTeeth() {
		return 0;
	}
	
    @Override
    public String getUpdateSql() {
            return String.format("%s, %s=%d", super.getUpdateSql(), //$NON-NLS-1$
                            COL_WING_LENGTH, Integer.valueOf(getWingLength()));
    }

    @Override
    public void getInsertSql(StringBuilder fields, StringBuilder values) {
            super.getInsertSql(fields, values);
            fields.append(',').append(COL_WING_LENGTH);
            values.append(',').append(getWingLength());
    }

    public static List<Bird> getAllBirds() {
       return Collections.unmodifiableList(allBirds);
    }

    public static void setColumnDefinitions(){
            columnDefinitions.put(COL_WING_LENGTH, COL_TYPE_INTEGER);
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + wingLength;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractBird other = (AbstractBird) obj;
		if (wingLength != other.wingLength)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return super.toString() + ", length of wings:" + this.wingLength;
	}

}
