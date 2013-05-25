package com.uba.tecnicas.promo.domain;

import java.util.ArrayList;
import java.util.List;

public class VentaCaja implements Venta {
	private List<Item> items = new ArrayList<Item>();
	private List<Descuento> descuentos = new ArrayList<Descuento>();
	
	@Override
	public double getTotal() {
		double importe = 0;
		for (Item item : items)
			importe = item.getImporte();
		return importe;
	}

	@Override
	public void agregarItem(Producto producto, double cantidad) {
		Item item = new Item(producto, cantidad);
		items.add(item);
	}

	@Override
	public List<Item> getItems() {
		return items;
	}

	@Override
	public List<Item> getItems(FiltroItem filtro) {
		List<Item> coincidencias = new ArrayList<Item>();
		for (Item item : items) {
			if (filtro.seCumple(item)) {
				coincidencias.add(item);
			}
		}
		return coincidencias;
	}

	@Override
	public Item getItem(FiltroItem filtro) throws ProductoNoEncontradoException {
		for (Item item : items) {
			if (filtro.seCumple(item)) {
				return item;
			}
		}
		throw new ProductoNoEncontradoException();
	}

	@Override
	public List<Descuento> getDescuentos() {
		return descuentos;
	}

	@Override
	public void agregarDescuento(Descuento descuento) {
		descuentos.add(descuento);
	}
}
