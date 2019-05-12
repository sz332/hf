package org.mik.zoo.livingbeing.animal.mammal;

import org.mik.zoo.livingbeing.animal.Animal;

public interface Mammal extends Animal {
	
    public static final String COL_HAIR_LENGTH = "hair_length"; //$NON-NLS-1$

    public static final int MAX_HAIR_LENGTH = 20;
    
	int getHairLength();
	
}
