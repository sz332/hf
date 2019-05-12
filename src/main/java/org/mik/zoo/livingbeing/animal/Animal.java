package org.mik.zoo.livingbeing.animal;

import org.mik.zoo.livingbeing.Livingbeing;

public interface Animal extends Livingbeing {
    
	public final static String COL_ANIMAL_TYPE = "animal_type"; //$NON-NLS-1$
    public final static String COL_NUMBER_OF_LEGS = "number_of_legs"; //$NON-NLS-1$
    public final static String COL_NUMBER_OF_TEETH = "number_of_teeth"; //$NON-NLS-1$
    public final static String COL_WEIGHT = "weight"; //$NON-NLS-1$
    
	int getNumberOfLegs();
	
	int getNumberOfTeeth();
	
	int getWeight();
	
	AnimalType getAnimalType();
}
