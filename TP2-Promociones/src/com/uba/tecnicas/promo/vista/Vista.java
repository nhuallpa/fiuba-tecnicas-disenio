package com.uba.tecnicas.promo.vista;


import com.uba.tecnicas.promo.controlador.Controlador;
import com.uba.tecnicas.promo.domain.Caja;
import javax.swing.JFrame;


public class Vista {
	private JFrame frame;	
	private Panel panel;
	
	public Vista(Caja caja, Controlador controlador) {
		frame= new JFrame();		
		frame.setBounds(100, 100, 640, 450);
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		panel = new Panel(caja, controlador);
		
		frame.add(panel);
		frame.setVisible(true);
	}
}