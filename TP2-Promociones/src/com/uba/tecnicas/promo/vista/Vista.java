package com.uba.tecnicas.promo.vista;

import javax.swing.JFrame;

import com.uba.tecnicas.promo.domain.Caja;

public class Vista {
	private JFrame frame;	
	private Panel panel;
	
	public Vista(Caja caja) {
		frame= new JFrame();		
		frame.setBounds(100, 100, 600, 411);
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		panel = new Panel(caja);
		
		frame.add(panel);
		frame.setVisible(true);
	}
}