package com.uba.tecnicas.promo.domain.filtros;

import com.uba.tecnicas.promo.domain.Producto;
import com.uba.tecnicas.promo.domain.Item;

public class FiltroProducto extends FiltroAbstracto {
	private Producto producto;
	
	public FiltroProducto(Producto producto) {
		this.producto = producto;
	}
	
	@Override
	public boolean seCumple(Item entrada) {
		return entrada.getProducto().equals(producto);
	}
}
