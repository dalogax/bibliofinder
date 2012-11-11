package com.dalogax.bibliofinder;

import java.util.List;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import com.actionbarsherlock.app.SherlockMapActivity;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class AndroidMapas extends SherlockMapActivity {

	private MapView mapa;
	private MapController controlMapa;
	private Double latitud;
	private Double longitud;
	private LocationManager mlocManager;
	private UserLocationListener mloclistener;
	private List<Overlay> capas;
	private UserOverlay userOverlay;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTheme(com.actionbarsherlock.R.style.Theme_Sherlock);
        setContentView(R.layout.activity_android_mapas);
        
        //Obtenemos una referencia al control MapView
        mapa = (MapView)findViewById(R.id.mapa);
        
        //Mostramos los controles de zoom sobre el mapa
        mapa.setBuiltInZoomControls(true);
        
        //Ponemos el modo satelite
        mapa.setSatellite(true);
        
        //Posicion Coruña
        latitud = 43.36*1E6;
        longitud = -8.41*1E6;
        
        //Obtenemos la ubicación del usuario
        mlocManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
    	mloclistener = new UserLocationListener(getApplicationContext(),this);
        getUserLocation();
        
        //Centramos el mapa sobre el usuario
        centerMap();
        
        //Obtenemos las capas del mapa
        capas = mapa.getOverlays();
        
        //Pintamos al usuario
        drawUser();
        
        //Pintamos las bibliotecas
        capas.add(PopulateBiblio.getBibliotecas(getResources(),mapa,this));
        mapa.postInvalidate();
        
    }
    
    private void getUserLocation(){
    	
    	mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mloclistener);
    	
    	Location userLocation = mlocManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
    	if (userLocation==null){
    		userLocation = mlocManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
    	}
    	if (userLocation!=null){
    		latitud = userLocation.getLatitude()*1E6;
    		longitud = userLocation.getLongitude()*1E6;
    	}
    }
    
    public void centerMap(){
    	GeoPoint loc =
            new GeoPoint(latitud.intValue(), longitud.intValue());
 
        controlMapa = mapa.getController();
        controlMapa.animateTo(loc);
        controlMapa.setZoom(15);
    }
    
    public void drawUser(){
    	if (userOverlay == null){
    		userOverlay = new UserOverlay(getResources(),latitud,longitud);
            capas.add(userOverlay);

    	}
    	else{
    		userOverlay.setLatitud(latitud);
    		userOverlay.setLongitud(longitud);
    	}
        mapa.postInvalidate();
    }
    
    @Override
    protected boolean isRouteDisplayed() {
    	return false;
    }

	public MapView getMapa() {
		return mapa;
	}

	public void setMapa(MapView mapa) {
		this.mapa = mapa;
	}

	public Double getLatitud() {
		return latitud;
	}

	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}

	public Double getLongitud() {
		return longitud;
	}

	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}

	public List<Overlay> getCapas() {
		return capas;
	}

	public void setCapas(List<Overlay> capas) {
		this.capas = capas;
	}
}
