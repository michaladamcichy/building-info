package gryffindor.buildinginfo.models;

import java.lang.reflect.Array;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Class that parses json into building class
 * @author Griffindor
 */
public class JSONToBuildingParser {
    public static ArrayList<Building> getBuildings(String json) throws JSONException {
        ArrayList<Building> parsedBuildings = new ArrayList<>();
//        ArrayList<Location> parsedFloors = new ArrayList<>();

        JSONArray buildings = (new JSONObject(new JSONTokener(json))).getJSONArray("buildings");
//        JSONArray floors = (new JSONObject(new JSONTokener(json))).getJSONArray("floors");

        for (int i = 0; i < buildings.length(); i++) {
            JSONObject building = buildings.getJSONObject(i);
            parsedBuildings.add(Building.readJSON(building));
        }

//        for (int i = 0; i < floors.length(); i++) {
//            JSONObject floor = floors.getJSONObject(i);
//            parsedFloors.add(Building.readJSON(floor)); //floor?
//        }

        return parsedBuildings;
    }

    public static ArrayList<Location> getLocations(String json) throws JSONException {
        ArrayList<Location> parsedFloors = new ArrayList<>();

        JSONArray floors = (new JSONObject(new JSONTokener(json))).getJSONArray("floors");

        for (int i = 0; i < floors.length(); i++) {
            JSONObject floor = floors.getJSONObject(i);
            parsedFloors.add(Building.readJSON(floor)); //floor?
        }

        return parsedFloors;
    }
}
