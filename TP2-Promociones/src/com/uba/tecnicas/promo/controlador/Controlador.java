package com.uba.tecnicas.promo.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import com.uba.tecnicas.promo.domain.Caja;
import com.uba.tecnicas.promo.domain.Producto;
import com.uba.tecnicas.promo.domain.exception.MarketException;

public class Controlador {

	private Caja caja;
	
	public Controlador(Caja caja) {
		this.caja = caja;
	}

	private class botonIniciarVenta implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			try {
				caja.iniciarVenta();
			} catch (MarketException e) {
				System.out.println("\n" + e.getMessage());
			}	
		}
	}
	
	public ActionListener getListenerBotonIniciarVenta() {		
		return new botonIniciarVenta();
	}
	
	private class botonAbrirCaja implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			caja.abrir(Calendar.getInstance());	
		}
	}
	
	public ActionListener getListenerBotonAbrirCaja() {		
		return new botonAbrirCaja();
	}
	
	private class botonCerrarCaja implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			caja.cerrar();
		}
	}
	
	public ActionListener getListenerBotonCerrarCaja() {		
		return new botonCerrarCaja();
	}
	
	public void agregarProducto(Producto producto, int cantidad) {
		caja.agregarProducto(producto, cantidad);
	}
}
