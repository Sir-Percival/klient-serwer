import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.security.KeyStore;
import java.util.Scanner;

public class SslClient
{
    private static final String TRUSTSTORE =  "clienttruststore.p12";
    private static final String PASSWD = System.getenv("passwd");

    static void main() throws Exception
    {
        // Truststore
        KeyStore trustStore = KeyStore.getInstance("PKCS12");

        try(FileInputStream fis = new FileInputStream(TRUSTSTORE))
        {
            trustStore.load(fis, PASSWD.toCharArray());
        }

        // TrustManagerFactory
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(trustStore);

        // SSLContext
        SSLContext sslContext = SSLContext.getInstance("TLS");

        sslContext.init(
                null,
                tmf.getTrustManagers(),
                null
        );

        // SSLSocket
        SSLSocketFactory factory = sslContext.getSocketFactory();
        SSLSocket socket = (SSLSocket) factory.createSocket("localhost", 5678);

        socket.setEnabledProtocols(new String[]{"TLSv1.3"});

        try(
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            String request, response;
            Scanner scanner = new Scanner(System.in);

            System.out.println(socket);

            while(true)
            {
                System.out.print("Enter message: ");
                request = scanner.nextLine();

                if(request.equalsIgnoreCase("end"))
                {
                    System.out.println(socket);
                    break;
                }

                out.println(request);

                response = in.readLine();
                System.out.println("Server response: " + response);
            }
        } catch (Exception e) {
            System.out.println("Client exception: " + e.getMessage());
        }
    }
}
