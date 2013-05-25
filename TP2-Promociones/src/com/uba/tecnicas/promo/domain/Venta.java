package com.uba.tecnicas.promo.domain;

import java.util.List;

public interface Venta {
	public double getTotal();
	public void agregarItem(Producto producto, double cantidad);
	public List<Item> getItems();
	public List<Item> getItems(FiltroItem filtro);
	public Item getItem(FiltroItem filtro) throws ProductoNoEncontradoException;
	public List<Descuento> getDescuentos();
	public void agregarDescuento(Descuento descuento);
}
