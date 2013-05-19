package com.uba.tecnicas.promo.domain;

import java.util.ArrayList;
import java.util.List;

public class VentaCaja implements Venta {

	List<Item> items = new ArrayList<Item>();
	
	@Override
	public double getTotal() {
		return items.get(0).getPrecio();
	}

	@Override
	public void agregar(Producto producto, int cantidad) {
		Item item = new ItemComprado(producto, cantidad);
		items.add(item);
	}

}
