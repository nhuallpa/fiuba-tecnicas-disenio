package com.uba.tecnicas.promo.domain.test;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.SortedMap;
import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

import com.uba.tecnicas.promo.domain.Caja;
import com.uba.tecnicas.promo.domain.CondicionOferta;
import com.uba.tecnicas.promo.domain.DescuentoOferta;
import com.uba.tecnicas.promo.domain.FormaPago;
import com.uba.tecnicas.promo.domain.ItemComprado;
import com.uba.tecnicas.promo.domain.Oferta;
import com.uba.tecnicas.promo.domain.Producto;
import com.uba.tecnicas.promo.domain.Sucursal;
import com.uba.tecnicas.promo.domain.Venta;
import com.uba.tecnicas.promo.domain.VentaCaja;
import com.uba.tecnicas.promo.domain.condiciones.CondicionCompuesta;
import com.uba.tecnicas.promo.domain.condiciones.CondicionSobreUnItem;
import com.uba.tecnicas.promo.domain.descuentos.DescuentoSobreProducto;
import com.uba.tecnicas.promo.domain.exception.MarketException;

public class SucursalTest {
	
	private Sucursal sucursal;

	private Producto coca;
	private Producto sprite;
	private Producto maceta;
	private Caja caja1;
	private Caja caja2;
	private Oferta oferta;
	private CondicionOferta condicion1;
	private CondicionOferta condicion2;
	private CondicionCompuesta condicionCompuesta;
	private DescuentoOferta descuento;
	
	@Before
	public void setUp() throws Exception {
		sucursal = new Sucursal();
		coca = new Producto("CocaCola", 6, "Bebidas");
		sprite = new Producto("Sprite", 7, "Bebidas");
		maceta = new Producto("Maceta", 5, "Jardineria");
		
		condicion1 = new CondicionSobreUnItem(new ItemComprado(coca, 1));
		condicion2 = new CondicionSobreUnItem(new ItemComprado(sprite, 1));
		condicionCompuesta = new CondicionCompuesta();
		condicionCompuesta.agregar(condicion1);
		condicionCompuesta.agregar(condicion2);
		descuento = new DescuentoSobreProducto(sprite, 0.3);
		oferta = new Oferta("coca+sprite 30%", condicion1, descuento, true);
		caja1 = new Caja(new Vector<Oferta>(), sucursal);
		caja2 = new Caja(new Vector<Oferta>(), sucursal);
	}
	
	@Test
	public void testRanking() throws MarketException {
		caja1.abrir(Calendar.getInstance());
		caja2.abrir(Calendar.getInstance());		
		caja1.iniciarVenta();
		caja2.iniciarVenta();
		
		caja1.agregarProducto(coca, 3);
		caja1.agregarProducto(sprite, 2);
		caja1.agregarProducto(maceta, 1);
		
		caja2.agregarProducto(coca, 1);
		caja2.agregarProducto(sprite, 4);
		caja2.agregarProducto(maceta, 4);
		
		caja1.finalizarVenta(FormaPago.CREDITO);
		caja2.finalizarVenta(FormaPago.EFECTIVO);
		SortedMap<String, Integer> rank = sucursal.getRanking();
		
		
		assertEquals(new Integer(6), rank.get("Sprite"));
		assertEquals(new Integer(5), rank.get("Maceta"));
		assertEquals(new Integer(4), rank.get("CocaCola"));
		
		
		assertEquals(new Integer(6), rank.get(rank.firstKey()));
		rank.remove(rank.firstKey());
		assertEquals(new Integer(5), rank.get(rank.firstKey()));
		rank.remove(rank.firstKey());
		assertEquals(new Integer(4), rank.get(rank.firstKey()));
		
	}
	
	
}
