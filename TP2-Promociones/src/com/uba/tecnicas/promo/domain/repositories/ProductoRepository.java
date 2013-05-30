package com.uba.tecnicas.promo.domain.repositories;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.uba.tecnicas.promo.domain.Producto;

public class ProductoRepository {
	private Map<String, Producto> productos;
	static private ProductoRepository repository = null;
	
	private ProductoRepository() {
		productos = new HashMap <String, Producto>();
	}
	
	static public ProductoRepository getInstance() {
		if(repository == null) 
			repository = new ProductoRepository();
		
		return repository;
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
