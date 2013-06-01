package com.uba.tecnicas.promo.domain.condiciones;

import java.util.List;

import com.uba.tecnicas.promo.domain.CondicionOferta;
import com.uba.tecnicas.promo.domain.Item;
import com.uba.tecnicas.promo.domain.Venta;

public class CondicionBucle implements CondicionOferta {
	private CondicionOferta condicion;
	
	public CondicionBucle(CondicionOferta condicion) {
		this.condicion = condicion;
	}
	
	@Override
	public boolean seCumple(Venta venta, List<Item> aplicantes) {
		boolean seCumplio = false;
		while (condicion.seCumple(venta, aplicantes)) {
			seCumplio = true;
		}
		return seCumplio;
	}
}
