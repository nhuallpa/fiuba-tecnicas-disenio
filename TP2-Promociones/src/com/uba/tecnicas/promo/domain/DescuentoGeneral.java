package com.uba.tecnicas.promo.domain;

public class DescuentoGeneral implements Descuento{
	private String nombre;
	private double monto;
	
	public DescuentoGeneral(String nombre, double monto) {
		this.nombre = nombre;
		this.monto = monto;
	}
	
	@Override
	public String getNombre() {
		return nombre;
	}

	@Override
	public double getImporte() {
		return -monto;
	}
}
