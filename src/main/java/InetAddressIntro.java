import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressIntro
{
    static void main()
    {
        try
        {
            // Create InetAddress by host name
            InetAddress netflix = InetAddress.getByName("netflix.com");
            System.out.println(netflix.getHostName() + "/" + netflix.getHostAddress());

            // Create InetAddress by IPv4
            byte[] address = {(byte) 212, 77, 98, 9};
            InetAddress wp = InetAddress.getByAddress(address);
            System.out.println(wp.getCanonicalHostName());

            // Create InetAddress by IPv6
            InetAddress google = InetAddress.getByName("2001:4860:4860::8888");
            System.out.println(google.getHostName());

            // Get local host
            InetAddress me = InetAddress.getLocalHost();
            System.out.println("My hostname: " + me.getHostName());
            System.out.println("My address: " + me.getHostAddress());

            InetAddress localhost = InetAddress.getByName("localhost");
            System.out.println("Localhost: " + localhost.getHostAddress());
        }
        catch (UnknownHostException exception)
        {
            System.out.println("Could not find the given host...");
        } catch (IOException exception)
        {
            System.out.println("Could not reach the given host...");
        }
    }
}
