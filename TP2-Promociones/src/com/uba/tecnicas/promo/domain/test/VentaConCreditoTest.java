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
import com.uba.tecnicas.promo.domain.condicion.CondicionFormaPago;
import com.uba.tecnicas.promo.domain.descuentos.DescuentoSobreTotal;

public class VentaConCreditoTest extends TestCase {
	private Producto coca;
	private Producto cepillo;
	private Oferta oferta;
	
	@Before
	public void setUp() throws Exception {
		double precioCoca = 1;
		double precioCepillo = 3;
		coca = new Producto("CocaCola", precioCoca, "Bebida");
		cepillo = new Producto("Cepillo", precioCepillo, "Perfumeria");
		CondicionOferta condicionOferta = new CondicionFormaPago(FormaPago.DEBITO); 
		DescuentoOferta descuento = new DescuentoSobreTotal(10);
		oferta = new Oferta("10 % desc con Debito", condicionOferta, descuento, true);
	}
	

	@Test
	public void testPagandoConTarjetaDebitoDiezPorcientoDescuento() {
		Venta venta = new VentaCaja(Calendar.getInstance());
		int cantCocas = 2;
		int cantCepillos = 1;
		venta.agregarItem(coca, cantCocas);
		venta.agregarItem(cepillo, cantCepillos);
		oferta.aplicar(venta);
		assertEquals(4.5, venta.getTotal());
		assertEquals(4.0, venta.getCantidadProductosVendidos());
	}

}
