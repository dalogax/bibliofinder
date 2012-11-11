package com.dalogax.bibliofinder;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.OverlayItem;

public class BibliotecaOverlayItem extends OverlayItem{

	private String telefono;
	private String email;
	
	public BibliotecaOverlayItem(GeoPoint arg0, String arg1, String arg2) {
		super(arg0, arg1, arg2);
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
