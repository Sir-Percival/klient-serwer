import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadedServer
{
    static void main()
    {
        try(ServerSocket server = new ServerSocket(5678))
        {
            while (true)
            {
                try
                {
                    Socket client = server.accept();
                    new ClientHandler(client).start();
                } catch (Exception e) {
                    System.out.println("Client error: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }

    private static class ClientHandler extends Thread
    {
        private Socket client;

        public ClientHandler(Socket client)
        {
            this.client = client;
        }

        @Override
        public void run()
        {
            try(PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream())))
            {
                String request;
                while (true)
                {
                    request = in.readLine();
                    if(request==null || request.equalsIgnoreCase("end"))
                    {
                        System.out.println("Client #" + client.getPort() + " closed connection");
                        break;
                    }
                    System.out.println("Client #" + client.getPort() + ": " + request);
                    out.println("Server-" + request);
                }
            } catch (Exception e) {
                System.out.println("Client error: " + e.getMessage());
            } finally {
                try {
                    client.close();
                } catch (IOException e) {
                    System.out.println("Client error: " + e.getMessage());
                }
            }
        }
    }
}
