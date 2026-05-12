import java.nio.ByteBuffer;

public class Nio
{
    static void main()
    {
        System.out.println("Create buffer");
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        System.out.println(buffer + " " + buffer.remaining());

        System.out.println("Write to buffer");
        buffer.put("Hello world".getBytes());
        System.out.println(buffer + " " + buffer.remaining());

        System.out.println("Flip buffer");
        buffer.flip();
        System.out.println(buffer + " " + buffer.remaining());

        System.out.println("Read from buffer");
        readBuffer(buffer);
        System.out.println(buffer + " " + buffer.remaining());

        System.out.println("Flip buffer");
        buffer.flip();
        System.out.println(buffer + " " + buffer.remaining());

        System.out.println("Adjust buffer");
        buffer.position(buffer.limit());
        buffer.limit(buffer.capacity());
        System.out.println(buffer + " " + buffer.remaining());

//        buffer.compact();

        System.out.println("Write again");
        buffer.put("Hello world again".getBytes());
        System.out.println(buffer + " " + buffer.remaining());

        System.out.println("Flip and read again");
        buffer.flip();
        readBuffer(buffer);
        System.out.println(buffer + " " + buffer.remaining());

        System.out.println("Flip and read again");
        buffer.clear();
        System.out.println(buffer + " " + buffer.remaining());
    }

    private static void readBuffer(ByteBuffer buffer)
    {
        byte[] data = new byte[buffer.limit()];
        buffer.get(data);
        System.out.println(new String(data));
    }
}
