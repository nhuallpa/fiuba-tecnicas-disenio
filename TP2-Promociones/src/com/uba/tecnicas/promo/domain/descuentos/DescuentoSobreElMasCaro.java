package com.uba.tecnicas.promo.domain.descuentos;

import java.util.List;

import com.uba.tecnicas.promo.domain.DescuentoOferta;
import com.uba.tecnicas.promo.domain.Item;
import com.uba.tecnicas.promo.domain.ItemDescuento;
import com.uba.tecnicas.promo.domain.Venta;

public class DescuentoSobreElMasCaro implements DescuentoOferta {
	private double porcentaje;
	
	public DescuentoSobreElMasCaro(double porcentaje) {
		this.porcentaje = porcentaje;
	}
	
	@Override
	public void aplicarDescuento(String nombre, Venta venta,
			List<Item> aplicantes) {
		if (aplicantes.size() > 0) {
			Item masCaro = aplicantes.get(0);
			for (Item aplicante : aplicantes) {
				if (masCaro.getProducto().getPrecio() < aplicante.getProducto().getPrecio()) {
					masCaro = aplicante;
				}
			}
			venta.agregarDescuentoParticular(new ItemDescuento(nombre, masCaro.getProducto(), porcentaje, aplicantes));
		}
	}

}
