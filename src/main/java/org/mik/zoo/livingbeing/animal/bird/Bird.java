package org.mik.zoo.livingbeing.animal.bird;

import org.mik.zoo.livingbeing.animal.Animal;

public interface Bird extends Animal {

	public final static String COL_WING_LENGTH = "wing_length"; //$NON-NLS-1$
	 
	int getWingLength();

}
