import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class MyApiClient
{
    private static final String BASE_URL = "http://localhost:8080/users";

    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    static void main() throws Exception
    {
        MyApiClient api = new MyApiClient();

        System.out.println("== CREATE USER ==");
//        User created = api.createUser("Jan", "Kowalski", 32);
//        System.out.println(created);

        System.out.println("== GET ALL ===");
        List<User> users = api.getAllUsers();
        users.forEach(System.out::println);

        System.out.println("=== DELETE ===");
        System.out.println("=== GET ALL ===");

    }

    // POST - tworzenie usera
    public User createUser(String firstName, String lastName, int age) throws Exception
    {
        String json = """
                "firstName":"%s",
                "lastName":"%s",
                "age":%d
                """.formatted(firstName, lastName, age);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());


        return mapper.readValue(response.body(), User.class);
    }

    public List<User> getAllUsers() throws Exception
    {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        return mapper.readValue(response.body(), new TypeReference<>(){});
    }


}
