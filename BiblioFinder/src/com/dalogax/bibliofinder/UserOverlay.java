package com.dalogax.bibliofinder;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

public class UserOverlay extends Overlay {
	private Double latitud;
    private Double longitud;
    private Resources resources;
    
    public UserOverlay(Resources resources, Double latitud, Double longitud){
    	this.resources = resources;
    	this.latitud = latitud;
    	this.longitud = longitud;
    }
    
    @Override
    public void draw(Canvas canvas, MapView mapView, boolean shadow)
    {
        Projection projection = mapView.getProjection();
        GeoPoint geoPoint =
            new GeoPoint(latitud.intValue(), longitud.intValue());
 
        if (shadow == false)
        {
            Point centro = new Point();
            projection.toPixels(geoPoint, centro);
 
            //Definimos el pincel de dibujo
            Paint p = new Paint();
            p.setColor(Color.BLUE);
            //p.setTextSize(35);
 
            //Marca Ejemplo 1: Círculo y Texto
            //canvas.drawCircle(centro.x, centro.y, 10, p);
            //canvas.drawText("You", centro.x+10, centro.y+5, p);
            
            //Dibujamos al usuario
            Bitmap bitmap = BitmapFactory.decodeResource(resources, R.drawable.nerdi);
            bitmap = Bitmap.createScaledBitmap(bitmap, 119, 128, false);
            canvas.drawBitmap(bitmap, centro.x-32, centro.y-91, p);
            
        }
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
    
}
