package com.uba.tecnicas.promo.domain.filtros;

import com.uba.tecnicas.promo.domain.Filtro;

public abstract class FiltroAbstracto implements Filtro {

	@Override
	public Filtro And(Filtro otro) {
		return new FiltroAnd(this, otro);
	}

	@Override
	public Filtro Or(Filtro otro) {
		return new FiltroOr(this, otro);
	}

	@Override
	public Filtro Not() {
		return new FiltroNot(this);
	}

}
