package com.uba.tecnicas.promo.domain.repositories;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.uba.tecnicas.promo.domain.Producto;

public class Repositorio {
	private Map<String, Producto> productos;
	static private Repositorio fabrica = null;
	
	private Repositorio() {
		productos = new HashMap <String, Producto>();
	}
	
	static public Repositorio getInstance() {
		if(fabrica == null) 
			fabrica = new Repositorio();
		
		return fabrica;
	}
	
	public Producto getProducto(String nombre) {
		Producto producto = productos.get(nombre);		
		return new Producto(nombre, producto.getPrecio(), producto.getRubro());
	}
	public void agregarProducto(Producto p) {
		productos.put(p.getNombre(),p);
	}
	public Set<String> getNombreProductos() {
		return productos.keySet();
	}
}
