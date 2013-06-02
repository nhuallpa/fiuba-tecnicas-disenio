package com.uba.tecnicas.promo.domain.test;

import static org.junit.Assert.*;

import java.util.Calendar;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.uba.tecnicas.promo.domain.CondicionOferta;
import com.uba.tecnicas.promo.domain.DescuentoOferta;
import com.uba.tecnicas.promo.domain.FormaPago;
import com.uba.tecnicas.promo.domain.Oferta;
import com.uba.tecnicas.promo.domain.Producto;
import com.uba.tecnicas.promo.domain.TipoCliente;
import com.uba.tecnicas.promo.domain.Venta;
import com.uba.tecnicas.promo.domain.VentaCaja;
import com.uba.tecnicas.promo.domain.condiciones.CondicionFormaPago;
import com.uba.tecnicas.promo.domain.condiciones.CondicionTipoCliente;
import com.uba.tecnicas.promo.domain.descuentos.DescuentoSobreTotal;

public class VentaJubiladosDescuentosTest extends TestCase {

	private Producto maceta = new Producto("Maceta", 10, "Jardin");
	private Producto lampara = new Producto("Lamparita", 15, "Hogar");
	private Oferta oferta;
	
	@Before
	public void setUp() throws Exception {
		CondicionOferta condicionOferta = new CondicionTipoCliente(TipoCliente.JUBILADO); 
		DescuentoOferta descuento = new DescuentoSobreTotal(0.1);
		oferta = new Oferta("10% Desc Jubilidado", condicionOferta, descuento, false);
	}
	

	@Test
	public void testJubiladoConDescuento() {
		Venta venta = new VentaCaja(Calendar.getInstance());
		venta.agregarItem(maceta, 1);
		venta.agregarItem(lampara, 2);
		venta.setTipoCliente(TipoCliente.JUBILADO);
		oferta.aplicar(venta);
		assertEquals(36.0, venta.getTotal());
		assertEquals(3, venta.getCantidadProductosVendidos());
	}

	
}
