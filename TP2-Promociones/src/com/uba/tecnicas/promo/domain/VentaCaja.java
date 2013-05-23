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
		return filtro.filtrar(items);
	}

	@Override
	public Item getItem(FiltroItem filtro) throws ProductoNoEncontradoException {
		return filtro.filtrarUno(items);
	}
}
