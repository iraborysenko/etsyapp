package borysenko.etsyapp.retrofit.deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import borysenko.etsyapp.model.Merchandise;
import borysenko.etsyapp.model.SearchMerchandise;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 16/11/18
 * Time: 13:46
 */
public class SearchMerchandiseDeserializer implements JsonDeserializer {
    @Override
    public SearchMerchandise deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        String count = jsonObject.get("count").getAsString();

        Merchandise[] results = context.deserialize(jsonObject.get("results"),
                Merchandise[].class);

        return new SearchMerchandise(
                count,
                results
        );
    }
}
