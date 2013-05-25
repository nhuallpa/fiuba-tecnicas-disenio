package com.uba.tecnicas.promo.domain.condiciones;

import java.util.List;

import com.uba.tecnicas.promo.domain.CondicionOferta;
import com.uba.tecnicas.promo.domain.Item;
import com.uba.tecnicas.promo.domain.Venta;
import com.uba.tecnicas.promo.domain.filtros.FiltroRubro;

public class CondicionRubro implements CondicionOferta {
	private String rubro;
	
	public CondicionRubro(String rubro) {
		this.rubro = rubro;
	}
	
	@Override
	public List<Item> getAplicantes(Venta venta) {
		return venta.getItems(new FiltroRubro(rubro));
	}

	@Override
	public boolean aplica(Venta venta) {
		// TODO Se puede chequear si hay porductos de este Rubro
		return true;
	}
}
