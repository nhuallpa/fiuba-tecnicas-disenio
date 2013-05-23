package com.uba.tecnicas.promo.domain;

public class ItemComprado implements Item {

	private Producto producto = null;
	private double cantidad;
	
	public ItemComprado(Producto producto, double cantidad) {
		this.producto = producto;
		this.cantidad = cantidad;
	}

	@Override
	public double getImporte() {
		return cantidad * producto.getPrecio();
	}

	@Override
	public Producto getProducto() {
		return producto;
	}

}
