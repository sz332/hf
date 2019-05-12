package org.mik.zoo.db;

import java.util.List;
import java.util.Optional;

import org.mik.zoo.livingbeing.Livingbeing;


public interface Dao {
	
	void close();

    Livingbeing getById(Integer id);

    List<Livingbeing> getAll();

    List<Livingbeing> findByScientificName(String name);

    List<Livingbeing> findByInstanceName(String name);

    boolean delete(Livingbeing lb);

    Livingbeing persist(Livingbeing lb);
}
