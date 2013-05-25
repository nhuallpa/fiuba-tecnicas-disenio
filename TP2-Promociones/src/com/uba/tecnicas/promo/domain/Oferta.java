package com.uba.tecnicas.promo.domain;

import java.util.List;

public class Oferta {
	private String nombre;
	private DescuentoOferta descuento;
	private CondicionOferta condicion;
	
	public Oferta(String nombre, CondicionOferta condicion, DescuentoOferta descuento) {
		this.nombre = nombre;
		this.condicion = condicion;
		this.descuento = descuento;
	}
	
	public void aplicar(Venta venta) {
		List<Item> aplicantes = condicion.getAplicantes(venta);
		while (aplicantes.size() > 0) {
			venta.agregarDescuento(descuento.crearDescuento(nombre, aplicantes));
			aplicantes = condicion.getAplicantes(venta);
		}
	}
}
