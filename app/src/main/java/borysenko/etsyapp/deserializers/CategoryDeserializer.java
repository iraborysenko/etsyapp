package borysenko.etsyapp.deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import borysenko.etsyapp.model.Category;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 14/11/18
 * Time: 21:31
 */
public class CategoryDeserializer implements JsonDeserializer {

    @Override
    public Category deserialize(JsonElement json, Type typeOfT,
                                JsonDeserializationContext context) throws JsonParseException {

        JsonObject jsonObject = json.getAsJsonObject();

        String categoryId = jsonObject.get("category_id").getAsString();
        String categoryName = jsonObject.get("category_name").getAsString();
        String shortName = jsonObject.get("short_name").getAsString();

        return new Category (
                categoryId,
                categoryName,
                shortName
        );
    }
}
