package PlaceItDB;

import HTTP.PLScheduleReceiver;
import HTTP.PlaceItListReceiver;
import HTTP.PlaceItReceiver;
import HTTP.RequestReceiver;
import Models.PLSchedule2;
import Models.PlaceIt;

public interface iPlaceItModelv2 {
	// Adding new PlaceIt
	public void addPlaceIt(PlaceIt PlaceIt, PlaceItReceiver receiver);

	// Getting All PlaceIts
	public void getAllPlaceIts(PlaceItListReceiver receiver);

	// Updating single PlaceIt
	public void updatePlaceIt(PlaceIt placeit,RequestReceiver receiver);

	// Deleting single PlaceIt
	public void deletePlaceIt(PlaceIt PlaceIt, RequestReceiver receiver);

	// deleteAll
	public void deleteAll();

	public void deactivatePlaceit(PlaceIt placeit, RequestReceiver receiver);

	public void getPlaceIt(String id, PlaceItReceiver receiver);
	
	public void addSchedule(PLSchedule2 schedule, PLScheduleReceiver receiver);
}
