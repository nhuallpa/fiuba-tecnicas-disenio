package com.uba.tecnicas.promo.domain.condiciones;

import java.util.List;

import com.uba.tecnicas.promo.domain.CondicionOferta;
import com.uba.tecnicas.promo.domain.Item;
import com.uba.tecnicas.promo.domain.TipoCliente;
import com.uba.tecnicas.promo.domain.Venta;

public class CondicionTipoCliente implements CondicionOferta {

	private TipoCliente tipoCliente;
	
	public CondicionTipoCliente(TipoCliente tipoCliente) {
		this.tipoCliente=tipoCliente;
	}

	@Override
	public boolean seCumple(Venta venta, List<Item> aplicantes) {
		return this.tipoCliente.equals(venta.getTipoCliente());
	}

}
