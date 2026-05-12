import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;

import static java.net.HttpURLConnection.HTTP_NOT_FOUND;
import static java.net.HttpURLConnection.HTTP_OK;

public class HomeService implements HttpHandler
{
    @Override
    public void handle(HttpExchange exchange) throws IOException
    {
        InputStream in = getClass().getClassLoader()
                .getResourceAsStream("index.html");

        if(in == null)
        {
            String msg = "index.html Not Found";
            exchange.sendResponseHeaders(HTTP_NOT_FOUND, msg.length());
            exchange.getResponseBody().write(msg.getBytes());
            exchange.close();
            return;
        }

        byte[] html = in.readAllBytes();

        exchange.getResponseHeaders()
                .add("Content-Type", "text/html; charset=UTF-8");
        exchange.sendResponseHeaders(HTTP_OK, html.length);
        exchange.getResponseBody().write(html);
        exchange.close();
    }
}
