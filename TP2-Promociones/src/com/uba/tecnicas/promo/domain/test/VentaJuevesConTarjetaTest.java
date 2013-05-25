package com.uba.tecnicas.promo.domain.test;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import com.uba.tecnicas.promo.domain.Caja;
import com.uba.tecnicas.promo.domain.DescuentoOferta;
import com.uba.tecnicas.promo.domain.Item;
import com.uba.tecnicas.promo.domain.Oferta;
import com.uba.tecnicas.promo.domain.Producto;
import com.uba.tecnicas.promo.domain.condiciones.CondicionCompuesta;
import com.uba.tecnicas.promo.domain.condiciones.CondicionItem;
import com.uba.tecnicas.promo.domain.descuentos.DescuentoSobreProducto;

public class VentaJuevesConTarjetaTest {
	private Calendar jueves=null;
	private Caja caja=null;
	private Producto coca = null;
	@Before
	public void setUp() throws Exception {
		jueves = Calendar.getInstance();
		jueves.set(2013, 4, 30);
		caja = new Caja();
		coca = new Producto("CocaCola", 1, "Bebida");
	}

	@Test
	public void testVendeDosPagaUna() {
		int cantidad = 2;
		caja.abrir(jueves.getTime());
		caja.inicarVenta();
		caja.agregarProducto(coca,cantidad);
		
		CondicionCompuesta condCompuesta = new CondicionCompuesta();
		condCompuesta.agregar(new CondicionItem(new Item(coca, 2)));
		DescuentoOferta descuento = new DescuentoSobreProducto(coca, 1);
		Oferta oferta = new Oferta("Coca 2x1", condCompuesta, descuento, true);
		caja.finalizarVenta(oferta);
		caja.cerrar();
		assertEquals(1, caja.getVentaTotal(),0);
		assertEquals(2, caja.getCantidadProductos());
	}
	
	

}
