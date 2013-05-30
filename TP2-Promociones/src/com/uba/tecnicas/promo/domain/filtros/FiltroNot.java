package com.uba.tecnicas.promo.domain.filtros;

import com.uba.tecnicas.promo.domain.Filtro;
import com.uba.tecnicas.promo.domain.Item;

public class FiltroNot extends FiltroAbstracto {
	private Filtro filtroANegar;
	
	public FiltroNot(Filtro filtroANegar) {
		this.filtroANegar = filtroANegar;
	}
	
	@Override
	public boolean seCumple(Item entrada) {
		return !filtroANegar.seCumple(entrada);
	}

}
