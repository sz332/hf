package org.mik.zoo.livingbeing.animal;

public enum AnimalType {
	
    PREDATOR(0),

    HERBIVOROUS(1),

    UNKNOWN(2);

    private int value;

    private AnimalType(int value) {
            this.value = value;
    }

    public int getValue() {
            return this.value;
    }

    public static AnimalType fromInt(int val) {
            switch (val) {
            case 0: return PREDATOR;
            case 1: return HERBIVOROUS;
            default:
                    return UNKNOWN;
            }
    }	
	
}
