package com.uba.tecnicas.promo.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class VentaCaja implements Venta {
	private List<ItemComprado> items;
	private List<ItemDescuento> descuentosParticulares; 
	private List<Descuento> descuentosGenerales;
	private List<Cupon> cuponesGenerados;
	private List<Cupon> cuponesAplicados;
	private Calendar fechaVenta;
	private FormaPago formaPago;

	public VentaCaja(Calendar fechaVenta) {
		this.fechaVenta = fechaVenta;
		items = new ArrayList<ItemComprado>();
		descuentosParticulares = new ArrayList<ItemDescuento>();
		descuentosGenerales = new ArrayList<Descuento>();
		cuponesGenerados = new ArrayList<Cupon>();
		cuponesAplicados = new ArrayList<Cupon>();
	}
	
	private double getSubtotal() {
		double importe = 0;
		for (ItemComprado item : items)
			importe += item.getImporte();
		for (ItemDescuento descuento : descuentosParticulares)
			importe += descuento.getImporte();
		for (Descuento descuento : descuentosGenerales)
			importe += descuento.getImporte();
		return importe;
	}
	
	@Override
	public double getTotal() {
		double subtotal = getSubtotal();
		double total = subtotal;
		for (Cupon cupon : cuponesAplicados) {
			total -= cupon.getImporte(subtotal);
		}
		return total;
	}

	@Override
	public void agregarItem(Producto producto, int cantidad) {
		ItemComprado item = new ItemComprado(producto, cantidad);
		for (Item itemContenido : items) {
			if (itemContenido.getProducto().equals(producto)) {
				itemContenido.setCantidad(itemContenido.getCantidad() + cantidad);
				return;
			}
		}
		items.add(item);
	}

	@Override
	public List<ItemComprado> getItemsComprados() {
		return items;
	}

	@Override
	public List<Item> getItems(Filtro filtro) {
		List<Item> coincidencias = new ArrayList<Item>();
		for (ItemComprado item : items) {
			if (filtro.seCumple(item)) {
				coincidencias.add(item);
			}
		}
		for (ItemDescuento descuento : descuentosParticulares) {
			if (filtro.seCumple(descuento)) {
				coincidencias.add(descuento);
			}
		}
		return coincidencias;
	}

	@Override
	public Item getItem(Filtro filtro) throws ProductoNoEncontradoException {
		for (ItemComprado item : items) {
			if (filtro.seCumple(item)) {
				return item;
			}
		}
		throw new ProductoNoEncontradoException();
	}
	
	@Override
	public Item getItemSinDescuento(Filtro filtro) throws ProductoNoEncontradoException {
		for (ItemComprado item : items) {
			if (filtro.seCumple(item)) {
				return getItemDisponible(item);
			}
		}
		throw new ProductoNoEncontradoException();
	}
	
	@Override
	public List<Item> getItemsSinDescuento(Filtro filtro) {
		List<Item> coincidencias = new ArrayList<Item>();
		for (ItemComprado item : items) {
			try {
				if (filtro.seCumple(item))
					coincidencias.add(getItemDisponible(item));
			}
			catch (ProductoNoEncontradoException e) {
				
			}
		}
		return coincidencias;
	}
	
	private Item getItemDisponible(Item item) throws ProductoNoEncontradoException {
		int cantidadDescontada = getCantidadDescontada(item.getProducto());
		if (cantidadDescontada < item.getCantidad())
			return new ItemComprado(item.getProducto(), item.getCantidad() - cantidadDescontada);
		throw new ProductoNoEncontradoException();
	}

	private int getCantidadDescontada(Producto producto) {
		int cantidad = 0;
		for (ItemDescuento descuento : descuentosParticulares) {
			cantidad += descuento.getCantidadAplicantes(producto);
		}
		return cantidad;
	}

	@Override
	public List<Descuento> getDescuentos() {
		List<Descuento> descuentos = new ArrayList<Descuento>();
		descuentos.addAll(descuentosGenerales);
		for (Descuento desc : descuentosParticulares) {
			descuentos.add(desc);
		}
		return descuentos;
	}

	@Override
	public void agregarDescuentoGeneral(Descuento descuento) {
		descuentosGenerales.add(descuento);
	}

	@Override
	public int getCantidadProductosVendidos() {
		int cantidadProductos = 0;
		for (ItemComprado item : items) {
			cantidadProductos += item.getCantidad(); 
		}
		return cantidadProductos;
	}

	@Override
	public Calendar getFechaVenta() {
		return fechaVenta;
	}
	
	public void setFechaVenta(Calendar fechaVenta) {
		this.fechaVenta = fechaVenta;
	}
	
	@Override
	public void agregarDescuentoParticular(ItemDescuento descuento) {
		descuentosParticulares.add(descuento);
	}

	@Override
	public boolean esFormaPago(FormaPago formaPago){
		if (this.formaPago == null || formaPago == null) return false;
		return this.formaPago.equals(formaPago);
	}

	@Override
	public void setFormaPago(FormaPago formaPago) {
		this.formaPago = formaPago;
	}

	@Override
	public void agregarCupon(Cupon cupon) {
		cuponesAplicados.add(cupon);
	}

	@Override
	public List<Cupon> getCupones() {
		return cuponesGenerados;
	}

	@Override
	public void generarCupon(Cupon cupon) {
		cuponesGenerados.add(cupon);
	}
}
