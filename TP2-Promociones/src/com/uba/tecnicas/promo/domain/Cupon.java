package com.uba.tecnicas.promo.domain;

public class Cupon {
	private String nombre;
	private double importe;
	private double porcentajeImporteMaximo;
	
	public Cupon(String nombre, double importe, double porcentajeImporteMaximo) {
		this.nombre = nombre;
		this.importe = importe;
		this.porcentajeImporteMaximo = porcentajeImporteMaximo;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public double getImporte(double total) {
		return Math.min(total*porcentajeImporteMaximo, importe);
	}
}
