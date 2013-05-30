package com.uba.tecnicas.promo.domain.condiciones;

import java.util.List;

import com.uba.tecnicas.promo.domain.CondicionOferta;
import com.uba.tecnicas.promo.domain.FormaPago;
import com.uba.tecnicas.promo.domain.Item;
import com.uba.tecnicas.promo.domain.Venta;

public class CondicionFormaPago implements CondicionOferta {
	private FormaPago formaPago;
	
	public CondicionFormaPago(FormaPago formaPago) {
		this.formaPago = formaPago; 
	}

	@Override
	public boolean seCumple(Venta venta, List<Item> aplicantes) {
		return venta.esFormaPago(formaPago);
	}
}
