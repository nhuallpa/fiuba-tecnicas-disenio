package com.uba.tecnicas.promo.vista;

import com.uba.tecnicas.promo.controlador.Controlador;
import com.uba.tecnicas.promo.domain.Caja;
import javax.swing.JFrame;

public class Vista {
	private Caja caja;	
	private Controlador controlador;
	
	public Vista(Caja caja, Controlador controlador) {
		this.caja = caja;
		this.controlador = controlador;
	}
	
	public void ejecutar() {
		JFrame frame= new JFrame();		
		frame.setBounds(100, 100, 700, 455);
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		Panel panel = new Panel(caja, controlador);
		
		frame.add(panel);
		frame.setVisible(true);
	}
}