package com.uba.tecnicas.promo.domain;

public interface Venta {
	
	public double getTotal();

	public void agregar(Producto producto, int cantidad);

}
