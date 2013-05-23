package com.uba.tecnicas.promo.domain;

import java.util.ArrayList;
import java.util.List;

public class VentaCaja implements Venta {
	private List<Item> items = new ArrayList<Item>();
	
	@Override
	public double getTotal() {
		double importe = 0;
		for (Item item : items)
			importe = item.getImporte();
		return importe;
	}

	@Override
	public void agregar(Producto producto, int cantidad) {
		Item item = new ItemComprado(producto, cantidad);
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
}
