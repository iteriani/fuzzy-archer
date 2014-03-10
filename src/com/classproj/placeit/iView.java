package com.classproj.placeit;

import java.io.IOException;
import java.util.List;

import org.json.JSONException;

import com.google.android.gms.maps.model.Marker;

import Models.PlaceIt;

public interface iView {
	
	public void addMarker(PlaceIt pc);
	
	public void removeMarker(PlaceIt pc);

	public Marker getMarker(int id);

	void notifyUser(List<PlaceIt> placeits, String ControllerType) throws IllegalStateException, IOException, JSONException;

}
