package abstraction;

public abstract class Animal
{
    protected String name;
    protected int age;
    protected double weight;

    public Animal(String name, int age, double weight)
    {
        this.name = name;
        this.age = age;
        this.weight = weight;
    }

    public abstract void makeSound();

    public void move(double speed)
    {
        System.out.println(name + " moves at speed: " + speed);
    }

    public void eat(double foodWeight)
    {
        weight += foodWeight * 0.25;
        System.out.println(name + " ate some food and now it's weight is: " + weight);
    }
}
