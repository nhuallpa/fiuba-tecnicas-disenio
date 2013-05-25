package com.uba.tecnicas.promo.domain;

import java.util.ArrayList;
import java.util.List;

public class Descuento {
	private String nombre;
	private List<Item> aplicantes;
	private double monto;
	
	public Descuento(String nombre, double monto) {
		this.nombre = nombre;
		aplicantes = new ArrayList<Item>();
		this.monto = monto;
	}
	
	public Descuento(String nombre, double monto, List<Item> aplicantes) {
		this.nombre = nombre;
		this.aplicantes = aplicantes;
		this.monto = monto;
	}
	
	public void setAplicantes(List<Item> aplicantes) {
		this.aplicantes = aplicantes;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public List<Item> getAplicantes() {
		return aplicantes;
	}
	
	public double getMonto() {
		return monto;
	}
	
	public double getCantidadAplicantes(Producto producto) {
		double cantidad = 0;
		for (Item item : aplicantes) {
			if (item.getProducto().equals(producto))
				cantidad += item.getCantidad();
		}
		return cantidad;
	}
}
