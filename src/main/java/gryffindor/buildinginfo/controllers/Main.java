package gryffindor.buildinginfo.controllers;

import gryffindor.buildinginfo.models.*;
import org.json.JSONException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
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

	/**
	 * Function getParametersById() searches locations for given id
	 * For searched location it calculates all parameters
	 * If given id equals 0 function returns summary parameters for all locations
	 * @return list of location parameters (area, volume, avgLight, avgHeating
	 */
	public ArrayList<Float> getParametersById(ArrayList<Building> buildings, int searchedId) {

		float sumArea = 0.0f, sumVolume = 0.0f, avgLight = 0.0f, avgHeating = 0.0f;
		for(Building building : buildings) {
			if (searchedId == 0) {
				sumArea += building.getArea();
				sumVolume += building.getVolume();
				avgLight += building.avgLight();
				avgHeating += building.avgHeating();
				continue;
			} else	if (building.getId() == searchedId) {
				sumArea = building.getArea();
				sumVolume = building.getVolume();
				avgLight = building.avgLight();
				avgHeating = building.avgHeating();
				break;
			} else {
				for (Floor floor : building.getFloors()) {
					if (floor.getId() == searchedId) {
						sumArea += building.getArea();
						sumVolume += building.getVolume();
						avgLight += building.avgLight();
						avgHeating += building.avgHeating();
						break;
					} else {
						for (Room room : floor.getRooms()) {
							if (room.getId() == searchedId) {
								sumArea += building.getArea();
								sumVolume += building.getVolume();
								avgLight += building.avgLight();
								avgHeating += building.avgHeating();
								break;
							}
						}
					}
				}
			}
		}

		ArrayList<Float> parameters  = new ArrayList<>(
				Arrays.asList(sumArea, sumVolume, avgLight, avgHeating)
		);

		return parameters;
	}

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
    public Map<String, Object> getArea(@RequestParam(value = "id", required = false) int searchedId, @RequestBody String json ) {
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
				if (building.getId() == searchedId) {
					sum += building.getArea();
					break;
				} else {
					for (Floor floor : building.getFloors()) {
						if (floor.getId() == searchedId) {
							sum += floor.getArea();
							break;
						} else {
							for (Room room : floor.getRooms()) {
								if (room.getId() == searchedId) {
									sum += room.getArea();
									break;
								}
							}
						}
					}
				}
			}
		if (sum == 0.0f) {
			response.put("error", "Id does not exist");
		}
//                sum += building.getArea();
		logger.debug("Area sum changed to {}", sum);
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
	 *  URL: 127.0.0.1:8080/getArea/{object_id}
	 *
	 **/
/*
	@PostMapping("/getArea/{objectId}")
	public Map<String, Object> getAreaId(@PathVariable int objectId, @RequestBody String json ) {
		Map<String, Object> response = new HashMap<>();

		ArrayList<Location> buildings = null;
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
			if (objectId == 1) {
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
*/
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
