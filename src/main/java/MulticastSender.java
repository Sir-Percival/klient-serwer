import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class MulticastSender
{
    static void main()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Press enter to start...");
        scanner.nextLine();

        try(DatagramSocket socket = new DatagramSocket())
        {
            String message = "Hello Multicast!";
            InetAddress group = InetAddress.getByName("232.2.2.2");

            DatagramPacket packet = new DatagramPacket(
                    message.getBytes(),
                    message.length(),
                    group,
                    5678
            );

            for(int i=0; i<5; i++)
            {
                System.out.println("Sending message #" + (i+1));
                socket.send(packet);
                Thread.sleep(500);
            }

        } catch (IOException | InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
