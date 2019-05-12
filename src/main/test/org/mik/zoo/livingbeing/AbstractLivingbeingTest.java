package org.mik.zoo.livingbeing;

import static org.junit.Assert.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.mik.zoo.livingbeing.animal.AbstractAnimal;
import org.mik.zoo.livingbeing.animal.Animal;
import org.mik.zoo.livingbeing.animal.AnimalType;
import org.mik.zoo.livingbeing.animal.mammal.Elephant;

public class AbstractLivingbeingTest {

	@SuppressWarnings("static-method")
	@Test
	public void testClasses() throws NoSuchMethodException {
		assertTrue(Livingbeing.class.isInterface());
		Method m = Livingbeing.class.getMethod("getId");
		assertNotNull(m);
		assertEquals(m.getReturnType(), Integer.class);
		
		m = Livingbeing.class.getMethod("getScientificName");
		assertNotNull(m);
		assertEquals(m.getReturnType(), String.class);
	
		m = Livingbeing.class.getMethod("isAnimal");
		assertNotNull(m);
		assertEquals(m.getReturnType(), boolean.class);
	
		assertTrue(Animal.class.isInterface());
		List<Class<?>> interfaces = Arrays.asList(Animal.class.getInterfaces());
		assertTrue(interfaces.contains(Livingbeing.class));
		
		m = Animal.class.getMethod("getAnimalType");
		assertNotNull(m);
		assertEquals(m.getReturnType(), AnimalType.class);

		/*  AbstractLivingbeing */
		assertFalse(AbstractLivingbeing.class.isInterface() 
				    && AbstractLivingbeing.class.isEnum());
		
		assertTrue(Modifier.isAbstract(AbstractLivingbeing.class.getModifiers()));
		
		interfaces = Arrays.asList(AbstractLivingbeing.class.getInterfaces());
		assertTrue(interfaces.contains(Livingbeing.class));
		
		assertNotNull(AbstractLivingbeing.class.getConstructor());
		
		Constructor<?> c = AbstractLivingbeing.class.getConstructor(
				Integer.class, String.class, String.class,String.class);
		assertNotNull(c);
		
		m = AbstractLivingbeing.class.getMethod("setScientificName", 
												String.class);
		assertNotNull(m);
		assertEquals(m.getReturnType(), void.class);
		

		/*  AbstractAnimal */
		assertFalse(AbstractAnimal.class.isInterface() 
				    && AbstractAnimal.class.isEnum());
		
		assertTrue(Modifier.isAbstract(AbstractAnimal.class.getModifiers()));
		
		interfaces = Arrays.asList(AbstractAnimal.class.getInterfaces());
		assertTrue(interfaces.contains(Animal.class));
		
		assertNotNull(AbstractAnimal.class.getConstructor());
		
		c = AbstractAnimal.class.getConstructor(
				Integer.class, String.class, String.class,String.class,
				int.class, int.class,int.class,AnimalType.class);
		assertNotNull(c);
		
		assertEquals(AbstractAnimal.class.getSuperclass(), 
				AbstractLivingbeing.class);
		
	}
	
	@Test
	public void objectTest() {
		Elephant e = new Elephant("Jumbo");
		assertNotNull(e);
		assertFalse(e.isBird());
		assertFalse(e.isFish());
		assertFalse(e.isFlower());
		assertFalse(e.isPlant());
		assertFalse(e.isTree());
		assertTrue(e.isAnimal());
		assertTrue(e.isMammal());
		assertEquals(e.getScientificName(), Elephant.SCIENTIFIC_NAME);
		
		
		
	}
	
	
	
	

}

















