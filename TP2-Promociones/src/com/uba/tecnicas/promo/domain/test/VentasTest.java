package com.uba.tecnicas.promo.domain.test;

import java.util.Calendar;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.uba.tecnicas.promo.domain.Producto;
import com.uba.tecnicas.promo.domain.ProductoNoEncontradoException;
import com.uba.tecnicas.promo.domain.Venta;
import com.uba.tecnicas.promo.domain.VentaCaja;
import com.uba.tecnicas.promo.domain.filtros.FiltroRubro;

public class VentasTest extends TestCase {
	private Venta venta;
	
	@Before
	public void setUp() throws Exception {
		venta = new VentaCaja(Calendar.getInstance());
	}

	@Test
	public void testVentaUnCaramelo() {
		double total = 1.0;
		Producto prod = new Producto("Caramelo", 1.0, "Golosinas");
		venta.agregarItem(prod,1);
		assertEquals(total, venta.getTotal(),0);
	}
	
	@Test
	public void testVentaUnDetegente() {
		double total = 6.0;
		Producto prod = new Producto("Detergente", 6.0, "Limpieza");
		venta.agregarItem(prod,1);
		assertEquals(total, venta.getTotal(), 0);
	}
	
	@Test
	public void testFiltrarUnItemPorRubro() {
		Producto prod1 = new Producto("Fosforos", 6.0, "Cocina");
		Producto prod2 = new Producto("Detergente", 6.0, "Limpieza");
		venta.agregarItem(prod1, 1);
		venta.agregarItem(prod2, 1);
		try {
			assertEquals(prod2.getNombre(), venta.getItem(new FiltroRubro("Limpieza")).getProducto().getNombre());
		} catch (ProductoNoEncontradoException e) {
			assertFalse("Fallo al no encontrar un item por el rubro", true);
		}
	}
	
	@Test
	public void testFiltrarMuchosItemsPorRubro() {
		Producto prod1 = new Producto("Arroz", 6.0, "Cocina");
		Producto prod2 = new Producto("Detergente", 6.0, "Limpieza");
		Producto prod3 = new Producto("Esponja", 6.0, "Limpieza");
		Producto prod4 = new Producto("Fideos", 6.0, "Cocina");
		venta.agregarItem(prod1, 1);
		venta.agregarItem(prod2, 1);
		venta.agregarItem(prod3, 1);
		venta.agregarItem(prod4, 1);
		assertEquals(prod2.getNombre(), venta.getItems(new FiltroRubro("Limpieza")).get(0).getProducto().getNombre());
		assertEquals(prod3.getNombre(), venta.getItems(new FiltroRubro("Limpieza")).get(1).getProducto().getNombre());
	}
}
