package com.uba.tecnicas.promo.domain;

public class Producto {
	private String nombre;
	private double precio;
	private String rubro;
	
	public Producto(String nombre, double precio, String rubro) {
		this.nombre = nombre;
		this.precio = precio;
		this.rubro = rubro;
	}

	public double getPrecio() {
		return this.precio;
	}

	public String getRubro() {
		return rubro;
	}

	public String getNombre() {
		return nombre;
	}
}
