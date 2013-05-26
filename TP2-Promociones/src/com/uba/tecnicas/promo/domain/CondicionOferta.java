package com.uba.tecnicas.promo.domain;

import java.util.List;

public interface CondicionOferta {
	public List<Item> getAplicantes(Venta venta);
	
	public boolean seCumple(Venta venta, List<Item> aplicantes);
}
