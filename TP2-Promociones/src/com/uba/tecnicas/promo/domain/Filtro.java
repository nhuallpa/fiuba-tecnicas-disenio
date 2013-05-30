package com.uba.tecnicas.promo.domain;

public interface Filtro {
	public abstract boolean seCumple(Item entrada);
	public Filtro And(Filtro otro);
	public Filtro Or(Filtro otro);
	public Filtro Not();
}
