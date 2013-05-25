package com.uba.tecnicas.promo.domain.descuentos;

import java.util.List;

import com.uba.tecnicas.promo.domain.Descuento;
import com.uba.tecnicas.promo.domain.DescuentoOferta;
import com.uba.tecnicas.promo.domain.Item;

public class DescuentoGeneral implements DescuentoOferta {
	private double porcentaje;
	
	public DescuentoGeneral(double porcentaje) {
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
}
