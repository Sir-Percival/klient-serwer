package abstraction;

import java.util.List;

public class Main
{
    static void main()
    {
        Cat cat = new Cat("Dexter", 2, 4.5, "Bengal");
        cat.scratch();

        Animal cat2 = new Cat("Gusia", 1, 7.2, "Maine-coon");
        //cat2.scratch();

        Fish fish = new Fish("Goldy", 1, 0.342, "fresh");
        Duck duck = new Duck("Biegusek", 3, 3.4, 58, false);

        List<Animal> animals = List.of(cat2, fish, duck);

        System.out.println("===== ANIMALS =====");
        for(Animal animal : animals)
        {
            animal.makeSound();
            animal.move(5);
            System.out.println("=".repeat(16));
        }

        System.out.println("===== SWIMMABLE =====");
        List<Swimmable> swimmables = List.of(
                new Fish("Josy", 1, 0.54, "fresh"),
                new Duck("Rocky", 2, 4.5, 32, false)
        );

        for(Swimmable swimmable : swimmables)
        {
            swimmable.swim(4.2);
        }

        System.out.println("===== DUCK =====");
        System.out.println("Duck as Animal");
        duck.makeSound();
        duck.move(5);
        System.out.println("Duck as Swimmable");
        duck.swim(6);
        System.out.println("Duck as Flyable");
        duck.startFly();
        duck.fly(300);
        duck.land();
    }
}
