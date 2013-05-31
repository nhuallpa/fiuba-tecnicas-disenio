package com.uba.tecnicas.promo.domain;

import java.util.ArrayList;
import java.util.List;

public class ListaItem {
	private List<Item> items;
	
	public ListaItem() {
		items = new ArrayList<Item>();
	}
	
	public void agregar(Item item) {
		for (Item contenido : items) {
			if (contenido.getProducto().equals(item.getProducto())) {
				contenido.setCantidad(contenido.getCantidad() + item.getCantidad());
				return;
			}
		}
		items.add(item);
	}
	
	public Item getItem(Filtro filtro) {
		for (Item item : items) {
			if (filtro.seCumple(item)) {
				return item;
			}
		}
		return null;
	}
	
	public List<Item> getItems(Filtro filtro) {
		List<Item> coincidencias = new ArrayList<Item>();
		for (Item item : items) {
			if (filtro.seCumple(item)) {
				coincidencias.add(item);
			}
		}
		return coincidencias;
	}
	
	public List<Item> listar() {
		return items;
	}
}
