package com.uba.tecnicas.promo.domain;

import java.util.ArrayList;
import java.util.List;

public abstract class FiltroItem {
	
	public List<Item> filtrar(List<Item> entrada) {
		List<Item> coincidencias = new ArrayList<Item>();
		for (Item item : entrada) {
			if (coincide(item)) {
				coincidencias.add(item);
			}
		}
		return coincidencias;
	}
	
	public Item filtrarUno(List<Item> entrada) throws ProductoNoEncontradoException {
		for (Item item : entrada) {
			if (coincide(item)) {
				return item;
			}
		}
		throw new ProductoNoEncontradoException();
	}
	
	public abstract boolean coincide(Item entrada);
}
