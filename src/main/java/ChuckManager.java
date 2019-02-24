import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

//import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ChuckManager {

    public static void joke(){
        try{
            System.out.println(jokeOnly(getResponse("https://api.chucknorris.io/jokes/random")));
            System.setProperty("http.agent", "Opera");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    private static String getResponse(String urlQueryString) throws IOException{
        URL url = new URL(urlQueryString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.addRequestProperty("User-Agent", "Mozilla/4.76");
        connection.connect();
        InputStream inStream = connection.getInputStream();
       // return new Scanner(inStream, StandardCharsets.UTF_8).useDelimiter("\\Z").next();
        return new Scanner(inStream, "UTF-8").useDelimiter("\\Z").next();
    }

    private static String jokeOnly(String rawJSON) {

        Gson gson = new Gson();
        NorrisJoke norrisJoke = gson.fromJson(rawJSON, NorrisJoke.class);

        return norrisJoke.getValue();

    }
}
