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
	
	@Before
	public void setUp() throws Exception {
		venta = new VentaCaja();
	}

	@Test
	public void testDescuentoConUnaCocaUnaSpriteGratis() {
		Producto pepsi = new Producto("Pepsi", 12, "Bebidas");
		Producto sprite = new Producto("Sprite", 12, "Bebidas");
		
		venta.agregarItem(pepsi, 1);
		venta.agregarItem(sprite, 1);
		
		CondicionCompuesta condCompuesta = new CondicionCompuesta();
		condCompuesta.agregar(new CondicionItem(new Item(pepsi, 1)));
		condCompuesta.agregar(new CondicionItem(new Item(sprite, 1)));
		
		DescuentoOferta descuento = new DescuentoSobreProducto(sprite, 1);
		
		Oferta oferta = new Oferta("Sprite gratis con una coca", condCompuesta, descuento, true);
		oferta.aplicar(venta);
		assertEquals(12.0, venta.getTotal());
	}

	@Test
	public void testContemplacionDeItemsConDescuentosAplicados() {
		Producto pepsi = new Producto("Pepsi", 12, "Bebidas");
		Producto sprite = new Producto("Sprite", 12, "Bebidas");
		
		venta.agregarItem(pepsi, 2);
		venta.agregarItem(sprite, 1);
		
		CondicionCompuesta condCompuesta = new CondicionCompuesta();
		condCompuesta.agregar(new CondicionItem(new Item(pepsi, 1)));
		condCompuesta.agregar(new CondicionItem(new Item(sprite, 1)));
		
		DescuentoOferta descuento = new DescuentoSobreProducto(sprite, 1);
		
		Oferta oferta = new Oferta("Sprite gratis con una coca", condCompuesta, descuento, true);
		oferta.aplicar(venta);
		assertEquals(24.0, venta.getTotal());
	}

	@Test
	public void testDescuentoPorRubro() {
		Producto pepsi = new Producto("Pepsi", 12, "Bebidas");
		Producto sprite = new Producto("Sprite", 12, "Bebidas");
		
		venta.agregarItem(pepsi, 1);
		venta.agregarItem(sprite, 1);
		
		CondicionOferta condicion = new CondicionRubro("Bebidas");
		DescuentoOferta descuento = new DescuentoGeneral(0.1);
		
		Oferta oferta = new Oferta("Descuento a las bebidas", condicion, descuento, false);
		oferta.aplicar(venta);
		assertEquals(21.6, venta.getTotal());
	}
}
