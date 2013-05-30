package com.uba.tecnicas.promo.domain.test;

import java.util.Calendar;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.uba.tecnicas.promo.domain.Caja;
import com.uba.tecnicas.promo.domain.exception.CajaCerradaException;
import com.uba.tecnicas.promo.domain.exception.MarketException;

public class CajaTest extends TestCase {
	Caja caja;
	
	@Before
	public void setUp() throws Exception {
		caja = new Caja();
	}
	
	@Test
	public void testIniciaCerrada() {
		assertFalse(caja.estaAbierta());
	}
	
	@Test
	public void testAbrirCaja() {
		caja.abrir(Calendar.getInstance());
		assertTrue(caja.estaAbierta());
	}
	
	@Test
	public void testCerrarCaja() {
		caja.cerrar();
		assertFalse(caja.estaAbierta());
	}
	
	@Test
	public void testInciarVentaSoloSiEstaAbierta() throws MarketException {
		caja.abrir(Calendar.getInstance());
		caja.iniciarVenta();
		assertTrue(caja.estaAbierta());
	}
	
	@Test(expected = CajaCerradaException.class)
	public void testNoInciarVentaConCajaCerrada() throws MarketException {
		caja.iniciarVenta();
		assertTrue(caja.estaAbierta());
	}
}
