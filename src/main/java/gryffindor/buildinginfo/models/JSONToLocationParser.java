package gryffindor.buildinginfo.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;

public class JSONToLocationParser {
    public static ArrayList<Building> getLocations(String json) throws JSONException {
        ArrayList<Building> parsedLocations = new ArrayList<>();

        JSONArray locations1 = (new JSONObject(new JSONTokener(json))).getJSONArray("buildings");
        JSONArray locations2 = (new JSONObject(new JSONTokener(json))).getJSONArray("floors");
        JSONArray locations3 = (new JSONObject(new JSONTokener(json))).getJSONArray("rooms");

        JSONArray locations = null;
        locations.put(locations1);
        locations.put(locations2);
        locations.put(locations3);
        for (int i = 0; i < locations.length(); i++) {
//            JSONObject location = locations.getJSONObject(i);
//            parsedLocations.add(Location.readJSON(location));
        }

        return parsedLocations;
    }
}
