package com.uba.tecnicas.promo.domain;

public interface Item {
	public Producto getProducto();
	public int getCantidad();
	public void setCantidad(int cantidad);
	public double getImporte();
}
