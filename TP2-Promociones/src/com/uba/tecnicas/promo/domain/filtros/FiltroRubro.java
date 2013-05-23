package com.uba.tecnicas.promo.domain.filtros;

import com.uba.tecnicas.promo.domain.FiltroItem;
import com.uba.tecnicas.promo.domain.Item;

public class FiltroRubro extends FiltroItem {
	private String rubro;
	
	public FiltroRubro(String rubro) {
		this.rubro = rubro;
	}
	
	@Override
	public boolean seCumple(Item entrada) {
		return entrada.getProducto().getRubro() == rubro;
	}

}
