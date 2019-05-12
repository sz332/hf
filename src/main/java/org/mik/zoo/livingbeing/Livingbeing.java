package org.mik.zoo.livingbeing;

public interface Livingbeing {

    public static final String UNKNOWN_INSTANCE_NAME = " "; //$NON-NLS-1$

    public static final String TABLE_NAME = "living_being"; //$NON-NLS-1$

    public static final String COL_ID = "id"; //$NON-NLS-1$

    public static final String COL_INSTANCE_NAME = "instance_name"; //$NON-NLS-1$

    public static final String COL_IMAGE_URI = "image_uri"; //$NON-NLS-1$

    public static final String COL_SCIENTIFIC_NAME = "scientific_name"; //$NON-NLS-1$

	Integer getId();
	
	void setId(Integer id);
	
	String getScientificName();
	
	String getInstanceName();
	
	String getImageURI();
	
	boolean isAnimal();
	
	boolean isPlant();
	
	boolean isTree();
	
	boolean isFlower();
	
	boolean isMammal();
	
	boolean isFish();
	
	boolean isBird();
	
    String getUpdateSql();

    void getInsertSql(StringBuilder fields, StringBuilder values);
	
}

