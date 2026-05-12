import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SimpleClient
{
    static void main()
    {
        try (Socket socket = new Socket("localhost", 5678))
        {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String request, response;
            Scanner scanner = new Scanner(System.in);

            while (true)
            {
                System.out.print("Enter message: ");
                request = scanner.nextLine();

                if (request.equalsIgnoreCase("end"))
                {
                    System.out.println("Client disconnected");
                    break;
                }

                out.println(request);
                response = in.readLine();

                System.out.println("Server responded: " + response);
            }

        } catch (Exception e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}
