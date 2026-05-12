package abstraction;

public class Cat extends Animal
{
    private String breed;
    private int lives;

    public Cat(String name, int age, double weight, String breed)
    {
        super(name, age, weight);
        this.breed = breed;
        this.lives = 9;
    }

    public Cat(String name, int age, double weight, String breed, int lives)
    {
        super(name, age, weight);
        this.breed = breed;
        this.lives = lives;
    }

    @Override
    public void makeSound()
    {
        System.out.println(name + " says: Meow meow!");
    }

    @Override
    public void move(double speed)
    {
        System.out.println(name + " raises its tail and is running at: " + speed);
    }

    public void scratch()
    {
        System.out.println(name + " scratches furniture...");
    }
}
