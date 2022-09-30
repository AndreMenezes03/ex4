import java.io.IOException;

import com.google.gson.*;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class TranslatorText {
    // ↓ Chave da API e localização do servidor
    private static String key = "<coloca a chave aqui ó>";
    private static String location = "brazilsouth";

    OkHttpClient client = new OkHttpClient();

    public String Post() throws IOException {
        MediaType mediaType = MediaType.parse("application/json");
        // ↓ Texto a ser traduzido
        RequestBody body = RequestBody.create(mediaType,
                "[{\"Text\": \"Eu adoraria ter acesso ao serviço da OpenAI da plataforma Azure, infelizmente não está disponível\"}]");
        // ↓ Chamada da API de tradução pelo código
        Request request = new Request.Builder()
                .url("https://api.cognitive.microsofttranslator.com/translate?api-version=3.0&from=pt-br&to=es&to=en&to=fr&to=it")
                .post(body)
                .addHeader("Ocp-Apim-Subscription-Key", key)
                .addHeader("Ocp-Apim-Subscription-Region", location) 
                .addHeader("Content-type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
    // ↓ Deixa o JSON de resposta bonitinho
    public static String prettify(String json_text) {
        JsonParser parser = new JsonParser();
        JsonElement json = parser.parse(json_text);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
       return gson.toJson(json);
    }

    public static void main(String[] args) {
        try {
            TranslatorText translateRequest = new TranslatorText();
            String response = translateRequest.Post();
            System.out.println(prettify(response));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}