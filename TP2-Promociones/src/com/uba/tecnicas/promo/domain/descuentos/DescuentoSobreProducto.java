package com.uba.tecnicas.promo.domain.descuentos;

import java.util.List;

import com.uba.tecnicas.promo.domain.Descuento;
import com.uba.tecnicas.promo.domain.DescuentoOferta;
import com.uba.tecnicas.promo.domain.Item;
import com.uba.tecnicas.promo.domain.Producto;

public class DescuentoSobreProducto implements DescuentoOferta {
	private Producto producto;
	private double porcentaje;
	
	public DescuentoSobreProducto(Producto producto, double porcentaje) {
		this.producto = producto;
		this.porcentaje = porcentaje;
	}
	
	@Override
	public Descuento crearDescuento(String nombre, List<Item> aplicantes) {
		return new Descuento(nombre, producto.getPrecio()*porcentaje, aplicantes);
	}
}
