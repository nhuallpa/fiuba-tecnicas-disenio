package com.uba.tecnicas.promo.domain;

import java.util.Calendar;

public class Caja {
	private Venta ventaActual;
	private Calendar fechaApertura;
	
	public void abrir(Calendar fechaApertura) {
		this.fechaApertura = fechaApertura;
	}

	public void inicarVenta() {
		ventaActual = new VentaCaja(fechaApertura);
	}

	public void agregarProducto(Producto producto, int cantidad) {
		ventaActual.agregarItem(producto, cantidad);
	}

	public void finalizarVenta(Oferta oferta) {
		oferta.aplicar(ventaActual);
	}

	public void cerrar() {
		// TODO Auto-generated method stub
	}

	public double getVentaTotal() {
		return ventaActual.getTotal();
	}

	public int getCantidadProductos() {
		return ventaActual.getCantidadProductosVendidos();
	}

}
