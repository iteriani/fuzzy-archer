package PlaceItControllers;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import org.json.JSONException;

import Models.LocationPlaceIt;
import Models.PlaceIt;
import PlaceItDB.iPlaceItModel;
import PlaceItDB.iPlaceItModelv2;
import android.content.Context;
import android.location.Location;
import android.widget.Toast;

import android.annotation.SuppressLint;
import android.location.Location;
import com.classproj.placeit.iView;
import com.google.android.gms.maps.model.LatLng;

public class PlaceItController {

	private iPlaceItModelv2 db;
	private iView view;
	private List<PlaceIt> placeits;
	List<PlaceIt> nonActive = new Vector<PlaceIt>();
	List<PlaceIt> active = new Vector<PlaceIt>();
	public PlaceItController(iPlaceItModelv2 db2, iView view) {
		this.db = db2;
		this.view = view;

		this.placeits = new Vector<PlaceIt>();
	}

	public void initializeView() throws IllegalStateException, IOException, JSONException {
/*
 * 
 * 		db.getAllPlaceIts(new PlaceItListReceiver(){
 * 			public void receivePlaceIts(List<PlaceIt> placeits){
 * }	
 * 	});
		placeits = db.getAllPlaceIts();
		for (PlaceIt pc : placeits) {
			if (pc.isActive()) {
				view.addMarker(pc);
			}
		}*/
	}

	@SuppressLint("NewApi")
	public PlaceIt AddPlaceIt(String titleText, String descText,
			final LatLng position) {
		return null;
	/*	
		// If title and description empty. no Place-It is created, and return null.
		if (titleText.length() == 0 && descText.length() == 0) {
			return null;
		}
		
		// If title is empty but description is not, take first 10 chars of the description to be the title.
		if (titleText.length() == 0) {
			int descLength = descText.length();
			if (descLength < 10) {
				titleText = descText.substring(0, descLength);
			}
			else {
				titleText = descText.substring(0, 10);
			}
		}

		PlaceIt placeit = new LocationPlaceIt(titleText, descText, position.latitude,
				position.longitude);
		return placeit;
		
	/*	String insertId = db.addPlaceIt(placeit);
		placeit.setID(insertId);
		placeits.add(placeit);
		view.addMarker(placeit);
		return placeit;*/
	}
	
	public void deactivatePlaceIt(PlaceIt placeit){
		///db.deactivatePlaceit(placeit);
		view.removeMarker(placeit);
	}
	
	public void removePlaceIt(PlaceIt placeit){
		
	//	db.deletePlaceIt(placeit);
		view.removeMarker(placeit);
	}
	
	public List<PlaceIt> getList()
	{
		return placeits;
	}
	
	public boolean checkViscinity (Location currLoc, Location checkLoc)
	{
		return false;
		
	}
	public void deleteFromList(PlaceIt placeit) {
		
	}

	public List<PlaceIt> checkCoordinates(Location coords) throws IllegalStateException, IOException, JSONException {
		
		List<PlaceIt> clean = new Vector<PlaceIt>();
		LatLng currLoc = new LatLng(coords.getLatitude(), coords.getLongitude());
		for (int i = 0; i < placeits.size(); i++) {
			LocationPlaceIt currMarker = (LocationPlaceIt) placeits.get(i);
			Location start = new Location("Start");
			Location end = new Location("End");
			if (currLoc != null && currMarker != null) {
				start.setLatitude(currLoc.latitude);
				start.setLongitude(currLoc.longitude);
				end.setLongitude(currMarker.getLongitude());
				end.setLatitude(currMarker.getLatitude());
				float dist = start.distanceTo(end);
				// Convert to miles
				dist = (float) (dist * 0.000621371);
				if (dist <= .5) {
					clean.add(placeits.get(i));
				}	
			}
		}
		view.notifyUser(clean,"Controller");
		return clean;

	}
	public List<PlaceIt> getNonActivePlaceIts()
	{
		nonActive = new Vector<PlaceIt>();
		for (PlaceIt i : placeits)
		{
			if (!i.isActive())
			{
				nonActive.add(i);
			}
		}
		
		return nonActive;
	}
	
	public List<PlaceIt> getActiveList()
	{
		active = new Vector<PlaceIt>();
		for (PlaceIt i : placeits)
		{
			if (i.isActive())
			{
				active.add(i);
			}
		}
		
		return active;
	}
	
	public String movePlaceIts(int id)
	{
		deactivatePlaceIt(placeits.get(id));
		return placeits.get(id).getTitle();
	}

	public void deletePlaceIts(int id, Context cont)
	{
		Toast.makeText(cont, "Did it", Toast.LENGTH_LONG).show();
		this.removePlaceIt(placeits.get(id));
	}

	public iPlaceItModelv2 getDB() {
		return this.db;
	}

	public iView getView() {
		return this.view;
	}
	

}
