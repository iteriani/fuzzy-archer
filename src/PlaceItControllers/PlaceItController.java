package PlaceItControllers;

import java.util.List;
import java.util.Vector;

import Models.PlaceIt;
import PlaceItDB.iPlaceItModel;
import android.location.Location;

import com.classproj.placeit.iView;
import com.google.android.gms.maps.model.LatLng;

public class PlaceItController {

	private iPlaceItModel db;
	private iView view;
	private List<PlaceIt> placeits;
	private PlaceItScheduler scheduler;

	public PlaceItController(iPlaceItModel db, iView view,
			PlaceItScheduler scheduler) {
		this.db = db;
		this.view = view;

		this.placeits = new Vector<PlaceIt>();
		this.scheduler = scheduler;
	}

	public void initializeMarkers() {

		placeits = db.getAllPlaceIts();
		for (PlaceIt pc : placeits) {
			if (pc.isActive()) {
				view.addMarker(pc);
			}
		}
	}

	public void AddPlaceIt(String titleText, String descText,
			final LatLng position) {

		PlaceIt placeit = new PlaceIt(titleText, descText, position.longitude,
				position.latitude);
		placeits.add(placeit);
		db.addPlaceIt(placeit);


		/* Add marker to the map */
		view.addMarker(placeit);

	}
	
	public List<PlaceIt> getList()
	{
		return placeits;
	}

	public List<PlaceIt> checkCoordinates(Location coords) {
		
		List<PlaceIt> clean = new Vector<PlaceIt>();
		LatLng currLoc = new LatLng(coords.getLatitude(), coords.getLongitude());
		
		for (int i = 0; i < placeits.size(); i++) {
			PlaceIt currMarker = placeits.get(i);
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
				if (dist <= 0.5) {
					clean.add(placeits.get(i));
				}	
			}
		}
		
		this.view.notifyUser(clean);
		return clean;

	}

}
