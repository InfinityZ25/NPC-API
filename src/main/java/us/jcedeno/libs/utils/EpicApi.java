package us.jcedeno.libs.utils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class EpicApi {

    private static HttpClient client = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(1)).build();
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private static String getUser(String username) throws IOException, InterruptedException {
        var uri = URI.create("https://api.ashcon.app/mojang/v2/user/" + username);
        var request = HttpRequest.newBuilder(uri).header("accept", "application/json").build();

        var response = client.send(request, BodyHandlers.ofString());
        return response.body();
    }

    /**
     * A function that returns a JsonObject containing the given user's skin
     * texture, as value, and skin signature, as signature.
     * 
     * @param username The user's username.
     * @return A JsonObject containing the user's skin texture and skin signature
     * @throws IOException          If an I/O error occurs.
     * @throws InterruptedException If the connection is interrupted.
     */
    public static JsonObject getPlayerSkin(String username) throws IOException, InterruptedException {
        return gson.fromJson(getUser(username), JsonObject.class).getAsJsonObject("textures").getAsJsonObject("raw");
    }

}
