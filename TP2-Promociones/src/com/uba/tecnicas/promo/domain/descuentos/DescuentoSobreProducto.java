package com.uba.tecnicas.promo.domain.descuentos;

import java.util.List;

import com.uba.tecnicas.promo.domain.DescuentoOferta;
import com.uba.tecnicas.promo.domain.Item;
import com.uba.tecnicas.promo.domain.ItemDescuento;
import com.uba.tecnicas.promo.domain.Producto;
import com.uba.tecnicas.promo.domain.Venta;

public class DescuentoSobreProducto implements DescuentoOferta {
	private Producto producto;
	private double porcentaje;
	
	public DescuentoSobreProducto(Producto producto, double porcentaje) {
		this.producto = producto;
		this.porcentaje = porcentaje;
	}

	@Override
	public void aplicarDescuento(String nombre, Venta venta,
			List<Item> aplicantes) {
		venta.agregarDescuentoParticular(new ItemDescuento(nombre, producto, porcentaje, aplicantes));
	}
}
