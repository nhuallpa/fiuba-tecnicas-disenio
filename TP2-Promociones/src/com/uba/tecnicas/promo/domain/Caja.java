package com.uba.tecnicas.promo.domain;

import java.util.Date;

import com.uba.tecnicas.promo.domain.condiciones.CondicionCompuesta;
import com.uba.tecnicas.promo.domain.condiciones.CondicionItem;

public class Caja {
	Venta ventaActual = null;
	private Date fechaApertura;
	
	public void abrir(Date fechaApertura) {
		this.fechaApertura = fechaApertura;
	}

	public void inicarVenta() {
		ventaActual = new VentaCaja();
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
