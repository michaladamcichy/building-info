package gryffindor.buildinginfo.controllers;

import gryffindor.buildinginfo.models.Building;
import gryffindor.buildinginfo.models.JSONToBuildingParser;
import gryffindor.buildinginfo.models.Room;
import org.json.JSONException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * REST Controller class
 * It enables web service communication
 * Example Request JSON Structure
 * {
    "buildings": [
        {
            "id": 11,
            "floors": [
                {
                    "id": 21,
					"rooms": [
						{
							"id": 31,
							"area": 2.0,
							"volume": 6.0,
							"heating": 4.0,
							"light": 5.0
						}
					]
                },
                {
                    "id": 22,
					"rooms": [
						{
							"id": 32,
							"area": 42.0,
							"volume": 336.0,
							"heating": 9.0,
							"light": 10.0
						}
					]
                }
            ]
        },

        {
            "id": 12,
            "floors": [
                {
                    "id": 23,
					"rooms": [
						{
							"id": 33,
							"area": 2.0,
							"volume": 6.0,
							"heating": 4.0,
							"light": 5.0
						}
					]
                },
                {
                    "id": 24,
					"rooms": [
						{
							"id": 34,
							"area": 42.0,
							"volume": 336.0,
							"heating": 9.0,
							"light": 10.0
						}
					]
                }
            ]
        }	
        
]
}
 */
@RestController
public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);
	private String objectId;

    @RequestMapping("/healthcheck")
    public Map<String, Object> healthcheck(){
        Map<String, Object> response = new HashMap<>();
        response.put("status", "ok");

        return response;
    }

	/**
	* Method : Post
	* Example json response
	{
    	"area": 500
     }
	*
	*  URL: 127.0.0.1:8080/getArea
	*
	**/
    @PostMapping("/getArea")
    public Map<String, Object> getArea(@RequestBody String json ) {
        Map<String, Object> response = new HashMap<>();

        ArrayList<Building> buildings = null;
        try {
            buildings = JSONToBuildingParser.getBuildings(json);
            logger.info("Building parsed from json");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(buildings == null) {
            response.put("error", "error");

        } else {
            Float sum = 0.0f;
            for(Building building : buildings) {
                sum += building.getArea();
                logger.debug("Area sum changed to {}", sum);
            }

            response.put("area", sum);
        }

        return response;
    }

	/**
	 * Method : Post
	 * Example json response
	 {
	 "area": 50
	 }
	 *
	 *  URL: 127.0.0.1:8080/getArea[object_id]
	 *
	 **/

	//	private String objectId;

	@PostMapping("/getArea{objectId}")
	public Map<String, Object> getAreaId(@RequestBody String json ) {
		Map<String, Object> response = new HashMap<>();

		// String x = objectId;
		ArrayList<Building> buildings = null;
		try {
			buildings = JSONToBuildingParser.getBuildings(json);
			logger.info("Building parsed from json");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		if(buildings == null) {
			response.put("error", "error");

		} else {
//			Float sum = 1.0f;

			Float sum = 1.0f;
			if (objectId.compareTo("1") == 1) {
				sum = 2.0f;
			}

			for(Building building : buildings) {
//				building.
//				sum += building.getArea();
				logger.debug("Area sum changed to {}", sum);
			}

			response.put("area", sum);
		}

		return response;
	}

    /**
	* Method : Post
	* Example json response
	{
    	"volume": 500
     }
	*
	*  URL: 127.0.0.1:8080/getVolume
	*
	**/
    @PostMapping("/getVolume")
	public Map<String, Object> getVolume(@RequestBody String json){
		Map<String, Object> response = new HashMap<>();

		ArrayList<Building> buildings = null;
		try{
			buildings = JSONToBuildingParser.getBuildings(json);
			logger.info("Building parsed from json");
		} catch (JSONException e){
			e.printStackTrace();
		}

		if(buildings == null) response.put("error", "error");
		else{
			Float sum = 0.0f;
			for(Building building: buildings){
				sum += building.getVolume();
			}
			logger.debug("Volume: {}", sum);
			response.put("volume", sum);
		}
		return response;
	}
	/**
	* Method: Post
	* Example json response
	*{
    	"light": 50
     }
	*
	* URL: 127.0.0.1:8080/avgLight
	*
	**/
    @PostMapping("/avgLight")
    public Map<String, Object> avgLight(@RequestBody String json ) {
        Map<String, Object> response = new HashMap<>();

        ArrayList<Building> buildings = null;
        try {
            buildings = JSONToBuildingParser.getBuildings(json);
            logger.info("Building parsed from json");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(buildings == null) {
            response.put("error", "error");

        } else {
            Float sum = 0.0f;
            for(Building building : buildings) {
                sum += building.avgLight();
                logger.debug("Average light sum changed to {}", sum);
            }

            response.put("light", sum);
        }

        return response;
    }
    /**
	* Method: Post
	* Example json response
	*{
    	"heating": 50
     }
	*
	* URL: 127.0.0.1:8080/avgHeating
	*
	**/
    @PostMapping("/avgHeating")
    public Map<String, Object> avgHeating(@RequestBody String json ) {
        Map<String, Object> response = new HashMap<>();

        ArrayList<Building> buildings = null;
        try {
            buildings = JSONToBuildingParser.getBuildings(json);
            logger.info("Building parsed from json");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(buildings == null) {
            response.put("error", "error");

        } else {
            Float sum = 0.0f;
            for(Building building : buildings) {
                sum += building.avgHeating();
                logger.debug("Average heating sum changed to {}", sum);
            }

            response.put("heating", sum);
        }

        return response;
    }
}
