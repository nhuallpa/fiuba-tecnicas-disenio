package com.uba.tecnicas.promo.domain.condicion;

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
	public List<Item> getAplicantes(Venta venta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean seCumple(Venta venta, List<Item> aplicantes) {
		// TODO Auto-generated method stub
		return venta.esFormaPago(formaPago);
	}

}
