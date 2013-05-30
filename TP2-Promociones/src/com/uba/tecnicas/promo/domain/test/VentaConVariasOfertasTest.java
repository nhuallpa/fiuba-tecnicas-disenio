package com.uba.tecnicas.promo.domain.test;

import java.util.Calendar;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.uba.tecnicas.promo.domain.CondicionOferta;
import com.uba.tecnicas.promo.domain.DescuentoOferta;
import com.uba.tecnicas.promo.domain.FormaPago;
import com.uba.tecnicas.promo.domain.ItemComprado;
import com.uba.tecnicas.promo.domain.Oferta;
import com.uba.tecnicas.promo.domain.Producto;
import com.uba.tecnicas.promo.domain.Venta;
import com.uba.tecnicas.promo.domain.VentaCaja;
import com.uba.tecnicas.promo.domain.condiciones.CondicionDia;
import com.uba.tecnicas.promo.domain.condiciones.CondicionFormaPago;
import com.uba.tecnicas.promo.domain.condiciones.CondicionItem;
import com.uba.tecnicas.promo.domain.descuentos.DescuentoSobreProducto;
import com.uba.tecnicas.promo.domain.descuentos.DescuentoSobreTotal;

public class VentaConVariasOfertasTest extends TestCase{
	private Producto coca;
	private Producto cepillo;
	private Producto maceta;
	private Oferta ofertaDosPorUno;
	private Oferta ofertaJueves;
	private Calendar unJueves;
	private Calendar unViernes;
	
	@Before
	public void setUp() throws Exception {
		unJueves = Calendar.getInstance();
		unJueves.set(2013, 4, 30);
		unViernes = Calendar.getInstance();
		unViernes.set(2013, 4, 31);
		
		coca = new Producto("CocaCola", 1, "Bebida");
		cepillo = new Producto("Cepillo", 3, "Perfumeria");
		maceta = new Producto("Maceta", 10, "Jardin");
		
		CondicionOferta condicionDosPorUno = new CondicionItem(new ItemComprado(coca, 2)); 
		CondicionOferta condicionDiaYPago = CondicionDia.Jueves(new CondicionFormaPago(FormaPago.CREDITO));;
		DescuentoOferta descuento = new DescuentoSobreTotal(0.1);
		DescuentoOferta descuentoCoca = new DescuentoSobreProducto(coca, 1);
		ofertaJueves = new Oferta("10% Con Credito", condicionDiaYPago, descuento, false);
		ofertaDosPorUno = new Oferta("2x1 Coca", condicionDosPorUno, descuentoCoca, false); 
	}

	@Test
	public void testJuevesDecuentoConCreditoY2x1Coca() {
		Venta venta = new VentaCaja(unJueves);
		venta.agregarItem(coca, 2);
		venta.agregarItem(cepillo, 1);
		venta.agregarItem(maceta, 1);
		venta.setFormaPago(FormaPago.CREDITO);
		ofertaDosPorUno.aplicar(venta);
		ofertaJueves.aplicar(venta);
		assertEquals(12.6, venta.getTotal());
		assertEquals(4, venta.getCantidadProductosVendidos());
	}

	@Test
	public void testViernesSinDecuentoConCreditoYDecuento2x1Coca() {
		Venta venta = new VentaCaja(unViernes);
		venta.agregarItem(coca, 2);
		venta.agregarItem(cepillo, 1);
		venta.agregarItem(maceta, 1);
		venta.setFormaPago(FormaPago.CREDITO);
		ofertaDosPorUno.aplicar(venta);
		ofertaJueves.aplicar(venta);
		assertEquals(14.0, venta.getTotal());
		assertEquals(4, venta.getCantidadProductosVendidos());
	}
}
