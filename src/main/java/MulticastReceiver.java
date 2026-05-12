import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastReceiver
{
    static void main()
    {
        try(MulticastSocket socket = new MulticastSocket(5678))
        {
            socket.setSoTimeout(5000);
            InetAddress groupAddress = InetAddress.getByName("232.2.2.2");
            socket.joinGroup(groupAddress);

            byte[] buffer = new byte[1024];
            System.out.println("Waiting for some data...");


            for(int i=0; i<10; i++)
            {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                String received = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Got #" + (i+1) + ": " + received);

                if(i == 3)
                {
                    socket.leaveGroup(groupAddress);
                    System.out.println("Left the group");
                }
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
