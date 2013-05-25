package com.uba.tecnicas.promo.domain.test;

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
import com.uba.tecnicas.promo.domain.condiciones.CondicionCompuesta;
import com.uba.tecnicas.promo.domain.condiciones.CondicionItem;
import com.uba.tecnicas.promo.domain.condiciones.CondicionRubro;
import com.uba.tecnicas.promo.domain.descuentos.DescuentoGeneral;
import com.uba.tecnicas.promo.domain.descuentos.DescuentoSobreProducto;

public class DescuentosTest extends TestCase {
	private Venta venta;
	Producto pepsi;
	Producto sprite;
	
	@Before
	public void setUp() throws Exception {
		venta = new VentaCaja();
		pepsi = new Producto("Pepsi", 12, "Bebidas");
		sprite = new Producto("Sprite", 12, "Bebidas");
	}

	@Test
	public void testDescuentoConUnaCocaUnaSpriteGratis() {
		venta.agregarItem(pepsi, 1);
		venta.agregarItem(sprite, 1);
		String nombreOferta = "Sprite gratis con una coca";
		CondicionCompuesta condCompuesta = new CondicionCompuesta();
		condCompuesta.agregar(new CondicionItem(new Item(pepsi, 1)));
		condCompuesta.agregar(new CondicionItem(new Item(sprite, 1)));
		
		DescuentoOferta descuento = new DescuentoSobreProducto(sprite, 1);
		
		Oferta oferta = new Oferta(nombreOferta, condCompuesta, descuento, true);
		oferta.aplicar(venta);
		assertEquals(12.0, venta.getTotal());
	}

	@Test
	public void testContemplacionDeItemsConDescuentosAplicados() {
		venta.agregarItem(pepsi, 2);
		venta.agregarItem(sprite, 1);
		String nombreOferta = "Sprite gratis con una coca";
		CondicionCompuesta condCompuesta = new CondicionCompuesta();
		condCompuesta.agregar(new CondicionItem(new Item(pepsi, 1)));
		condCompuesta.agregar(new CondicionItem(new Item(sprite, 1)));
		
		DescuentoOferta descuento = new DescuentoSobreProducto(sprite, 1);
		
		Oferta oferta = new Oferta(nombreOferta, condCompuesta, descuento, true);
		oferta.aplicar(venta);
		assertEquals(24.0, venta.getTotal());
	}
	@Test
	public void testDescuentoPorRubro() {
		venta.agregarItem(pepsi, 1);
		venta.agregarItem(sprite, 1);
		String nombreOferta = "Descuento a las bebidas";
		CondicionOferta condicion = new CondicionRubro("Bebidas");
		DescuentoOferta descuento = new DescuentoGeneral(0.1);
		
		Oferta oferta = new Oferta(nombreOferta, condicion, descuento, false);
		oferta.aplicar(venta);
		assertEquals(21.6, venta.getTotal());
	}
}
