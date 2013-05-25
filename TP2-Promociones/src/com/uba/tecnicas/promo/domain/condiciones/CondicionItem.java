package com.uba.tecnicas.promo.domain.condiciones;

import java.util.ArrayList;
import java.util.List;

import com.uba.tecnicas.promo.domain.CondicionOferta;
import com.uba.tecnicas.promo.domain.Item;
import com.uba.tecnicas.promo.domain.Venta;
import com.uba.tecnicas.promo.domain.filtros.FiltroProducto;

public class CondicionItem implements CondicionOferta {
	private Item itemBuscado;
	
	public CondicionItem(Item itemBuscado) {
		this.itemBuscado = itemBuscado;
	}
	
	@Override
	public List<Item> getAplicantes(Venta venta) {
		List<Item> items = new ArrayList<Item>();
		try {
			Item encontrado = venta.getItem(new FiltroProducto(itemBuscado.getProducto()));
			if (encontrado.getCantidad() > itemBuscado.getCantidad())
			items.add(encontrado);
		}
		catch (Exception e) {
		}
		return items;
	}
}
