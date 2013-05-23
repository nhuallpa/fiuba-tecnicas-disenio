package com.uba.tecnicas.promo.domain;

import java.util.List;

public interface Venta {
	public double getTotal();
	public void agregar(Producto producto, int cantidad);
	public List<Item> getItems();
	public List<Item> getItems(FiltroItem filtro);
	public Item getItem(FiltroItem filtro) throws ProductoNoEncontradoException;
}
