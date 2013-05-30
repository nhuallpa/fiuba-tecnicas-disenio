package com.uba.tecnicas.promo.domain.descuentos;

import java.util.List;

import com.uba.tecnicas.promo.domain.DescuentoGeneral;
import com.uba.tecnicas.promo.domain.DescuentoOferta;
import com.uba.tecnicas.promo.domain.Item;
import com.uba.tecnicas.promo.domain.Venta;

public class DescuentoSobreTotal implements DescuentoOferta {
	private double porcentaje;
	
	public DescuentoSobreTotal(double porcentaje) {
		this.porcentaje = porcentaje;
	}

	@Override
	public void aplicarDescuento(String nombre, Venta venta,
			List<Item> aplicantes) {
		venta.agregarDescuentoGeneral(new DescuentoGeneral(nombre, porcentaje*venta.getTotal()));
	}
}
