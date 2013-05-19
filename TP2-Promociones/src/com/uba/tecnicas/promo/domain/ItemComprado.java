package com.uba.tecnicas.promo.domain;

public class ItemComprado implements Item {

	private Producto producto = null;
	
	public ItemComprado(Producto producto, int cantidad) {
		this.producto = producto;
	}

	@Override
	public double getPrecio() {
		return producto.getPrecio();
	}

}
