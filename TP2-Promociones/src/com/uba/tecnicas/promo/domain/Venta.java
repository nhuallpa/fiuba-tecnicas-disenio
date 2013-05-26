package com.uba.tecnicas.promo.domain;

import java.util.Calendar;
import java.util.List;

public interface Venta {
	public double getTotal();
	public void agregarItem(Producto producto, int cantidad);
	public List<ItemComprado> getItemsComprados();
	public List<Item> getItems(Filtro filtro);
	public Item getItem(Filtro filtro) throws ProductoNoEncontradoException;
	public List<Descuento> getDescuentos();
	public void agregarDescuentoGeneral(Descuento descuento);
	public void agregarDescuentoParticular(ItemDescuento descuento);
	public int getCantidadDescontada(Producto producto);
	public int getCantidadProductosVendidos();
	public Calendar getFechaVenta();
	public void setFormaPago(FormaPago formaPago);
	public boolean esFormaPago(FormaPago formaPago);
}
