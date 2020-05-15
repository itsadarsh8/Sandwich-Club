package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        ArrayList<String> alsoKnownAsList=new ArrayList<>();
        ArrayList<String> ingredientsList=new ArrayList<>();

        Sandwich sandwich=new Sandwich();

        try {
            JSONObject baseJson = new JSONObject(json);
            JSONObject Name = baseJson.getJSONObject("name");

            String mainName = Name.getString("mainName");
            String placeOfOrigin = baseJson.getString("placeOfOrigin");
            String description =baseJson.getString("description");
            String image = baseJson.getString("image");

            JSONArray alsoKnownAs = Name.getJSONArray("alsoKnownAs");
            for (int i = 0; i < alsoKnownAs.length(); i++){
                alsoKnownAsList.add(alsoKnownAs.getString(i));
            }

            JSONArray ingredients = baseJson.getJSONArray("ingredients");
            for (int j = 0; j < ingredients.length(); j++){
                ingredientsList.add(ingredients.getString(j));
            }


            sandwich.setMainName(mainName);
            sandwich.setAlsoKnownAs(alsoKnownAsList);
            sandwich.setIngredients(ingredientsList);
            sandwich.setImage(image);
            sandwich.setPlaceOfOrigin(placeOfOrigin);
            sandwich.setDescription(description);
            Log.i("success:","True");


        } catch (JSONException e) {
           Log.i("JsonUtil Class","Error fetching JSON Data");
        }
        return sandwich;
    }
}
