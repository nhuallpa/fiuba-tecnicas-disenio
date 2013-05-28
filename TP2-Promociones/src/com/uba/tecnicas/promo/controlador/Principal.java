package com.uba.tecnicas.promo.controlador;

import com.uba.tecnicas.promo.domain.Caja;
import com.uba.tecnicas.promo.vista.Vista;



public class Principal {
	
	public static void main(String[] args) {		
		Caja caja = new Caja();		
		Vista vista = new Vista(caja);
	}
	
}
