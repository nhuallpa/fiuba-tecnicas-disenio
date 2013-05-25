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
	public List<Item> getAplicantes(Venta venta) {
		List<Item> resultado = new ArrayList<Item>();
		List<Item> resultadoIndividual;
		for (CondicionOferta condicion : condiciones) {
			resultadoIndividual = condicion.getAplicantes(venta);
			if (resultadoIndividual.size() > 0)
				resultado.addAll(resultadoIndividual);
			else
				return resultadoIndividual;
		}
		return resultado;
	}
}
