package com.uba.tecnicas.promo.domain.test;

import java.util.Calendar;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.uba.tecnicas.promo.domain.CondicionOferta;
import com.uba.tecnicas.promo.domain.DescuentoOferta;
import com.uba.tecnicas.promo.domain.Item;
import com.uba.tecnicas.promo.domain.Oferta;
import com.uba.tecnicas.promo.domain.Producto;
import com.uba.tecnicas.promo.domain.Venta;
import com.uba.tecnicas.promo.domain.VentaCaja;
import com.uba.tecnicas.promo.domain.condiciones.CondicionDia;
import com.uba.tecnicas.promo.domain.condiciones.CondicionItem;
import com.uba.tecnicas.promo.domain.descuentos.DescuentoSobreProducto;

public class VentaConEfectivoTest extends TestCase {
	private Producto coca;
	private Producto cepillo;
	private Oferta oferta;
	
	@Before
	public void setUp() throws Exception {
		coca = new Producto("CocaCola", 1, "Bebida");
		cepillo = new Producto("Cepillo", 3, "Perfumeria");
		
		CondicionOferta condDecorada = generarOferta();
		DescuentoOferta descuento = new DescuentoSobreProducto(coca, 1);
		oferta = new Oferta("Coca 2x1", condDecorada, descuento, true);
	}
	
	private CondicionOferta generarOferta() {
		switch (Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
			case Calendar.MONDAY: return CondicionDia.Lunes(new CondicionItem(new Item(coca, 2)));
			case Calendar.TUESDAY: return CondicionDia.Martes(new CondicionItem(new Item(coca, 2)));
			case Calendar.WEDNESDAY: return CondicionDia.Miercoles(new CondicionItem(new Item(coca, 2)));
			case Calendar.THURSDAY: return CondicionDia.Jueves(new CondicionItem(new Item(coca, 2)));
			case Calendar.FRIDAY: return CondicionDia.Viernes(new CondicionItem(new Item(coca, 2)));
			case Calendar.SATURDAY: return CondicionDia.Sabado(new CondicionItem(new Item(coca, 2)));
			default: return CondicionDia.Domingo(new CondicionItem(new Item(coca, 2)));
		}
	}

	@Test
	public void testHoyVendeDosCocasPagaUna() {
		Venta venta = new VentaCaja(Calendar.getInstance());
		venta.agregarItem(coca, 2);
		venta.agregarItem(cepillo, 1);
		oferta.aplicar(venta);
		assertEquals(4.0, venta.getTotal());
		assertEquals(3.0, venta.getCantidadProductosVendidos());
	}

	@Test
	public void testMañanaVendeDosCocasPagaDosYUnCepillo() {
		Calendar mañana = Calendar.getInstance();
		mañana.add(Calendar.DAY_OF_MONTH, 1);
		Venta venta = new VentaCaja(mañana);
		venta.agregarItem(coca, 2);
		venta.agregarItem(cepillo, 1);
		oferta.aplicar(venta);
		assertEquals(5.0, venta.getTotal());
		assertEquals(3.0, venta.getCantidadProductosVendidos());
	}
}
