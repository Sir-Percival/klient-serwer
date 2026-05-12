package abstraction;

public class Fish extends Animal implements Swimmable
{
    private String waterType; // "fresh" or "salt"

    public Fish(String name, int age, double weight, String waterType)
    {
        super(name, age, weight);
        this.waterType = waterType;
    }

    @Override
    public void makeSound()
    {
        System.out.println(name + " says: blub blub");
    }

    @Override
    public void swim(double speed)
    {
        System.out.println(name + " is swimming in " + waterType + " water at speed: " + speed);
    }

    @Override
    public void move(double speed)
    {
        swim(speed);
    }
}
