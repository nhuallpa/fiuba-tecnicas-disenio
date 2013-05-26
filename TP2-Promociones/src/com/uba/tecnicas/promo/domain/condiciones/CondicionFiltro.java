package com.uba.tecnicas.promo.domain.condiciones;

import java.util.List;

import com.uba.tecnicas.promo.domain.CondicionOferta;
import com.uba.tecnicas.promo.domain.Filtro;
import com.uba.tecnicas.promo.domain.Item;
import com.uba.tecnicas.promo.domain.Venta;

public class CondicionFiltro implements CondicionOferta {
	private Filtro filtro;
	
	public CondicionFiltro(Filtro filtro) {
		this.filtro = filtro;
	}
	
	@Override
	public boolean seCumple(Venta venta, List<Item> aplicantes) {
		List<Item> items = venta.getItems(filtro);
		if (items.size() > 0) {
			aplicantes.addAll(items);
			return true;
		}
		else
			return false;
	}

}
