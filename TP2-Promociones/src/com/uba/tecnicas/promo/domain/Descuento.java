package com.uba.tecnicas.promo.domain;

public class Descuento {
	private String nombre;
	private double monto;
	
	public Descuento(String nombre, double monto) {
		this.nombre = nombre;
		this.monto = monto;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public double getImporte() {
		return -monto;
	}
}
