import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer
{
    static void main(String[] args)
    {
        int port = args.length > 0 ? Integer.parseInt(args[0]) : 5678;

        try(ServerSocket server = new ServerSocket(port))
        {
            boolean isServerOn = true;
            while (isServerOn)
            {
                try (Socket socket = server.accept())
                {
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    String request;

                    System.out.println("Client #" + socket.getPort() + " connected...");

                    while(true)
                    {
                        request = in.readLine();

                        if(request == null || request.equalsIgnoreCase("end")) {
                            break;
                        } else if(request.equalsIgnoreCase("end-server")) {
                            System.out.println("Shutting down the server...");
                            isServerOn = false;
                            break;
                        }

                        System.out.println("Server to client #" + socket.getPort() + ": " + request);
                        out.println("Server-" + request);
                    }
                } catch (Exception e) {
                    System.out.println("Client ends connection...");
                }
            }
        } catch (IOException e) {
            System.out.println("Server exception: " + e.getMessage());
        }
    }
}
