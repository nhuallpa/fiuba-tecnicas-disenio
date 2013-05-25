package com.uba.tecnicas.promo.domain;

public class Item {
	private Producto producto = null;
	private double cantidad;
	
	public Item(Producto producto, double cantidad) {
		this.producto = producto;
		this.cantidad = cantidad;
	}
	
	public double getCantidad() {
		return cantidad;
	}

	public double getImporte() {
		return cantidad * producto.getPrecio();
	}

	public Producto getProducto() {
		return producto;
	}

	public Item resta(Item item) throws ImposibleRestarItemException {
		if (this.producto.equals(item.producto) && cantidad < item.cantidad)
			return new Item(producto, cantidad);
		else
			throw new ImposibleRestarItemException();
	}
}
