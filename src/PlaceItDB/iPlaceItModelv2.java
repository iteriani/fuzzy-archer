package PlaceItDB;

import java.io.IOException;
import java.util.List;

import org.json.JSONException;

import HTTP.RequestReceiver;
import Models.PlaceIt;
import Models.PlaceIt;

public interface iPlaceItModelv2 {
	// Adding new PlaceIt
	public void addPlaceIt(PlaceIt PlaceIt, RequestReceiver receiver);

	// Getting All PlaceIts
	public void getAllPlaceIts(RequestReceiver receiver);

	// Updating single PlaceIt
	public void updatePlaceIt(PlaceIt placeit,RequestReceiver receiver);

	// Deleting single PlaceIt
	public void deletePlaceIt(PlaceIt PlaceIt, RequestReceiver receiver);

	// deleteAll
	public void deleteAll();

	public void deactivatePlaceit(PlaceIt placeit, RequestReceiver receiver);

	public void getPlaceIt(String id, RequestReceiver receiver);
}
