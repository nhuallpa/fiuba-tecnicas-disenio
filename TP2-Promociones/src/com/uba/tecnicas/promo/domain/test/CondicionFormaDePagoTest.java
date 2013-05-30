package com.uba.tecnicas.promo.domain.test;

import java.util.Calendar;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.uba.tecnicas.promo.domain.CondicionOferta;
import com.uba.tecnicas.promo.domain.DescuentoOferta;
import com.uba.tecnicas.promo.domain.FormaPago;
import com.uba.tecnicas.promo.domain.Oferta;
import com.uba.tecnicas.promo.domain.Producto;
import com.uba.tecnicas.promo.domain.Venta;
import com.uba.tecnicas.promo.domain.VentaCaja;
import com.uba.tecnicas.promo.domain.condiciones.CondicionFormaPago;
import com.uba.tecnicas.promo.domain.descuentos.DescuentoSobreTotal;

public class CondicionFormaDePagoTest extends TestCase {
	private Producto coca = new Producto("CocaCola", 1, "Bebida");
	private Producto cepillo = new Producto("Cepillo", 3, "Perfumeria");
	private Oferta oferta;
	
	@Before
	public void setUp() throws Exception {
		CondicionOferta condicionOferta = new CondicionFormaPago(FormaPago.CREDITO); 
		DescuentoOferta descuento = new DescuentoSobreTotal(0.1);
		oferta = new Oferta("10% desc con Debito", condicionOferta, descuento, false);
	}
	

	@Test
	public void testPagandoConTarjetaCreditoDiezPorcientoDescuento() {
		Venta venta = new VentaCaja(Calendar.getInstance());
		venta.agregarItem(coca, 2);
		venta.agregarItem(cepillo, 1);
		venta.setFormaPago(FormaPago.CREDITO);
		oferta.aplicar(venta);
		assertEquals(4.5, venta.getTotal());
		assertEquals(3, venta.getCantidadProductosVendidos());
	}

	@Test
	public void testPagandoSinTarjCreditoNoTienDescuento() {
		Venta venta = new VentaCaja(Calendar.getInstance());
		venta.agregarItem(coca, 2);
		venta.agregarItem(cepillo, 1);
		venta.setFormaPago(FormaPago.EFECTIVO);
		oferta.aplicar(venta);
		assertEquals(5.0, venta.getTotal());
		assertEquals(3, venta.getCantidadProductosVendidos());
	}
}
