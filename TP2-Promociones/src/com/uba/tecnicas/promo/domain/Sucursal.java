package com.uba.tecnicas.promo.domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;



public class Sucursal {
	private List<ItemComprado> items;
	private SortedMap<String, Integer> map;
	
	public Sucursal() {
		items = new ArrayList<>();
		Comparator<Producto> k =null;
		map = new TreeMap<>();
	}
	public void agregarItem(List<ItemComprado> itemNuevo) {
		for (ItemComprado item : itemNuevo) 
			items.add(item);
	}
	
	public List<ItemComprado> getItems() {
		return items;
	}
	public SortedMap<String, Integer>  getRanking() {
		for (ItemComprado item : items) {
			String nomProduct = item.getProducto().getNombre();
			if (map.containsKey(nomProduct)) {
				map.put(nomProduct, item.getCantidad() + map.get(nomProduct));
			} else {
				map.put(nomProduct, item.getCantidad());
			}
		}
		return map;
	}
	
}
