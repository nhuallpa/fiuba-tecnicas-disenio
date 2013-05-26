package com.uba.tecnicas.promo.domain.condiciones;

import java.util.List;

import com.uba.tecnicas.promo.domain.CondicionOferta;
import com.uba.tecnicas.promo.domain.Item;
import com.uba.tecnicas.promo.domain.Producto;
import com.uba.tecnicas.promo.domain.Venta;
import com.uba.tecnicas.promo.domain.filtros.FiltroProducto;

public class CondicionProducto implements CondicionOferta {
	private Producto producto;
	
	public CondicionProducto(Producto producto) {
		this.producto = producto;
	}
		
	@Override
	public List<Item> getAplicantes(Venta venta) {
		return venta.getItems(new FiltroProducto(producto));
	}
}
