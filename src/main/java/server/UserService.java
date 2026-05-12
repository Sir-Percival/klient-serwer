package server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Optional;

import static java.net.HttpURLConnection.HTTP_OK;

public class UserService implements HttpHandler
{
    private final UserRepository repo;
    private final ObjectMapper mapper = new ObjectMapper();

    public UserService(UserRepository repo)
    {
        this.repo = repo;
    }


    @Override
    public void handle(HttpExchange exchange) throws IOException
    {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();

        String[] parts = path.split("/");
        Long id = (parts.length == 3) ? Long.parseLong(parts[2]) : null;

        try
        {

            switch (method)
            {
                case "GET" -> {
                    if(id == null) {
                        getAll(exchange);
                    } else {
                        getOne(exchange, id);
                    }
                }
                case "POST" -> create(exchange);
                case "PUT" -> update(exchange, id);
                case "DELETE" -> delete(exchange, id);
                default -> send(exchange, 405, "Method Not Allowed");
            }

        } catch (Exception e) {
            System.out.println("User e: " + e.getMessage());
        }

    }

    private void send(HttpExchange ex, int status, Object msg) throws IOException
    {
        byte[] data = mapper.writeValueAsBytes(msg);
        ex.getResponseHeaders()
                .add("Content-Type", "text/json");
        ex.sendResponseHeaders(status, data.length);
        ex.getResponseBody().write(data);
        ex.close();
    }

    private void getAll(HttpExchange exchange) throws IOException
    {
        send(exchange, HTTP_OK, repo.findAll());
    }

    private void getOne(HttpExchange exchange, Long id) throws IOException
    {
        Optional<User> user = repo.findById(id);
        if(user.isEmpty())
        {
            send(exchange, 404, "User Not Found");
            return;
        }

        send(exchange, 200, user.get());
    }

    private void create(HttpExchange exchange) throws IOException
    {
        User user = mapper.readValue(exchange.getRequestBody(), User.class);
        send(exchange, 201, repo.save(user));
    }

    private void delete(HttpExchange exchange, Long id) throws IOException
    {
        if(id == null)
        {
            send(exchange, 400, "Missing id");
            return;
        }

        repo.delete(id);
        send(exchange, 200, "User deleted");
    }

    private void update(HttpExchange exchange, Long id) throws IOException
    {
        if(id == null)
        {
            send(exchange, 400, "Missing id");
            return;
        }

        Optional<User> user = repo.findById(id);

        if(user.isEmpty())
        {
            send(exchange, 404, "User Not Found");
            return;
        }

        User updatedUser = mapper.readValue(exchange.getRequestBody(), User.class);
        updatedUser.setId(id);

        send(exchange, 200, repo.save(updatedUser));
    }
}
