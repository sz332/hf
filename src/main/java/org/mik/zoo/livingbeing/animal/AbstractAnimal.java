package org.mik.zoo.livingbeing.animal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mik.zoo.livingbeing.AbstractLivingbeing;

public abstract class AbstractAnimal extends AbstractLivingbeing implements Animal {

	private static List<Animal> allAnimals = new ArrayList<>();
	private static Map<AnimalType, List<Animal>> mapsByEating = new HashMap<>();

	private int numberOfLegs;
	private int numberOfTeeth;
	private int weight;
	private AnimalType animalType;

	public AbstractAnimal() {
	}

	public AbstractAnimal(Integer id, String scientificName, String instanceName, String imageURI, int numberOfLegs,
			int numberOfTeeth, int weight, AnimalType animalType) {
		super(id, scientificName, instanceName, imageURI);
		this.numberOfLegs = numberOfLegs;
		this.numberOfTeeth = numberOfTeeth;
		this.weight = weight;
		this.animalType = animalType;
		registerAnimal(this);
	}

	public AbstractAnimal(ResultSet rs) throws SQLException {
		super(rs);
		this.animalType = AnimalType.fromInt(rs.getInt(COL_ANIMAL_TYPE));
		this.numberOfLegs = rs.getInt(COL_NUMBER_OF_LEGS);
		this.numberOfTeeth = rs.getInt(COL_NUMBER_OF_TEETH);
		this.weight = rs.getInt(COL_WEIGHT);
		registerAnimal(this);
	}

	@Override
	public int getNumberOfLegs() {
		return this.numberOfLegs;
	}

	public void setNumberOfLegs(int numberOfLegs) {
		this.numberOfLegs = numberOfLegs;
	}

	@Override
	public int getNumberOfTeeth() {
		return this.numberOfTeeth;
	}

	public void setNumberOfTeeth(int numberOfTeeth) {
		this.numberOfTeeth = numberOfTeeth;
	}

	@Override
	public int getWeight() {
		return this.weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public AnimalType getAnimalType() {
		return this.animalType;
	}

	public void setAnimalType(AnimalType animalType) {
		this.animalType = animalType;
	}

	@Override
	public boolean isAnimal() {
		return true;
	}

	public static List<Animal> getAllAnimals() {
		return allAnimals;
	}

	public static Map<AnimalType, List<Animal>> getMapsByEating() {
		return Collections.unmodifiableMap(mapsByEating);
	}

	public static void setColumnDefinitions() {
		columnDefinitions.put(COL_ANIMAL_TYPE, COL_TYPE_INTEGER);
		columnDefinitions.put(COL_NUMBER_OF_LEGS, COL_TYPE_INTEGER);
		columnDefinitions.put(COL_NUMBER_OF_TEETH, COL_TYPE_INTEGER);
		columnDefinitions.put(COL_WEIGHT, COL_TYPE_INTEGER);
	}

	private static void registerAnimal(Animal animal) {
		if (!allAnimals.contains(animal))
			allAnimals.add(animal);
		List<Animal> l = mapsByEating.get(animal.getAnimalType());
		if (l == null) {
			l = new ArrayList<>();
			mapsByEating.put(animal.getAnimalType(), l);
		}
		if (!l.contains(animal))
			l.add(animal);
	}

	@Override
	public String getUpdateSql() {
		return String.format("%s, %s=%d, %s=%d, %s=%d, %s=%d", super.getUpdateSql(), //$NON-NLS-1$
				COL_ANIMAL_TYPE, Integer.valueOf(getAnimalType().getValue()), COL_NUMBER_OF_LEGS,
				Integer.valueOf(getNumberOfLegs()), COL_NUMBER_OF_TEETH, Integer.valueOf(getNumberOfTeeth()),
				COL_WEIGHT, Integer.valueOf(getWeight()));
	}

	@Override
	public void getInsertSql(StringBuilder fields, StringBuilder values) {
		super.getInsertSql(fields, values);
		fields.append(',').append(COL_ANIMAL_TYPE).append(',').append(COL_NUMBER_OF_LEGS).append(',')
				.append(COL_NUMBER_OF_TEETH).append(',').append(COL_WEIGHT);

		values.append(',').append(getAnimalType().getValue()).append(',').append(getNumberOfLegs()).append(',')
				.append(getNumberOfTeeth()).append(',').append(getWeight());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((this.animalType == null) ? 0 : this.animalType.hashCode());
		result = prime * result + this.numberOfLegs;
		result = prime * result + this.numberOfTeeth;
		result = prime * result + this.weight;
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
		AbstractAnimal other = (AbstractAnimal) obj;
		if (this.animalType != other.animalType)
			return false;
		if (this.numberOfLegs != other.numberOfLegs)
			return false;
		if (this.numberOfTeeth != other.numberOfTeeth)
			return false;
		if (this.weight != other.weight)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return super.toString() + ", numberOfLegs=" + this.numberOfLegs 
				+ ", numberOfTeeth=" + this.numberOfTeeth //$NON-NLS-1$ //$NON-NLS-2$
				+ ", weight=" + this.weight  //$NON-NLS-1$
				+ ", animalType=" + this.animalType; //$NON-NLS-1$ //$NON-NLS-2$
	}

}
