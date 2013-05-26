package com.uba.tecnicas.promo.domain.condiciones;

import java.util.List;

import com.uba.tecnicas.promo.domain.CondicionOferta;
import com.uba.tecnicas.promo.domain.Item;
import com.uba.tecnicas.promo.domain.ProductoNoEncontradoException;
import com.uba.tecnicas.promo.domain.Venta;
import com.uba.tecnicas.promo.domain.filtros.FiltroProducto;

public class CondicionItem implements CondicionOferta {
	private Item itemBuscado;
	
	public CondicionItem(Item itemBuscado) {
		this.itemBuscado = itemBuscado;
	}

	@Override
	public boolean seCumple(Venta venta, List<Item> aplicantes) {
		try {
			Item encontrado = venta.getItem(new FiltroProducto(itemBuscado.getProducto()));
			int diferencia = encontrado.getCantidad() - venta.getCantidadDescontada(itemBuscado.getProducto());
			if (diferencia >= itemBuscado.getCantidad()) {
				aplicantes.add(itemBuscado);
				return true;
			}
			else
				return false;
		}
		catch (ProductoNoEncontradoException e) {
			return false;
		}
	}
}
