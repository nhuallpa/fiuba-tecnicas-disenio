package com.uba.tecnicas.promo.domain;

public interface Item {
	public Producto getProducto();
	public int getCantidad();
	public double getImporte();
	public void sumar(int cantidad);
	public void restar(int cantidad);
}
