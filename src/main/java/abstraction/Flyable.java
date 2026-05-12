package abstraction;

public interface Flyable
{
    public final double MAX_ALTITUDE = 10000;

    public abstract void startFly();

    void fly(int flyHeight);

    public default void land()
    {
        System.out.println("I'm landing...");
    }
}
