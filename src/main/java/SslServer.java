import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.security.KeyStore;

public class SslServer
{
    private static final String KEYSTORE = "serverkeystore.p12";
    private static final String PASSWD = System.getenv("passwd");

    static void main() throws Exception
    {
        // KeyStore
        KeyStore keyStore = KeyStore.getInstance("PKCS12");

        try(FileInputStream fis = new FileInputStream(KEYSTORE))
        {
            keyStore.load(fis, PASSWD.toCharArray());
        }

        // KeyManagerFactory
        KeyManagerFactory kmf = KeyManagerFactory.getInstance(
                KeyManagerFactory.getDefaultAlgorithm());

        kmf.init(keyStore, PASSWD.toCharArray());

        // SSLContext
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(
                kmf.getKeyManagers(),
                null,
                null
        );

        // SSLServerSocket
        SSLServerSocketFactory factory = sslContext.getServerSocketFactory();
        SSLServerSocket serverSocket = (SSLServerSocket) factory.createServerSocket(5678);

        // Wersje protokołów
        serverSocket.setEnabledProtocols(new String[] {"TLSv1.3"});

        while (true)
        {
            SSLSocket client = (SSLSocket) serverSocket.accept();
            System.out.println("New client #" + client.getPort());
            handleClient(client);
        }
    }

    private static void handleClient(SSLSocket client)
    {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter out = new PrintWriter(client.getOutputStream(), true)
        ) {
            String request;
            while (true)
            {
                request = in.readLine();
                if(request == null || request.equalsIgnoreCase("end"))
                    break;
                System.out.println("Server to client #" + client.getPort() + ": " + request);
                out.println("Server-" + request);
            }
        } catch (Exception e) {
            System.out.println("Server e: " + e.getMessage());
        }
    }
}
