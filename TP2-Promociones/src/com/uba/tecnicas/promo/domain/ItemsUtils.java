package com.uba.tecnicas.promo.domain;

import java.util.List;

public class ItemsUtils {
	public static int getCantidad(List<Item> items, Producto producto) {
		for (Item item : items) {
			if (item.getProducto().equals(producto))
				return item.getCantidad();
		}
		return 0;
	}
	
	public static void agregar(List<Item> items, Item item) {
		for (Item contenido : items) {
			if (contenido.getProducto().equals(item.getProducto())) {
				contenido.setCantidad(contenido.getCantidad() + item.getCantidad());
				return;
			}
		}
		items.add(new ItemComprado(item.getProducto(), item.getCantidad()));
	}
	
	public static double getTotal(List<Item> items) {
		double total = 0;
		for (Item item : items) {
			total += item.getImporte();
		}
		return total;
	}
	
	public static int getCantidadTotal(List<Item> items) {
		int cantidad = 0;
		for (Item item : items) {
			cantidad += item.getCantidad();
		}
		return cantidad;
	}
}
