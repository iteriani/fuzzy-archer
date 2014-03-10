package Models;

import org.json.JSONException;
import org.json.JSONObject;

public class PlaceItFactory {

	public static PlaceIt CreatePlaceIt(JSONObject obj) throws JSONException{
		if(obj.getString("category")!= null){
			CategoryPlaceIt placeit = new CategoryPlaceIt(obj.getString("title"),
											obj.getString("description"));	
			placeit.setActiveDate(obj.getLong("active_date"));
			placeit.setCategory(obj.getString("category"));
			placeit.setID(obj.getString("_id"));
			return placeit;
		}else if(obj.getLong("latitude")!= 0 && obj.getLong("longitude") != 0){
			LocationPlaceIt placeit = new LocationPlaceIt(obj.getString("title"),
													obj.getString("description"));
			placeit.setActiveDate(obj.getLong("active_date"));
			placeit.setID(obj.getString("_id"));
			placeit.setLatitude(obj.getLong("latitude"));
			placeit.setLongitude(obj.getLong("longitude"));
		}
		return null;
	}
	
	public static JSONObject JSONify(PlaceIt placeit) throws JSONException{
		JSONObject obj = new JSONObject();
		obj.put("title", placeit.getTitle());
		obj.put("description", placeit.getDescription());
		obj.put("active_date", placeit.getActiveDate().getTime());
		obj.put("start_week",0);
		obj.put("day", 0);
		obj.put("week", 0);
		if(placeit.getClass() == LocationPlaceIt.class){
			LocationPlaceIt locPlaceIt = (LocationPlaceIt) placeit;
			obj.put("latitude", locPlaceIt.getLatitude());
			obj.put("longitude", locPlaceIt.getLongitude());
		}else if (placeit.getClass() == CategoryPlaceIt.class){
			CategoryPlaceIt categoryplaceit = (CategoryPlaceIt)
			 placeit;
			obj.put("category", categoryplaceit.getCategory());
		}
		return obj;
	}
}
