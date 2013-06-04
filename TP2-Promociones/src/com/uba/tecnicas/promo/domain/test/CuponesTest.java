package com.uba.tecnicas.promo.domain.test;

import java.util.Calendar;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.uba.tecnicas.promo.domain.CondicionOferta;
import com.uba.tecnicas.promo.domain.Cupon;
import com.uba.tecnicas.promo.domain.ItemComprado;
import com.uba.tecnicas.promo.domain.Oferta;
import com.uba.tecnicas.promo.domain.Producto;
import com.uba.tecnicas.promo.domain.Venta;
import com.uba.tecnicas.promo.domain.VentaCaja;
import com.uba.tecnicas.promo.domain.condiciones.CondicionBucle;
import com.uba.tecnicas.promo.domain.condiciones.CondicionSobreUnItem;
import com.uba.tecnicas.promo.domain.descuentos.DescuentoGeneraCupon;

public class CuponesTest extends TestCase {
	private Venta venta;
	private Producto coca;
	
	public CuponesTest() {
		coca = new Producto("Coca", 1, "Bebidas");
	}
	
	@Before
	public void setUp() throws Exception {
		venta = new VentaCaja(Calendar.getInstance());
	}
	
	@Test
	public void test_generacionCuponDescuento() {
		CondicionOferta cond = new CondicionBucle(new CondicionSobreUnItem(new ItemComprado(coca, 2)));
		Oferta oferta = new Oferta("2x1 en coca", cond, new DescuentoGeneraCupon(0.5, 0.2), false);
		venta.agregarItem(coca, 10);
		venta.agregarCupon(new Cupon("cupon 2x1 en coca", 10, 0.2));
		oferta.aplicar(venta);
		assertEquals(8.0, venta.getTotal());
		assertEquals(5.0, venta.getCupones().get(0).getImporte(1000));
		assertEquals(2.0, venta.getCupones().get(0).getImporte(10));
	}
}
