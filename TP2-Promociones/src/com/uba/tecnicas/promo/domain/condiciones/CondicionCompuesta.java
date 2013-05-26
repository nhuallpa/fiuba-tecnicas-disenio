package com.uba.tecnicas.promo.domain.condiciones;

import java.util.ArrayList;
import java.util.List;

import com.uba.tecnicas.promo.domain.CondicionOferta;
import com.uba.tecnicas.promo.domain.Item;
import com.uba.tecnicas.promo.domain.Venta;

public class CondicionCompuesta implements CondicionOferta {
	private List<CondicionOferta> condiciones;
	
	public CondicionCompuesta() {
		condiciones = new ArrayList<CondicionOferta>();
	}
	
	public void agregar(CondicionOferta condicion) {
		condiciones.add(condicion);
	}

	@Override
	public boolean seCumple(Venta venta, List<Item> aplicantes) {
		for (CondicionOferta condicion : condiciones) {
			if (!condicion.seCumple(venta, aplicantes)) {
				aplicantes.clear();
				return false;
			}
		}
		return true;
	}
}
