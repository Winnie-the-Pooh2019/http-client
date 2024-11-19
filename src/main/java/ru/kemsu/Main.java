package ru.kemsu;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {
    private final static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Config config = Config.getInstance();

        String requestUrl = config.getBaseUrl() + "?page=%s".formatted(1);

        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(requestUrl))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            UserListResponse userListResponse = gson.fromJson(response.body(), UserListResponse.class);

            logger.info("Page number {}", userListResponse.getPage());
            logger.info("Total users amount: {}", userListResponse.getTotal());
            logger.info("Users");
            userListResponse.getData().forEach(u -> logger.info(u.toString()));

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}