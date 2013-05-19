package com.uba.tecnicas.promo.domain.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.uba.tecnicas.promo.domain.Item;
import com.uba.tecnicas.promo.domain.ItemComprado;
import com.uba.tecnicas.promo.domain.Producto;
import com.uba.tecnicas.promo.domain.Venta;
import com.uba.tecnicas.promo.domain.VentaCaja;

public class VentasTest {
	private Venta venta;
	@Before
	public void setUp() throws Exception {
		venta=new VentaCaja();
	}

	@Test
	public void testVentaUnCaramelo() {
		double total = 1.0;
		Producto prod = new Producto("Caramelo",1.0);
		venta.agregar(prod,1);
		assertEquals(total, venta.getTotal(),0);
	}
	
	@Test
	public void testVentaUnDetegente() {
		double total = 6.0;
		Producto prod = new Producto("Deterente",6.0);
		venta.agregar(prod,1);
		assertEquals(total, venta.getTotal(),0);
	}

}
