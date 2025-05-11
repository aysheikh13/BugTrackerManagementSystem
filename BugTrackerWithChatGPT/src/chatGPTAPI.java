import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


public class chatGPTAPI {
    // These are private static final variables that will never be altered as there is no need as well as to ensure the class methods have access to them always
    private static final String apiKey = "Insert_API_Key";
    private static final String chatgptURL = "https://api.openai.com/v1/chat/completions";
    private static final String chatgptModel = "gpt-3.5-turbo";

    public static String chatGPTCall(String input) throws Exception {
        try {
            // Establish connection to the ChatGPT website that does require a key/authorization and obtain JSON data
            URL obj = new URL(chatgptURL);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + apiKey);
            connection.setRequestProperty("Content-Type", "application/json");

            // Essentially we are creating the body that will grant us the means of getting the JSON data from the api
            String body = "{\"model\": \"" + chatgptModel + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + input + "\"}]}";
            connection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(body);
            writer.flush();
            writer.close();

            // Here we are reading the incoming data from the chatGPT API
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;

            StringBuffer response = new StringBuffer();

            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();

            return parseChatGPTCall(response.toString());
        } catch (IOException e) {
            return "Error: " + e.getMessage();
        }
    }

    public static String parseChatGPTCall(String input) {
        // This is the method/function for parsing the input we obtained from the api into a readable string that is not in JSON form
        int startOfString = input.indexOf("content")+ 11;

        int endOfString = input.indexOf("\"", startOfString);

        return input.substring(startOfString, endOfString);

    }
}
