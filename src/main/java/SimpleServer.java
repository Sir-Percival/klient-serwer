import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer
{
    static void main()
    {
        try(ServerSocket server = new ServerSocket(5678))
        {
            while (true)
            {
                Socket socket = server.accept();
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                String request;

                while(true)
                {
                    request = in.readLine();
                    System.out.println("Server got: " + request);

                    if(request == null || request.equalsIgnoreCase("end"))
                    {
                        break;
                    }

                    out.println("Server-" + request);
                }
            }
        } catch (IOException e) {
            System.out.println("Server exception: " + e.getMessage());
        }
    }
}
