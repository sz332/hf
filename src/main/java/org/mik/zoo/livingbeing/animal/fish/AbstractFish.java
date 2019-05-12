package org.mik.zoo.livingbeing.animal.fish;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.mik.zoo.livingbeing.animal.AbstractAnimal;
import org.mik.zoo.livingbeing.animal.AnimalType;

public abstract class AbstractFish extends AbstractAnimal implements Fish {

    private static List<Fish> allFishes = new ArrayList<>();
    
	private int deep;
	
	public AbstractFish() {}

	public AbstractFish(Integer id, String scientificName, String instanceName, String imageURI, int numberOfLegs,
			int numberOfTeeth, int weight, AnimalType animalType, int deep) {
		super(id, scientificName, instanceName, imageURI, numberOfLegs, numberOfTeeth, weight, animalType);
		this.deep=deep;
	}

    public AbstractFish(ResultSet rs) throws SQLException {
        super(rs);
        this.deep = rs.getInt(COL_DEEP);
        if (!allFishes.contains(this))
                allFishes.add(this);
    }
    
	@Override
	public int getDeep() {
		return this.deep;
	}

	public void setDeep(int deep) {
		this.deep = deep;
	}
	
	@Override
	public boolean isFish() {
		return true;
	}

    @Override
    public String getUpdateSql() {
            return String.format("%s, %s=%d", super.getUpdateSql(), //$NON-NLS-1$
                            COL_DEEP, Integer.valueOf(getDeep()));
    }

    @Override
    public void getInsertSql(StringBuilder fields, StringBuilder values) {
            super.getInsertSql(fields, values);
            fields.append(',').append(COL_DEEP);
            values.append(',').append(getDeep());
    }

    public static List<Fish> getAllFishes() {
            return Collections.unmodifiableList(allFishes);
    }

    public static void setColumnDefinitions(){
            columnDefinitions.put(COL_DEEP, COL_TYPE_INTEGER);
    }
    
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + this.deep;
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
		AbstractFish other = (AbstractFish) obj;
		if (this.deep != other.deep)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return super.toString() + ", deep:" + this.deep;
	}
	
	

}
