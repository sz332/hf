package org.mik.zoo.livingbeing;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mik.zoo.livingbeing.animal.AbstractAnimal;
import org.mik.zoo.livingbeing.animal.bird.AbstractBird;
import org.mik.zoo.livingbeing.animal.fish.AbstractFish;
import org.mik.zoo.livingbeing.animal.mammal.AbstractMammal;

public abstract class AbstractLivingbeing implements Livingbeing {

    protected final static String COL_TYPE_VARCHAR_255 = "varchar(255)"; //$NON-NLS-1$
    protected final static String COL_TYPE_INTEGER = "integer"; //$NON-NLS-1$

    private static List<Livingbeing> allLivingbeing = new ArrayList<>();
    protected static Map<String, String> columnDefinitions = new HashMap<>();
    
    static {
        columnDefinitions.put(COL_ID, " integer not null PRIMARY KEY GENERATED ALWAYS AS IDENTITY"); //$NON-NLS-1$
        columnDefinitions.put(COL_INSTANCE_NAME, COL_TYPE_VARCHAR_255);
        columnDefinitions.put(COL_SCIENTIFIC_NAME, COL_TYPE_VARCHAR_255);
        columnDefinitions.put(COL_IMAGE_URI, COL_TYPE_VARCHAR_255);
        AbstractAnimal.setColumnDefinitions();
        AbstractMammal.setColumnDefinitions();
        AbstractFish.setColumnDefinitions();
        AbstractBird.setColumnDefinitions();
    }
    
	private Integer id;
	private String scientificName;
	private String instanceName;
	private String imageURI;
    
	public AbstractLivingbeing() {
	}

	public AbstractLivingbeing(Integer id, String scientificName, String instanceName, String imageURI) {
		this.id = id;
		this.scientificName = scientificName;
		this.instanceName = instanceName;
		this.imageURI = imageURI;
		if (!allLivingbeing.contains(this))
			allLivingbeing.add(this);
	}

	public AbstractLivingbeing(ResultSet rs) throws SQLException {
		this(Integer.valueOf(rs.getInt(COL_ID)), 
			 rs.getString(COL_SCIENTIFIC_NAME),
			 rs.getString(COL_INSTANCE_NAME),
			 rs.getString(COL_IMAGE_URI));
	}

	public AbstractLivingbeing(String scientificName) {
		this(null, scientificName, null, null);
	}

	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
	public String getScientificName() {
		return this.scientificName;
	}

	@Override
	public String getInstanceName() {
		return this.instanceName;
	}

	@Override
	public String getImageURI() {
		return this.imageURI;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public void setScientificName(String scientificName) {
		this.scientificName = scientificName;
	}

	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
	}

	public void setImageURI(String imageURI) {
		this.imageURI = imageURI;
	}

	@Override
	public boolean isAnimal() {
		return false;
	}

	@Override
	public boolean isPlant() {
		return false;
	}

	@Override
	public boolean isTree() {
		return false;
	}

	@Override
	public boolean isFlower() {
		return false;
	}

	@Override
	public boolean isMammal() {
		return false;
	}

	@Override
	public boolean isFish() {
		return false;
	}

	@Override
	public boolean isBird() {
		return false;
	}

	@Override
	public String getUpdateSql() {
		return String.format("%s=%d, %s='%s', %s='%s', %s='%s'", COL_ID, getId(), //$NON-NLS-1$
				COL_INSTANCE_NAME, getInstanceName(), COL_SCIENTIFIC_NAME, getScientificName(), COL_IMAGE_URI,
				getImageURI());
	}

	@Override
	public void getInsertSql(StringBuilder fields, StringBuilder values) { // insert into table ( field1, field2,
																			// field3..) values ()
		fields.append(COL_SCIENTIFIC_NAME).append(',').append(COL_INSTANCE_NAME).append(',').append(COL_IMAGE_URI);
		values.append('\'').append(getScientificName()).append('\'').append(',').append('\'').append(getInstanceName())
				.append('\'').append(',').append('\'').append(getImageURI()).append('\'');
	}

	public static Map<String, String> getColumnDefinitions() {
		return columnDefinitions;
	}
	
	public static List<Livingbeing> getAllLivingbeings() {
		return allLivingbeing;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
		result = prime * result + ((this.imageURI == null) ? 0 : this.imageURI.hashCode());
		result = prime * result + ((this.instanceName == null) ? 0 : this.instanceName.hashCode());
		result = prime * result + ((this.scientificName == null) ? 0 : this.scientificName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractLivingbeing other = (AbstractLivingbeing) obj;
		if (this.id == null) {
			if (other.id != null)
				return false;
		} else if (!this.id.equals(other.id))
			return false;
		if (this.imageURI == null) {
			if (other.imageURI != null)
				return false;
		} else if (!this.imageURI.equals(other.imageURI))
			return false;
		if (this.instanceName == null) {
			if (other.instanceName != null)
				return false;
		} else if (!this.instanceName.equals(other.instanceName))
			return false;
		if (this.scientificName == null) {
			if (other.scientificName != null)
				return false;
		} else if (!this.scientificName.equals(other.scientificName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("scientificName=")
		  .append(this.scientificName);
		if (this.instanceName != null && !this.instanceName.isEmpty()) {
			sb.append(", instanceName=")
			.append(this.instanceName);
		}
		
		return sb.toString(); //$NON-NLS-1$
	}

}
