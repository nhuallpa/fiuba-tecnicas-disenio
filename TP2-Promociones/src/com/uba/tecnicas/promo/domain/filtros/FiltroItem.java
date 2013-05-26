package com.uba.tecnicas.promo.domain.filtros;

import com.uba.tecnicas.promo.domain.Filtro;
import com.uba.tecnicas.promo.domain.Item;

public class FiltroItem implements Filtro {
	private Item item;
	
	public FiltroItem(Item item) {
		this.item = item;
	}
	
	@Override
	public boolean seCumple(Item entrada) {
		return item.getProducto().equals(entrada.getProducto()) &&
				item.getCantidad() >= entrada.getCantidad();
	}
}
