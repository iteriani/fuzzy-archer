package HTTP;

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
import Models.PLSchedule2;
import Models.PlaceIt;
import Models.PlaceItFactory;
import PlaceItDB.iPlaceItModelv2;

public class PlaceItWebService extends WebService implements iPlaceItModelv2 {
	HttpContext context;

	public PlaceItWebService(HttpContext context) {
		this.context = context;
	}

	@Override
	public void getPlaceIt(String id, final PlaceItReceiver receiver) {
		new RequestTask(new RequestReceiver() {

			@Override
			public void receiveTask(String s) {
				try {
					receiver.receivePlaceIt(PlaceItFactory
							.CreatePlaceIt(new JSONObject(s)));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}, context).execute(ALL_PLACEITS + id);
	}

	@Override
	public void getAllPlaceIts(final PlaceItListReceiver receiver) {
		new RequestTask(new RequestReceiver() {

			@Override
			public void receiveTask(String s) {
				try {
					JSONArray arr = new JSONArray(s);
					List<PlaceIt> placeits = new Vector<PlaceIt>();
					for (int i = 0; i < arr.length(); i++) {
						placeits.add(PlaceItFactory.CreatePlaceIt(arr
								.getJSONObject(i)));
					}
					receiver.receivePlaceIts(placeits);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}, context).execute(ALL_PLACEITS);
	}

	@Override
	public void updatePlaceIt(PlaceIt placeit, RequestReceiver receiver) {
		List<NameValuePair> values = new Vector<NameValuePair>();
		NameValuePair id = new BasicNameValuePair("id", placeit.getID());
		NameValuePair activedate = new BasicNameValuePair("active_date",
				Long.toString(placeit.getActiveDate().getTime()));
		values.add(id);
		values.add(activedate);
		new RequestTask(receiver, context, values).execute(UPDATE_PLACEIT);
	}

	@Override
	public void deletePlaceIt(PlaceIt PlaceIt, RequestReceiver receiver) {
		List<NameValuePair> values = new Vector<NameValuePair>();
		NameValuePair id = new BasicNameValuePair("id", PlaceIt.getID());
		values.add(id);
		new RequestTask(receiver, context, values).execute(DELETE_PLACEIT);

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
	 * title : String, description : String, latitude : Number, longitude :
	 * Number, active_date : Number, start_week : Number, day : Number, week :
	 * Number, category : String,
	 */
	@Override
	public void addPlaceIt(PlaceIt placeit, final PlaceItReceiver receiver) {
		// TODO Auto-generated method stub
		List<NameValuePair> values = new Vector<NameValuePair>();
		values.add(new BasicNameValuePair("title", placeit.getTitle()));
		values.add(new BasicNameValuePair("description", placeit
				.getDescription()));
		values.add(new BasicNameValuePair("active_date", Long.toString(placeit
				.getActiveDate().getTime())));
		if (placeit instanceof LocationPlaceIt) {
			LocationPlaceIt lplaceit = (LocationPlaceIt) placeit;
			values.add(new BasicNameValuePair("latititude", Double
					.toString(lplaceit.getLatitude())));
			values.add(new BasicNameValuePair("longitude", Double
					.toString(lplaceit.getLongitude())));
			values.add(new BasicNameValuePair("category", ("")));
		} else {
			CategoryPlaceIt cplaceit = (CategoryPlaceIt) placeit;
			values.add(new BasicNameValuePair("category", (cplaceit
					.getCategory())));
			values.add(new BasicNameValuePair("latititude", "0"));
			values.add(new BasicNameValuePair("longitude", "0"));
		}
		values.add(new BasicNameValuePair("start_week", ""));
		values.add(new BasicNameValuePair("day", ""));
		values.add(new BasicNameValuePair("week", ""));

		new RequestTask(new RequestReceiver() {

			@Override
			public void receiveTask(String s) {
				try {
					receiver.receivePlaceIt(PlaceItFactory
							.CreatePlaceIt(new JSONObject(s)));
				} catch (JSONException e) {
					receiver.receivePlaceIt(null);
					e.printStackTrace();
				}

			}

		}, context, values).execute(SINGLE_PLACEIT);
	}

	public void addSchedule(PLSchedule2 schedule, final PlaceItReceiver receiver) {
		List<NameValuePair> values = new Vector<NameValuePair>();
		values.add(new BasicNameValuePair("id", schedule.getPlaceItId()));
		values.add(new BasicNameValuePair("start_week", Integer
				.toString(schedule.getStartWeek())));
		values.add(new BasicNameValuePair("day", Integer.toString(schedule
				.getWeek())));
		values.add(new BasicNameValuePair("week", Integer.toString(schedule
				.getDay())));
		new RequestTask(new RequestReceiver() {

			@Override
			public void receiveTask(String s) {
				try {
					receiver.receivePlaceIt(PlaceItFactory
							.CreatePlaceIt(new JSONObject(s)));
				} catch (JSONException e) {
					receiver.receivePlaceIt(null);
					e.printStackTrace();
				}
			}

		}, context, values).execute(ADD_SCHEDULE);
	}

	public void getSchedule(final PlaceIt placeit, final PLScheduleReceiver receiver) {
		new RequestTask(new RequestReceiver() {
			@Override
			public void receiveTask(String s) {
				try {
					JSONObject obj = new JSONObject(s);
					PLSchedule2 schedule = new PLSchedule2(placeit.getID());
					schedule.setStartWeek(obj.getInt("start_week"));
					schedule.setWeek(obj.getInt("week"));
					schedule.setDay(obj.getInt("day"));
					receiver.receiveSchedule(schedule);
					
				} catch (JSONException e) {
					receiver.receiveSchedule(null);
					e.printStackTrace();
				}

			}

		}, context).execute(ALL_PLACEITS + placeit.getID());
	}

}
