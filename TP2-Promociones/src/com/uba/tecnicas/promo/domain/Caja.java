package com.uba.tecnicas.promo.domain;

import java.util.Calendar;

import com.uba.tecnicas.promo.domain.exception.CajaCerradaException;
import com.uba.tecnicas.promo.domain.exception.MarketException;

public class Caja {
	private boolean estaAbierta;
	private Venta ventaActual;
	private Calendar fechaApertura;
	
	public Caja() {
		this.estaAbierta = false;
	}
	
	public void abrir(Calendar fechaApertura) {
		this.fechaApertura = fechaApertura;
		this.estaAbierta = true;
	}

	public void iniciarVenta() throws MarketException  {
		if (!estaAbierta) throw new CajaCerradaException("Falta abrir caja");
		ventaActual = new VentaCaja(fechaApertura);
	}

	public void agregarProducto(Producto producto, int cantidad) {
		ventaActual.agregarItem(producto, cantidad);
	}

	public void finalizarVenta(Oferta oferta) {
		oferta.aplicar(ventaActual);
	}

	public void cerrar() {
		this.estaAbierta = false;
	}

	
	public double getVentaTotal() {
		return ventaActual.getTotal();
	}

	public double getCantidadProductos() {
		return ventaActual.getCantidadProductosVendidos();
	}

	public boolean estaAbierta() {
		return estaAbierta;
	}

}
