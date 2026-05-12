import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpEchoServer
{
    static void main()
    {
        try(DatagramSocket socket = new DatagramSocket(5678))
        {
            System.out.println("Server is listening...");

            byte[] buffer = new byte[1024];

            while (true)
            {
                // Odbieranie pakietu
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                socket.receive(request);

                String message = new String(request.getData(), 0, request.getLength());
                System.out.println("Server got: " + message);

                // Przygotowanie odpowiedzi
                byte[] responseData = message.getBytes();

                DatagramPacket response = new DatagramPacket(
                        responseData,
                        request.getLength(),
                        request.getAddress(),
                        request.getPort()
                );

                socket.send(response);
            }
        } catch (IOException e)
        {
            System.out.println("Server error: " + e.getMessage());
        }
    }
}
