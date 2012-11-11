package com.dalogax.bibliofinder;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.Toast;

public class UserLocationListener implements LocationListener {

	private Context context;
	private AndroidMapas androidMapas;
	
	public UserLocationListener(Context context,AndroidMapas androidMapas){
		super();
		this.context = context;
		this.androidMapas = androidMapas;
	}
	
	@Override
	public void onLocationChanged(Location loc) {
		androidMapas.setLatitud(loc.getLatitude()*1E6);
		androidMapas.setLongitud(loc.getLongitude()*1E6);
		androidMapas.centerMap();
		androidMapas.drawUser();
	}

	@Override
	public void onProviderDisabled(String arg0) {
		Toast.makeText(context, R.string.gpsOff, Toast.LENGTH_LONG).show();
	}

	@Override
	public void onProviderEnabled(String arg0) {
		Toast.makeText(context, R.string.gpsOn, Toast.LENGTH_LONG).show();
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {

	}

}
