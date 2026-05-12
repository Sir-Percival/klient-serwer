import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UdpEchoClient
{
    static void main()
    {
        try(DatagramSocket socket = new DatagramSocket();
            Scanner scanner = new Scanner(System.in))
        {
            InetAddress address = InetAddress.getByName("localhost");

            while (true)
            {
                System.out.print("Enter message (end to quit): ");
                String message = scanner.nextLine();

                if(message.equalsIgnoreCase("end"))
                    break;

                byte[] data = message.getBytes();

                // Wysylanie wiadomosci
                DatagramPacket request = new DatagramPacket(
                        data,
                        data.length,
                        address,
                        5678
                );
                socket.send(request);

                // Odbieranie odpowiedzi
                byte[] buffer = new byte[1024];
                DatagramPacket response = new DatagramPacket(buffer, buffer.length);
                socket.receive(response);

                String responseMessage = new String(response.getData(), 0, response.getLength());
                System.out.println("Server responded with: " + responseMessage);
            }
        } catch (IOException e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}
