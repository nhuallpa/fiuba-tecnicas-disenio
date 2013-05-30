package com.uba.tecnicas.promo.domain.condiciones;

import java.util.List;

import com.uba.tecnicas.promo.domain.CondicionOferta;
import com.uba.tecnicas.promo.domain.Filtro;
import com.uba.tecnicas.promo.domain.Item;
import com.uba.tecnicas.promo.domain.ProductoNoEncontradoException;
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
		try {
			Item encontrado = venta.getItemSinDescuento(filtro);
			if (encontrado.getCantidad() >= cantidad) {
				encontrado.setCantidad(cantidad);
				aplicantes.add(encontrado);
				return true;
			}
			return false;
		}
		catch (ProductoNoEncontradoException e) {
			return false;
		}
	}

}
