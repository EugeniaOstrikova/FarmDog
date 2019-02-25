package leverx.course;

import java.util.ArrayList;

public class FarmDog {
    public static void main(String... args) {
        ArrayList<Dog> dogList = new ArrayList<>();
        ArrayList<Aviary> dogsRoom = new ArrayList<>();

        Dog dogSam = new Dog("Sam", 3.0, 5.0);
        Dog dogTom = new Dog("Tom", 8.2, 7.0);
        Dog dogSally = new Dog("Sally", 0.7, 3.4);
        Dog dogLuck = new Dog("Luck", 0.4, 2.8);

        dogList.add(dogSam);
        dogList.add(dogTom);
        dogList.add(dogSally);
        dogList.add(dogLuck);

        Doctor doctor = new Doctor("Sven");
        Trainer trainer = new Trainer("Anna");
        Сleaner cleaner = new Сleaner("Alice");
        ServiceStaff serviceStaff = new ServiceStaff("Alex");

        Aviary dogsRoom1 = new Aviary(1);
        Aviary dogsRoom2 = new Aviary(2);
        dogsRoom.add(dogsRoom1);
        dogsRoom.add(dogsRoom2);
        TrainRoom trainRoom = new TrainRoom(1);

        for (Dog dog : dogList) {
            dog.setHungry(true);
        }
        dogTom.setHealth(false);
        dogLuck.setHealth(false);
        for (Aviary aviary : dogsRoom) {
            aviary.setClean(false);
        }

        //morning feeding
        System.out.println("The morning feeding start: \n");
        for (Dog dog : dogList) {
            serviceStaff.feedDog(dog);
        }
        System.out.println("The morning feeding end\n...\n");

        //medical examination
        System.out.println("The medical examination start: \n");
        for (Dog dog : dogList) {
            if (!doctor.inspectDog(dog))
                doctor.treatDog(dog);
        }
        System.out.println("The medical examination end\n...\n");

        //Aviary cleaning
        System.out.println("The aviary cleaning start: \n");
        for (Aviary aviary : dogsRoom) {
            cleaner.clearAviary(aviary);
        }
        System.out.println("The aviary cleaning end\n...\n");

        //Training
        System.out.println("The training start: \n");
        for (Dog dog : dogList)
            trainer.trainDog(dog, trainRoom);
        System.out.println("The training end\n...\n");

        //Dogs working
        System.out.println("The Dogs working start: \n");
        for (Dog dog : dogList)
            dog.work();
        System.out.println("The Dogs working end\n...\n");

        //Evening feeding
        System.out.println("The evening feeding start: \n");
        for (Dog dog : dogList) {
            serviceStaff.feedDog(dog);
        }
        System.out.println("The evening feeding end\n...\n");
    }

}

class Dog {
    private String name;
    private double age, weight;
    private boolean isHealth, isHungry, isTrained;

    public Dog(String name, double age, double weight) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.isHealth = true;
        this.isHungry = false;
    }

    public String getName() {
        return this.name;
    }

    public boolean getHealth() {
        return this.isHealth;
    }

    public void setHealth(boolean isHealth) {
        this.isHealth = isHealth;
    }

    public boolean getHungry() {
        return this.isHungry;
    }

    public void setHungry(boolean isHungry) {
        this.isHungry = isHungry;
    }

    public boolean getTrained() {
        return this.isTrained;
    }

    public void setTrained(boolean trained) {
        this.isTrained = trained;
    }

    public double getAge () {
        return this.age;
    }

    public double getCofficientOfDiet() {
        if (this.age > 1)
            return this.weight * 3.5;
        else
            return this.weight * 5;
    }

    public void work () {
        if (this.age < 1)
            System.out.println("The dog " + this.name + " is young wor work.");
        else if (this.age > 8)
            System.out.println("The dog " + this.name + " is older wor work.");
        else {
            System.out.println("The dog " + this.name + " work successfully.");
        }
    }

    @Override
    public String toString() {
        return "It is " + this.name + " dog, age: " + this.age;
    }
}

class Human {
    String name;

    public Human(String name) {
        this.name = name;
    }
}

class Doctor extends Human {
    public Doctor(String name) {
        super(name);
    }

    public boolean inspectDog(Dog dog) {
        boolean health = dog.getHealth();
        if (health) {
            System.out.println("The dog " + dog.getName() + " is healthy");
            return true;
        } else {
            System.out.println("The dog " + dog.getName() + " is sick. Treatment is required.");
            return false;
        }
    }

    public void treatDog(Dog dog) {
        dog.setHealth(true);
        System.out.println("Has been treated. The dog " + dog.getName() + " is healthy.");
    }
}

class Trainer extends Human {
    public Trainer(String name) {
        super(name);
    }

    public void trainDog(Dog dog, TrainRoom room) {
        if (dog.getAge() > 1) {
            System.out.println("The dog " + dog.getName() + " is older for training.");
        } else {
            if (dog.getTrained())
                System.out.println("The dog " + dog.getName() + " is already trained.");
            else {
                dog.setTrained(true);
                dog.setHungry(true);
                room.setFullOfDog(true, dog);
                System.out.println("Passed training, the dog " + dog.getName() + " is trained.");
                room.setFullOfDog(false);
            }
        }
    }
}

class Сleaner extends Human {
    public Сleaner(String name) {
        super(name);
    }

    public void clearAviary(Aviary aviary) {
        if (aviary.getСlean())
            System.out.println("Aviary " + aviary.getNumber() + " is already clean.");
        else {
            aviary.setClean(true);
            System.out.println("Aviary " + aviary.getNumber() + " cleaned.");
        }
    }
}

class ServiceStaff extends Human {
    public ServiceStaff(String name) {
        super(name);
    }

    public void feedDog(Dog dog) {
        if (dog.getHungry()) {
            dog.setHungry(false);
            double numberOfFood = dog.getCofficientOfDiet() / 2;
            System.out.println("The dog " + dog.getName() + " was fed. Feed rate: " + numberOfFood);
        } else
            System.out.println("The dog " + dog.getName() + " is already fed.");
    }
}

class Room {
    int number;

    public Room(int number) {
        this.number = number;
    }

    public int getNumber() {
        return this.number;
    }
}

class Aviary extends Room {
    boolean isClean;

    public Aviary(int number) {
        super(number);
        this.isClean = true;
    }

    public boolean getСlean() {
        return this.isClean;
    }

    public void setClean(boolean clean) {
        this.isClean = clean;
    }
}

class TrainRoom extends Room {
    boolean isFull;
    Dog dog;

    public TrainRoom(int number) {
        super(number);
        this.isFull = false;
    }

    public boolean getFull() {
        return this.isFull;
    }

    public void setFullOfDog(boolean full, Dog dog) {
        this.isFull = full;
        this.dog = dog;
    }

    public void setFullOfDog(boolean full) {
        this.isFull = full;
        this.dog = null;
    }
}

