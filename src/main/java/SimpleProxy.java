import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleProxy
{
    public static void main(String[] args)
    {
        int proxyPort = 5679;
        String serverHost = "localhost";
        int serverPort = 5678;

        try(ServerSocket proxyServer = new ServerSocket(proxyPort))
        {
            System.out.println("Proxy started on port " + proxyPort);

            while(true)
            {
                Socket clientSocket = proxyServer.accept();

                System.out.println("Client connected to proxy...");

                new Thread(() ->
                {
                    try (Socket serverSocket = new Socket(serverHost, serverPort);
                         BufferedReader clientIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                         PrintWriter clientOut = new PrintWriter(clientSocket.getOutputStream(), true);
                         BufferedReader serverIn = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
                         PrintWriter serverOut = new PrintWriter(serverSocket.getOutputStream(), true);
                    )
                    {
                        String request;

                        while((request = clientIn.readLine()) != null)
                        {
                            System.out.println("[PROXY] Client says: " + request);

                            // Możemy zmodyfikować dane
                            String modifiedRequest =
                                    "[PROXY-MODIFIED] " + request;

                            // Wysyłamy do serwera
                            serverOut.println(modifiedRequest);

                            // Odbieramy odpowiedź
                            String response = serverIn.readLine();

                            System.out.println("[PROXY] Server response: " + response);

                            // Odsyłamy klientowi
                            clientOut.println(response);

                            if(request.equalsIgnoreCase("end"))
                            {
                                break;
                            }
                        }

                        clientSocket.close();

                    } catch (IOException e)
                    {
                        System.out.println("Proxy exception: " + e.getMessage());
                    }
                }).start();
            }

        } catch (IOException e)
        {
            System.out.println("Proxy server exception: " + e.getMessage());
        }
    }
}
