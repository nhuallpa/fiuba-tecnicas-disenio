package com.uba.tecnicas.promo.domain;

import java.util.List;

public class ItemDescuento extends ItemComprado {
	private String nombre;
	private double porcentaje;
	private List<Item> aplicantes;
	
	public ItemDescuento(String nombre, Producto producto,
			double porcentaje, List<Item> aplicantes) {
		super(producto, 1);
		this.nombre = nombre;
		this.porcentaje = porcentaje;
		this.aplicantes = aplicantes;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public List<Item> getAplicantes() {
		return aplicantes;
	}
	
	public double getCantidadAplicantes(Producto producto) {
		int cantidad = 0;
		for (Item item : aplicantes) {
			if (item.getProducto().equals(producto))
				cantidad += item.getCantidad();
		}
		return cantidad;
	}

	@Override
	public double getImporte() {
		return -1*super.getImporte()*porcentaje;
	}
}
