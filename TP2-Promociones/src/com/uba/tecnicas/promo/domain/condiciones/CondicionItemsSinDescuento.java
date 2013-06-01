package com.uba.tecnicas.promo.domain.condiciones;

import java.util.List;

import com.uba.tecnicas.promo.domain.CondicionOferta;
import com.uba.tecnicas.promo.domain.Filtro;
import com.uba.tecnicas.promo.domain.Item;
import com.uba.tecnicas.promo.domain.ItemsUtils;
import com.uba.tecnicas.promo.domain.Venta;

public class CondicionItemsSinDescuento implements CondicionOferta {
	private Filtro filtro;
	private int cantidad;
	
	public CondicionItemsSinDescuento(Filtro filtro, int cantidad) {
		this.filtro = filtro;
		this.cantidad = cantidad;
	}
	
	@Override
	public boolean seCumple(Venta venta, List<Item> aplicantes) {
		int cant = cantidad;
		List<Item> encontrados = venta.getItemsSinDescuento(filtro);
		for (Item item : encontrados) {
			if (item.getCantidad() - ItemsUtils.getCantidad(aplicantes, item.getProducto()) >= cant) {
				item.setCantidad(cant);
			}
			ItemsUtils.agregar(aplicantes, item);
			cant -= item.getCantidad();
			if (cant == 0)
				return true;
		}
		return false;
	}
}
