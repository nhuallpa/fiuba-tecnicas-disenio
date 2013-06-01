package com.uba.tecnicas.promo.domain.test;

import java.util.Calendar;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.uba.tecnicas.promo.domain.CondicionOferta;
import com.uba.tecnicas.promo.domain.ItemComprado;
import com.uba.tecnicas.promo.domain.Oferta;
import com.uba.tecnicas.promo.domain.Producto;
import com.uba.tecnicas.promo.domain.Venta;
import com.uba.tecnicas.promo.domain.VentaCaja;
import com.uba.tecnicas.promo.domain.condiciones.CondicionBucle;
import com.uba.tecnicas.promo.domain.condiciones.CondicionCompuesta;
import com.uba.tecnicas.promo.domain.condiciones.CondicionSobreUnItem;
import com.uba.tecnicas.promo.domain.condiciones.CondicionItemsSinDescuento;
import com.uba.tecnicas.promo.domain.condiciones.CondicionRubro;
import com.uba.tecnicas.promo.domain.descuentos.DescuentoGeneraCupon;
import com.uba.tecnicas.promo.domain.descuentos.DescuentoSobreAplicantes;
import com.uba.tecnicas.promo.domain.descuentos.DescuentoSobreElMasCaro;
import com.uba.tecnicas.promo.domain.descuentos.DescuentoSobreProducto;
import com.uba.tecnicas.promo.domain.filtros.FiltroProducto;
import com.uba.tecnicas.promo.domain.filtros.FiltroRubro;

public class DescuentosTest extends TestCase {
	private Venta venta;
	private Producto coca;
	private Producto sprite;
	private Producto fanta;
	
	private Oferta ofertaBebidas;
	private Oferta ofertaCocaSprite;
	private Oferta ofertaSegundaBebidaAl50;
	
	public DescuentosTest() {
		coca = new Producto("Coca", 12, "Bebidas");
		sprite = new Producto("Sprite", 11, "Bebidas");
		fanta = new Producto("Fanta", 13, "Bebidas");

		ofertaBebidas = new Oferta("10% en bebidas",
				new CondicionRubro("Bebidas"),
				new DescuentoSobreAplicantes(0.1), false);

		CondicionCompuesta condCompuesta = new CondicionCompuesta();
		condCompuesta.agregar(new CondicionSobreUnItem(new ItemComprado(coca, 1)));
		condCompuesta.agregar(new CondicionSobreUnItem(new ItemComprado(sprite, 1)));
		
		ofertaCocaSprite = new Oferta("Sprite gratis con una coca",
				condCompuesta, new DescuentoSobreProducto(sprite, 1), true);
		
		ofertaSegundaBebidaAl50 = new Oferta("2da bebida al 50%",
				new CondicionItemsSinDescuento(
						new FiltroRubro("Bebidas").And(new FiltroProducto(fanta).Not()), 2),
				new DescuentoSobreElMasCaro(0.5), true);
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
	
	@Test
	public void test_Cond_2daBebida_Desc_50_2Cocas1Sprite() {
		venta.agregarItem(coca, 2);
		venta.agregarItem(sprite, 1);
		ofertaSegundaBebidaAl50.aplicar(venta);
		assertEquals(12.0 + 6.0 + 11.0, venta.getTotal());
	}
	
	@Test
	public void test_Cond_2daBebida_Desc_50_1Coca1Sprite() {
		venta.agregarItem(coca, 1);
		venta.agregarItem(sprite, 1);
		ofertaSegundaBebidaAl50.aplicar(venta);
		assertEquals(6.0 + 11.0, venta.getTotal());
	}
	
	@Test
	public void test_Cond_2daBebida_Desc_50_1Coca1Fanta1Sprite() {
		venta.agregarItem(coca, 1);
		venta.agregarItem(fanta, 1);
		venta.agregarItem(sprite, 1);
		ofertaSegundaBebidaAl50.aplicar(venta);
		assertEquals(6.0 + 13.0 + 11.0, venta.getTotal());
	}
	
	@Test
	public void test_Cond_SpriteAl30ComprandoUnaCoca() {
		CondicionCompuesta condSpriteCoca = new CondicionCompuesta();
		condSpriteCoca.agregar(new CondicionSobreUnItem(new ItemComprado(coca, 1)));
		condSpriteCoca.agregar(new CondicionSobreUnItem(new ItemComprado(sprite, 1)));
		
		Oferta oferta = new Oferta("Sprite gratis con una coca",
				condSpriteCoca, new DescuentoSobreProducto(sprite, 0.3), true);
		venta.agregarItem(coca, 1);
		venta.agregarItem(sprite, 1);
		oferta.aplicar(venta);
		assertEquals(12 + 0.7*11, venta.getTotal());
	}
	
	@Test
	public void test_generacionCuponDescuento() {
		CondicionOferta cond = new CondicionBucle(new CondicionSobreUnItem(new ItemComprado(coca, 2)));
		Oferta oferta = new Oferta("2x1 en coca", cond, new DescuentoGeneraCupon(0.5, 0.2), false);
		venta.agregarItem(coca, 10);
		oferta.aplicar(venta);
		assertEquals(60.0, venta.getCupones().get(0).getImporte(1000));
		assertEquals(2.0, venta.getCupones().get(0).getImporte(10));
	}
}
