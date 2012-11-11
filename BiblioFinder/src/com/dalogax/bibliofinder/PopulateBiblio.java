package com.dalogax.bibliofinder;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.res.Resources;
import android.util.Log;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;

public abstract class PopulateBiblio {

	public static BibliotecaOverlay getBibliotecas(Resources resources, MapView mapa, AndroidMapas androidMapas){
		BibliotecaOverlay bibliotecaOverlay = new BibliotecaOverlay(resources,mapa,androidMapas);
		
		InputStream is = resources.openRawResource(R.raw.bibliotecas);
		Writer writer = new StringWriter();
		char[] buffer = new char[1024];
		try {
		    Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		    int n;
		    while ((n = reader.read(buffer)) != -1) {
		        writer.write(buffer, 0, n);
		    }
		
		    is.close();

			String jsonString = writer.toString();
			
			JSONObject json = new JSONObject(jsonString);
			JSONArray jArray = json.getJSONArray("list");
			
			for(int i=0;i<jArray.length();i++){
				JSONObject json_data = jArray.getJSONObject(i);
				GeoPoint geoPoint = new GeoPoint((int)json_data.getDouble("latitude"),
						(int)json_data.getDouble("longitude"));
				BibliotecaOverlayItem bov = new BibliotecaOverlayItem(geoPoint,
						json_data.getString("name"),json_data.getString("description"));
				bov.setTelefono(json_data.getString("telf"));
				bov.setEmail(json_data.getString("email"));
				bibliotecaOverlay.addOverlay(bov);
			}
		} catch(Exception e){
			Log.v("PopulateBiblio", e.toString());
		}

		return bibliotecaOverlay;
	}
}
