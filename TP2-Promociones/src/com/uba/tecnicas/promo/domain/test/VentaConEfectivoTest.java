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
import com.uba.tecnicas.promo.domain.condiciones.CondicionDia;
import com.uba.tecnicas.promo.domain.condiciones.CondicionItem;
import com.uba.tecnicas.promo.domain.descuentos.DescuentoSobreProducto;

public class VentaConEfectivoTest {
	private Calendar jueves=null;
	private Calendar viernes=null;
	private Caja caja=null;
	private Producto coca = null;
	private Producto cepillo = null;
	private Oferta oferta = null;
	@Before
	public void setUp() throws Exception {
		jueves = Calendar.getInstance();
		jueves.set(2013, 4, 30);
		viernes = Calendar.getInstance();
		viernes.set(2013, 4, 31);
		caja = new Caja();
		coca = new Producto("CocaCola", 1, "Bebida");
		cepillo = new Producto("Cepillo", 3, "Perfumeria");
		
		CondicionCompuesta condCompuesta = new CondicionCompuesta();
		condCompuesta.agregar(new CondicionItem(new Item(coca, 2)));
		condCompuesta.agregar(CondicionDia.JUEVES);
		DescuentoOferta descuento = new DescuentoSobreProducto(coca, 1);
		oferta = new Oferta("Coca 2x1", condCompuesta, descuento, true);
	}

	@Test
	public void testJuevesVendeDosCocasPagaUna() {
		int cantidad = 2;
		caja.abrir(jueves.getTime());
		caja.inicarVenta();
		caja.agregarProducto(coca,cantidad);
		caja.finalizarVenta(oferta);
		caja.cerrar();
		assertEquals(1, caja.getVentaTotal(),0);
		assertEquals(2, caja.getCantidadProductos());
	}

	@Test
	public void testJuevesVendeDosCocasPagaUnaYUnCepillo() {
		int cantidadCocas = 2;
		int cantidadCepillos = 1;
		caja.abrir(jueves.getTime());
		caja.inicarVenta();
		caja.agregarProducto(coca,cantidadCocas);
		caja.agregarProducto(cepillo,cantidadCepillos);
		caja.finalizarVenta(oferta);
		caja.cerrar();
		assertEquals(4, caja.getVentaTotal(),0);
		assertEquals(3, caja.getCantidadProductos());
	}
	
	@Test
	public void testViernesVendeDosCocasYUnCepillo() {
		int cantidadCocas = 2;
		int cantidadCepillos = 1;
		caja.abrir(viernes.getTime());
		caja.inicarVenta();
		caja.agregarProducto(coca,cantidadCocas);
		caja.agregarProducto(cepillo,cantidadCepillos);
		caja.finalizarVenta(oferta);
		caja.cerrar();
		assertEquals(5, caja.getVentaTotal(),0);
		assertEquals(3, caja.getCantidadProductos());
	}

}
