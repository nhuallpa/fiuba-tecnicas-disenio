package com.uba.tecnicas.promo.domain;

import java.util.ArrayList;
import java.util.List;

public class ListaItem<T extends Item> {
	private List<T> items;
	
	public ListaItem() {
		items = new ArrayList<T>();
	}
	
	public void agregar(T item) {
		for (T contenido : items) {
			if (contenido.getProducto().equals(item.getProducto())) {
				contenido.setCantidad(contenido.getCantidad() + item.getCantidad());
				return;
			}
		}
		items.add(item);
	}
	
	public void agregar(ListaItem<T> lista) {
		agregar(lista.items);
	}
	
	public void agregar(List<T> lista) {
		for (T item : lista) {
			agregar(item);
		}
	}
	
	public Item getItem(Filtro filtro) {
		for (T item : items) {
			if (filtro.seCumple(item)) {
				return item;
			}
		}
		return null;
	}
	
	public ListaItem<T> getItems(Filtro filtro) {
		ListaItem<T> coincidencias = new ListaItem<T>();
		for (T item : items) {
			if (filtro.seCumple(item)) {
				coincidencias.items.add(item);
			}
		}
		return coincidencias;
	}
	
	public int getCantidad(Producto producto) {
		for (T item : items) {
			if (item.getProducto().equals(producto)) {
				return item.getCantidad();
			}
		}
		return 0;
	}
	
	public int getCantidadTotal() {
		int cantidad = 0;
		for (T item : items) {
			cantidad += item.getCantidad();
		}
		return cantidad;
	}
	
	public double getTotal() {
		double total = 0;
		for (T item : items) {
			total += item.getImporte();
		}
		return total;
	}
	
	public List<T> listar() {
		return items;
	}
}
