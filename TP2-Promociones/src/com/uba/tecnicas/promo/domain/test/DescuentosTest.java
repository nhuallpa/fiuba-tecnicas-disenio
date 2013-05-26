package com.uba.tecnicas.promo.domain.test;

import java.util.Calendar;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.uba.tecnicas.promo.domain.ItemComprado;
import com.uba.tecnicas.promo.domain.Oferta;
import com.uba.tecnicas.promo.domain.Producto;
import com.uba.tecnicas.promo.domain.Venta;
import com.uba.tecnicas.promo.domain.VentaCaja;
import com.uba.tecnicas.promo.domain.condiciones.CondicionCompuesta;
import com.uba.tecnicas.promo.domain.condiciones.CondicionItem;
import com.uba.tecnicas.promo.domain.condiciones.CondicionRubro;
import com.uba.tecnicas.promo.domain.descuentos.DescuentoSobreAplicantes;
import com.uba.tecnicas.promo.domain.descuentos.DescuentoSobreProducto;

public class DescuentosTest extends TestCase {
	private Venta venta;
	private Producto coca;
	private Producto sprite;
	
	private Oferta ofertaBebidas;
	private Oferta ofertaCocaSprite;
	
	public DescuentosTest() {
		coca = new Producto("Coca", 12, "Bebidas");
		sprite = new Producto("Sprite", 11, "Bebidas");

		ofertaBebidas = new Oferta("10% en bebidas",
				new CondicionRubro("Bebidas"),
				new DescuentoSobreAplicantes(0.1), false);

		CondicionCompuesta condCompuesta = new CondicionCompuesta();
		condCompuesta.agregar(new CondicionItem(new ItemComprado(coca, 1)));
		condCompuesta.agregar(new CondicionItem(new ItemComprado(sprite, 1)));
		
		ofertaCocaSprite = new Oferta("Sprite gratis con una coca",
				condCompuesta, new DescuentoSobreProducto(sprite, 1), true);
	}
	
	@Before
	public void setUp() throws Exception {
		venta = new VentaCaja(Calendar.getInstance());
	}

	@Test
	public void test_Cond_1Coca_Desc_1Sprite_Lleva_1Coca1Sprite() {
		venta.agregarItem(coca, 1);
		venta.agregarItem(sprite, 1);
		ofertaCocaSprite.aplicar(venta);
		assertEquals(12.0, venta.getTotal());
	}

	@Test
	public void test_Cond_1Coca_Desc_1Sprite_Lleva_2Cocas1Sprite() {
		venta.agregarItem(coca, 2);
		venta.agregarItem(sprite, 1);
		ofertaCocaSprite.aplicar(venta);
		assertEquals(24.0, venta.getTotal());
	}
	
	@Test
	public void test_Cond_Bebidas_Desc_10Porciento() {
		venta.agregarItem(coca, 1);
		venta.agregarItem(sprite, 1);
		ofertaBebidas.aplicar(venta);
		assertEquals(20.7, venta.getTotal());
	}
	
	@Test
	public void test_Cond_Bebidas_Desc_10_Cond_1Coca1Sprite_Desc_1Sprite() {
		venta.agregarItem(coca, 1);
		venta.agregarItem(sprite, 1);
		ofertaCocaSprite.aplicar(venta);
		ofertaBebidas.aplicar(venta);
		assertEquals(10.8, venta.getTotal());
	}
}
