package com.uba.tecnicas.promo.domain;

public class ItemComprado implements Item {
	private Producto producto;
	private int cantidad;
	
	public ItemComprado(Producto producto, int cantidad) {
		this.producto = producto;
		this.cantidad = cantidad;
	}
	
	@Override
	public int getCantidad() {
		return cantidad;
	}

	@Override
	public double getImporte() {
		return cantidad * producto.getPrecio();
	}

	@Override
	public Producto getProducto() {
		return producto;
	}
	
	@Override
	public void sumar(int cantidad) {
		this.cantidad += cantidad;
	}
	
	@Override
	public void restar(int cantidad) {
		this.cantidad -= cantidad;
	}
}
