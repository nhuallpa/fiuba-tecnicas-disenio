package com.uba.tecnicas.promo.domain;

import java.util.Calendar;
import java.util.Collection;
import java.util.Observable;

import com.uba.tecnicas.promo.domain.exception.CajaCerradaException;
import com.uba.tecnicas.promo.domain.exception.MarketException;

public class Caja extends Observable{
	private boolean estaAbierta;
	private Venta ventaActual;
	private Calendar fechaApertura;
	private Collection<Oferta> ofertas;
	private Sucursal sucursal;
	
	public Caja(Collection<Oferta> ofertas, Sucursal sucursal) {
		this.ofertas = ofertas;
		this.estaAbierta = false;
		this.sucursal = sucursal;
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

	public void finalizarVenta( FormaPago forma) {
		ventaActual.setFormaPago(forma);
		for (Oferta oferta : ofertas) {
			oferta.aplicar(ventaActual);			
		}		
		sucursal.agregarItem(ventaActual.getItemsComprados());
		actualizarObservadores();
	}

	public void cerrar() {
		this.estaAbierta = false;
	}

	
	public double getVentaTotal() {
		return ventaActual.getTotal();
	}

	public int getCantidadProductos() {
		return ventaActual.getCantidadProductosVendidos();
	}

	public boolean estaAbierta() {
		return estaAbierta;
	}
	
	public Venta getVenta() {
		return ventaActual;
	}
	
	public void actualizarObservadores() {
		setChanged();
		notifyObservers();
	}
}
