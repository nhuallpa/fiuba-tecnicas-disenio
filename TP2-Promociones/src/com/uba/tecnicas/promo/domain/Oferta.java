package com.uba.tecnicas.promo.domain;

import java.util.List;

public class Oferta {
	private String nombre;
	private DescuentoOferta descuento;
	private CondicionOferta condicion;
	private boolean repetir;
	
	public Oferta(String nombre, CondicionOferta condicion, DescuentoOferta descuento, boolean repetir) {
		this.nombre = nombre;
		this.condicion = condicion;
		this.descuento = descuento;
		this.repetir = repetir;
	}
	
	public void aplicar(Venta venta) {
		List<Item> aplicantes = condicion.getAplicantes(venta);
		while (aplicantes.size() > 0) {
			venta.agregarDescuento(descuento.crearDescuento(nombre, aplicantes));
			if (!repetir)
				return;
			aplicantes = condicion.getAplicantes(venta);
		}
	}
}
