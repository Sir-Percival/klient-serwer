import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadedExecServer
{
    private static volatile boolean running = true;
    private static final int THREAD_POOL_SIZE = 10;

    static void main()
    {
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

        try (ServerSocket server = new ServerSocket(5678))
        {
            while (running)
            {
                try
                {
                    Socket client = server.accept();
                    executor.execute(new ClientHandler(client, server));
                } catch (SocketException e) {
                    System.out.println("Server socket closed.");
                } catch (Exception e) {
                    System.out.println("Client error: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("Server error: " + e.getMessage());
        } finally {
            executor.shutdown();
        }

        System.out.println("Server is down.");
    }

    private static class ClientHandler implements Runnable
    {
        private Socket client;
        private ServerSocket server;

        public ClientHandler(Socket client, ServerSocket server)
        {
            this.client = client;
            this.server = server;
        }

        @Override
        public void run()
        {
            try (PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream())))
            {
                String request;

                while (true)
                {
                    request = in.readLine();

                    if (request == null || request.equalsIgnoreCase("end"))
                    {
                        System.out.println("Client #" + client.getPort() + " closed connection");
                        break;
                    }

                    if (request.equalsIgnoreCase("end-server"))
                    {
                        out.println("Server is shutting down...");
                        running = false;
                        server.close();
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