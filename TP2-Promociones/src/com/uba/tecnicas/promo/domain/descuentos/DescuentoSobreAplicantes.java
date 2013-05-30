package com.uba.tecnicas.promo.domain.descuentos;

import java.util.List;

import com.uba.tecnicas.promo.domain.DescuentoGeneral;
import com.uba.tecnicas.promo.domain.DescuentoOferta;
import com.uba.tecnicas.promo.domain.Item;
import com.uba.tecnicas.promo.domain.Venta;

public class DescuentoSobreAplicantes implements DescuentoOferta {
	private double porcentaje;
	
	public DescuentoSobreAplicantes(double porcentaje) {
		this.porcentaje = porcentaje;
	}
	
	@Override
	public void aplicarDescuento(String nombre, Venta venta,
			List<Item> aplicantes) {
		double total = 0;
		for (Item aplicante : aplicantes) {
			total += aplicante.getImporte();
		}
		venta.agregarDescuentoGeneral(new DescuentoGeneral(nombre, total*porcentaje));
	}
}
