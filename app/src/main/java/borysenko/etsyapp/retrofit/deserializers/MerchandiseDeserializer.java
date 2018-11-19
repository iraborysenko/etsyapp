package borysenko.etsyapp.retrofit.deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import borysenko.etsyapp.model.Merchandise;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 14/11/18
 * Time: 21:17
 */
public class MerchandiseDeserializer implements JsonDeserializer {
    @Override
    public Merchandise deserialize(JsonElement json, Type typeOfT,
                                   JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        String id = jsonObject.get("listing_id").getAsString();
        String categoryId = jsonObject.get("category_id").getAsString();
        String title = jsonObject.get("title").getAsString();

        String description;
        if (jsonObject.get("description").isJsonNull())
            description = "none";
        else description = jsonObject.get("description").getAsString();

        String price = jsonObject.get("price").getAsString();
        String currencyCode = jsonObject.get("currency_code").getAsString();

        return new Merchandise(
                id,
                categoryId,
                title,
                description,
                price,
                currencyCode
        );
    }
}
