package abstraction;

public class Duck extends Animal implements Flyable, Swimmable
{
    private double wingSpan;
    private boolean isWild;

    public Duck(String name, int age, double weight, double wingSpan, boolean isWild)
    {
        super(name, age, weight);
        this.wingSpan = wingSpan;
        this.isWild = isWild;
    }

    @Override
    public void makeSound()
    {
        System.out.println(name + " goes quack quack!");
    }

    @Override
    public void startFly()
    {
        System.out.println(name + " spreads its wings, starts running and flapping them...");
    }

    @Override
    public void fly(int flyHeight)
    {
        System.out.println(name + " is flying at " + flyHeight + "m above ground");
    }

    @Override
    public void swim(double speed)
    {
        System.out.println(name + " floats majestically on the water, wiggles its legs and swims at a speed of: " + speed);
    }
}
