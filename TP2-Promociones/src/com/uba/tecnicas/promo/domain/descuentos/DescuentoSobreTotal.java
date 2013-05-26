package com.uba.tecnicas.promo.domain.descuentos;

import java.util.List;

import com.uba.tecnicas.promo.domain.Descuento;
import com.uba.tecnicas.promo.domain.DescuentoOferta;
import com.uba.tecnicas.promo.domain.Item;
import com.uba.tecnicas.promo.domain.Venta;

public class DescuentoSobreTotal implements DescuentoOferta {
	private double porcentaje;
	
	public DescuentoSobreTotal(double porcentaje) {
		this.porcentaje = porcentaje;
	}
	
	@Override
	public Descuento crearDescuento(String nombre, List<Item> aplicantes) {
		double total = 0;
		for (Item aplicante : aplicantes) {
			total += aplicante.getImporte();
		}
		return new Descuento(nombre, total*porcentaje);
	}

	@Override
	public void aplicarDescuento(String nombre, Venta venta,
			List<Item> aplicantes) {
		venta.agregarDescuento(new Descuento(nombre, porcentaje*venta.getTotal()));
	}
}
