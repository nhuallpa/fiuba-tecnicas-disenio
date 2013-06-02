package com.uba.tecnicas.promo.domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import com.uba.tecnicas.promo.utils.RankingComparator;



public class Sucursal {
	private List<ItemComprado> items;

	private Map<String, Integer> productoVendidos;
	
	public Sucursal() {
		items = new ArrayList<>();
		Comparator<Producto> k =null;
		productoVendidos = new  HashMap<String, Integer>();
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
			if (productoVendidos.containsKey(nomProduct)) {
				productoVendidos.put(nomProduct, item.getCantidad() + productoVendidos.get(nomProduct));
			} else {
				productoVendidos.put(nomProduct, item.getCantidad());
			}
		}
		RankingComparator rankComp = new RankingComparator(productoVendidos);
		SortedMap<String, Integer> ranking = new TreeMap<>(rankComp);
		ranking.putAll(productoVendidos);
		return ranking;
	}
	
}
