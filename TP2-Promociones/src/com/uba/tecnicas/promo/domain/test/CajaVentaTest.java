package com.uba.tecnicas.promo.domain.test;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import com.uba.tecnicas.promo.domain.Caja;
import com.uba.tecnicas.promo.domain.Producto;
import com.uba.tecnicas.promo.domain.repositories.ProductoRepository;

public class CajaVentaTest {
	private Caja caja;
	ProductoRepository prodRespository;
	@Before
	public void setUp() throws Exception {
		caja = new Caja();
		caja.abrir(Calendar.getInstance());
		prodRespository = ProductoRepository.getInstance();
	}	

	@Test
	public void testVentaEfectivo() {
		Producto coca = new Producto("Coca", 1, "Bebidas");
		caja.agregarProducto(coca, 1);
	}

}
