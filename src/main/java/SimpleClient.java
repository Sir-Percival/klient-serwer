import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class SimpleClient
{
    static void main()
    {
        try(Socket client = new Socket("localhost", 5678))
        {
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            String request, response;

            Scanner scanner = new Scanner(System.in);

            while(true)
            {
                System.out.println("Enter message: ");
                request = scanner.nextLine();

                if(request.equalsIgnoreCase("end"))
                {
                    break;
                }

                out.println(request);

                response = in.readLine();
                System.out.println("Server response: " + response);
            }

        }
        catch (IOException e)
        {
            System.out.println("Client exception: " + e.getMessage());
        }
    }
}
