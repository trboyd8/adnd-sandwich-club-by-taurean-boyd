package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        try {
            JSONObject sandwichJSON = new JSONObject(json);
            Sandwich sandwich = new Sandwich();

            JSONObject nameJSON = sandwichJSON.getJSONObject("name");
            sandwich.setMainName(nameJSON.getString("mainName"));
            sandwich.setAlsoKnownAs(convertJSONArrayToList(nameJSON.getJSONArray("alsoKnownAs")));

            sandwich.setPlaceOfOrigin(sandwichJSON.getString("placeOfOrigin"));
            sandwich.setDescription(sandwichJSON.getString("description"));
            sandwich.setImage(sandwichJSON.getString("image"));
            sandwich.setIngredients(convertJSONArrayToList(sandwichJSON.getJSONArray("ingredients")));

            return sandwich;
        }
        catch (JSONException ex) {
            Log.w("JSONUtils", "A JSON string we were trying to parse is invalid.");
            ex.printStackTrace();
            return null;
        }
    }

    private static List<String> convertJSONArrayToList(JSONArray jsonArray) throws JSONException {
        if (jsonArray == null || jsonArray.length() == 0) {
            return new ArrayList<String>();
        }

        int numItemsInArray = jsonArray.length();
        String[] array = new String[numItemsInArray];
        for (int i = 0; i < numItemsInArray; i++) {
            array[i] = (String) jsonArray.get(i);
        }

        return Arrays.asList(array);
    }
}
