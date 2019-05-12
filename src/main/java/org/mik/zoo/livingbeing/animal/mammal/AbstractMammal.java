package org.mik.zoo.livingbeing.animal.mammal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mik.zoo.livingbeing.animal.AbstractAnimal;
import org.mik.zoo.livingbeing.animal.AnimalType;

public abstract class AbstractMammal extends AbstractAnimal implements Mammal {

	private static List<Mammal> allMammals = new ArrayList<>();

	private int hairLength;

	public AbstractMammal() {
	}

	public AbstractMammal(Integer id, String scientificName, String instanceName, String imageURI, int numberOfLegs,
			int numberOfTeeth, int weight, AnimalType animalType, int hairLength) {
		super(id, scientificName, instanceName, imageURI, numberOfLegs, numberOfTeeth, weight, animalType);
		this.hairLength = hairLength;
	}

	public AbstractMammal(ResultSet rs) throws SQLException {
		super(rs);
		this.hairLength = rs.getInt(COL_HAIR_LENGTH);
		if (!allMammals.contains(this))
			allMammals.add(this);
	}

	@Override
	public int getHairLength() {
		return this.hairLength;
	}

	public void setHairLength(int hairLength) {
		this.hairLength = hairLength;
	}

	@Override
	public boolean isMammal() {
		return true;
	}

	@Override
	public String getUpdateSql() {
		return String.format("%s, %s=%d", super.getUpdateSql(), //$NON-NLS-1$
				COL_HAIR_LENGTH, Integer.valueOf(getHairLength()));
	}

	@Override
	public void getInsertSql(StringBuilder fields, StringBuilder values) {
		super.getInsertSql(fields, values);
		fields.append(',').append(COL_HAIR_LENGTH);
		values.append(',').append(getHairLength());
	}

	public static List<Mammal> getAllMammals() {
		return allMammals;
	}

	public static void setColumnDefinitions() {
		columnDefinitions.put(COL_HAIR_LENGTH, COL_TYPE_INTEGER);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + this.hairLength;
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
		AbstractMammal other = (AbstractMammal) obj;
		if (this.hairLength != other.hairLength)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return super.toString() + ", hairLength=" + this.hairLength; //$NON-NLS-1$ //$NON-NLS-2$
	}

}
