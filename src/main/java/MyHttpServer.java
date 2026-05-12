import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class MyHttpServer
{
    public static void main(String[] args)
    {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
            UserRepository repo = new InMemoryUserRepository();

            server.createContext("/", new HomeService());
            server.createContext("/users", new UserService(repo));

            server.start();
            System.out.println("Server is listening on port 8080....");
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }
}
