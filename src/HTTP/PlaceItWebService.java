package HTTP;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Models.CategoryPlaceIt;
import Models.LocationPlaceIt;
import Models.PlaceIt;
import Models.PlaceItFactory;
import PlaceItDB.iPlaceItModelv2;

public class PlaceItWebService extends WebService implements iPlaceItModelv2 {
	HttpContext context;
	
	public PlaceItWebService(HttpContext context){
		this.context = context;
	}
	@Override
	public void getPlaceIt(String id, RequestReceiver receiver) {
		new RequestTask(receiver,context).execute(SINGLE_PLACEIT + id);
	}

	@Override
	public void getAllPlaceIts(RequestReceiver receiver){
		new RequestTask(receiver, context).execute(ALL_PLACEITS);
	}

	@Override
	public void updatePlaceIt(PlaceIt placeit,RequestReceiver receiver) {
		List<NameValuePair> values = new Vector<NameValuePair>();
		NameValuePair id = new BasicNameValuePair("id", placeit.getID());
		NameValuePair activedate = new BasicNameValuePair("active_date",
				String.valueOf(placeit.getActiveDate().getTime()));
		values.add(id);
		values.add(activedate);
		new RequestTask(receiver, context, values).execute(UPDATE_PLACEIT);
	}

	@Override
	public void deletePlaceIt(PlaceIt PlaceIt, RequestReceiver receiver) {
		List<NameValuePair> values = new Vector<NameValuePair>();
		NameValuePair id = new BasicNameValuePair("id", PlaceIt.getID());
		new RequestTask(receiver, context, values).execute(UPDATE_PLACEIT);

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public void deactivatePlaceit(PlaceIt placeit, RequestReceiver receiver) {
		placeit.setActiveDate(0);
		this.updatePlaceIt(placeit, receiver);

	}
	/*
	 *     title : String,
    	description : String,
    	latitude : Number,
    	longitude : Number,
    	active_date : Number,
    	start_week : Number,
    	day : Number,
    	week : Number,
    	category : String,
	 * */
	@Override
	public void addPlaceIt(PlaceIt placeit, RequestReceiver receiver) {
		// TODO Auto-generated method stub
		List<NameValuePair> values = new Vector<NameValuePair>();
		values.add(new BasicNameValuePair("title", placeit.getTitle()));
		values.add(new BasicNameValuePair("description", placeit.getDescription()));
		values.add(new BasicNameValuePair("active_date", 
				Long.toString(placeit.getActiveDate().getTime())));
		LocationPlaceIt lplaceit = (LocationPlaceIt) placeit;
		values.add(new BasicNameValuePair("latititude", Double.toString(lplaceit.getLatitude())));
		values.add(new BasicNameValuePair("longitude", Double.toString(lplaceit.getLongitude())));
		CategoryPlaceIt cplaceit = (CategoryPlaceIt) placeit;
		values.add(new BasicNameValuePair("category", (cplaceit.getCategory())));
	}

}
