package com.uba.tecnicas.promo.domain.filtros;

import com.uba.tecnicas.promo.domain.Filtro;
import com.uba.tecnicas.promo.domain.Item;

public class FiltroOr extends FiltroAbstracto {
	private Filtro uno;
	private Filtro otro;
	
	public FiltroOr(Filtro uno, Filtro otro) {
		this.uno = uno;
		this.otro = otro;
	}
	
	@Override
	public boolean seCumple(Item entrada) {
		return uno.seCumple(entrada) || otro.seCumple(entrada);
	}

}
