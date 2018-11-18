package borysenko.etsyapp.retrofit.deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import borysenko.etsyapp.model.Category;
import borysenko.etsyapp.model.SearchCategories;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 14/11/18
 * Time: 21:16
 */
public class SearchCategoriesDeserializer implements JsonDeserializer {
    @Override
    public SearchCategories deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        String count = jsonObject.get("count").getAsString();

        Category[] results = context.deserialize(jsonObject.get("results"),
                Category[].class);

        return new SearchCategories(
                count,
                results
        );
    }
}
