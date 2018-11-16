package borysenko.etsyapp.deserializers;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import borysenko.etsyapp.model.Image;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 16/11/18
 * Time: 14:37
 */
public class ImageDeserializer implements JsonDeserializer {

    @Override
    public Image deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        JsonArray array = (JsonArray) jsonObject.get("results");
        JsonObject obj =  array.get(0).getAsJsonObject();
        String listingId = obj.get("listing_id").getAsString();
        String imageUrl = obj.get("url_75x75").getAsString();

        return new Image (
                listingId,
                imageUrl
        );
    }
}
