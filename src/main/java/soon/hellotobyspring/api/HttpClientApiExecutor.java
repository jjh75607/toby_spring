package soon.hellotobyspring.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;

public class HttpClientApiExecutor implements ApiExecutor{

    @Override
    public String execute(URI uri) throws IOException {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(uri)
            .GET()
            .build();

        try (HttpClient client = HttpClient.newBuilder().build()) {
            return client.send(request, BodyHandlers.ofString()).body();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
