import server.User;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class ApiClient {

    private static final String BASE_URL = "http://localhost:8080/users";

    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    static void main() throws Exception
    {
        ApiClient api = new ApiClient();

        System.out.println("=== CREATE USER ===");
        User created = api.createUser("Jan", "Kowalski", 30);
        api.createUser("Adam", "West", 32);
        System.out.println(created);

        System.out.println("\n=== GET ALL USERS ===");
        List<User> users = api.getAllUsers();
        users.forEach(System.out::println);

        System.out.println("\n=== GET ONE USER ===");
        User user = api.getUser(created.getId());
        System.out.println(user);

        System.out.println("\n=== UPDATE USER ===");
        User updated = api.updateUser(created.getId(), "Jan", "Nowak", 35);
        System.out.println(updated);

        System.out.println("\n=== GET UPDATED USER ===");
        System.out.println(api.getUser(created.getId()));

        System.out.println("\n=== DELETE USER ===");
        String response = api.deleteUser(created.getId());
        System.out.println(response);

        System.out.println("\n=== GET ALL USERS (AFTER DELETE) ===");
        api.getAllUsers().forEach(System.out::println);
    }

    // ---------------- CREATE ----------------
    public User createUser(String firstName, String lastName, int age) throws Exception {

        String json = """
                {
                    "firstName": "%s",
                    "lastName": "%s",
                    "age": %d
                }
                """.formatted(firstName, lastName, age);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return mapper.readValue(response.body(), User.class);
    }

    // ---------------- GET ALL ----------------
    public List<User> getAllUsers() throws Exception {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return mapper.readValue(response.body(), new TypeReference<>() {
        });
    }

    // ---------------- GET ONE ----------------
    public User getUser(long id) throws Exception {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + id))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return mapper.readValue(response.body(), User.class);
    }

    // ---------------- UPDATE ----------------
    public User updateUser(long id, String firstName, String lastName, int age) throws Exception {

        String json = """
                {
                    "firstName": "%s",
                    "lastName": "%s",
                    "age": %d
                }
                """.formatted(firstName, lastName, age);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + id))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return mapper.readValue(response.body(), User.class);
    }

    // ---------------- DELETE ----------------
    public String deleteUser(long id) throws Exception {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + id))
                .DELETE()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }
}