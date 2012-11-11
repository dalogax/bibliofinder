package com.dalogax.bibliofinder;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;
import com.google.android.maps.Projection;
import com.readystatesoftware.mapviewballoons.BalloonItemizedOverlay;

public class BibliotecaOverlay extends BalloonItemizedOverlay<OverlayItem> {
	
    private Resources resources;
    private Context context;
    private List<BibliotecaOverlayItem> bibliotecas;
    private MapView mapa;
    private AndroidMapas androidMapas;
    
    public BibliotecaOverlay(Resources resources, MapView mapa,AndroidMapas androidMapas){
    	super(boundCenterBottom(resources.getDrawable(R.drawable.biblio)),mapa);
    	bibliotecas = new ArrayList<BibliotecaOverlayItem>();
    	this.resources = resources;
    	this.mapa = mapa;
    	this.context = mapa.getContext();
    	this.androidMapas=androidMapas;
    	populate();
    }
    
//    @Override
//    public void draw(Canvas canvas, MapView mapView, boolean shadow)
//    {
//    	for (BibliotecaOverlayItem boi : bibliotecas){
//	        Projection projection = mapView.getProjection();
//	        GeoPoint geoPoint =
//	            new GeoPoint(boi.getPoint().getLatitudeE6(), boi.getPoint().getLongitudeE6());
//	 
//	        if (shadow == false)
//	        {
//	            Point centro = new Point();
//	            projection.toPixels(geoPoint, centro);
//	 
//	            //Definimos el pincel de dibujo
//	            Paint p = new Paint();
//	            //p.setColor(Color.BLUE);
//	            //p.setTextSize(35);
//	 
//	            //Marca Ejemplo 1: Círculo y Texto
//	            //canvas.drawCircle(centro.x, centro.y, 10, p);
//	            //canvas.drawText(name, centro.x+10, centro.y+5, p);
//	            
//	            //Dibujamos la biblioteca
//	            Bitmap bitmap = BitmapFactory.decodeResource(resources, R.drawable.biblio);
//	            
//	            bitmap = Bitmap.createScaledBitmap(bitmap, 64, 109, false);
//	            canvas.drawBitmap(bitmap, centro.x-32, centro.y-91, p);
//	        }
//        }
//    }

	public List<BibliotecaOverlayItem> getBibliotecas() {
		return bibliotecas;
	}

	public void setBibliotecas(List<BibliotecaOverlayItem> bibliotecas) {
		this.bibliotecas = bibliotecas;
	}

	@Override
	protected OverlayItem createItem(int i) {
		return bibliotecas.get(i);
	}

	@Override
	public int size() {
		return bibliotecas.size();
	}
	
	public void addOverlay(BibliotecaOverlayItem overlay) {
		bibliotecas.add(overlay);
	    populate();
	}
	
	@Override
	protected boolean onBalloonTap(int index, OverlayItem item) {
//		Toast.makeText(context, "onBalloonTap for overlay index " + index,
//				Toast.LENGTH_LONG).show();
		AlertDialog.Builder builder = new AlertDialog.Builder(androidMapas);
	    builder.setTitle(item.getTitle());
	    String[] items = new String[3];
	    items[0] = item.getSnippet();
	    items[1] = ((BibliotecaOverlayItem)item).getTelefono();
	    items[2] = ((BibliotecaOverlayItem)item).getEmail();
	    builder.setItems(items, null);
	    builder.create().show();
		return true;
	}
	
}
