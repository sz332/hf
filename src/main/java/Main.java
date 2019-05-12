import java.util.List;

import org.mik.zoo.db.Dao;
import org.mik.zoo.db.ZooDao;
import org.mik.zoo.livingbeing.AbstractLivingbeing;
import org.mik.zoo.livingbeing.Livingbeing;
import org.mik.zoo.livingbeing.animal.AbstractAnimal;
import org.mik.zoo.livingbeing.animal.Animal;
import org.mik.zoo.livingbeing.animal.bird.AbstractBird;
import org.mik.zoo.livingbeing.animal.bird.Bird;
import org.mik.zoo.livingbeing.animal.fish.AbstractFish;
import org.mik.zoo.livingbeing.animal.fish.Fish;
import org.mik.zoo.livingbeing.animal.mammal.AbstractMammal;
import org.mik.zoo.livingbeing.animal.mammal.Mammal;

public class Main {

	private Dao dao;
	
	public Main() {
		
	}
	
	public void process() {
		try {
			this.dao=ZooDao.getInstance();
			this.dao.getAll();
			List<Livingbeing> livingbeings = AbstractLivingbeing.getAllLivingbeings();
			List<Animal> animals = AbstractAnimal.getAllAnimals();
			List<Mammal> mammals = AbstractMammal.getAllMammals();
			List<Fish> fishes = AbstractFish.getAllFishes();
			List<Bird> birds = AbstractBird.getAllBirds();
			
			System.out.println(String.format("There are %d livingbeigns, %d animals, %d mammals, %d fishes, %d birds in our zoo", 
					livingbeings.size(), animals.size(), mammals.size(), fishes.size(), birds.size()));
			System.out.println("Birds:");
			for(Bird b:birds)
				System.out.println(b);

			System.out.println("Fishes:");
			for(Fish f:fishes)
				System.out.println(f);
			
			System.out.println("Mammals:");
			for(Mammal m:mammals)
				System.out.println(m);

			System.out.println("Livingbeings:");
			for(Livingbeing l : livingbeings)
				System.out.println(l);
		}
		finally {
			this.dao.close();	
		}
	}
	
	public static void main(String[] args) {
		new Main().process();
		
	}

}
