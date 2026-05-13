import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class SimpleClient
{
    static void main(String[] args)
    {
        int port = args.length > 0 ? Integer.parseInt(args[0]) : 5679;

        try(Socket client = new Socket("localhost", port))
        {
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            String request, response;

            Scanner scanner = new Scanner(System.in);

            System.out.println(client);

            while(true)
            {
                System.out.print("Enter message: ");
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