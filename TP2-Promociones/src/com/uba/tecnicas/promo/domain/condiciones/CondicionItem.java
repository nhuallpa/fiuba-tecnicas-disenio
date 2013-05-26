package com.uba.tecnicas.promo.domain.condiciones;

import java.util.ArrayList;
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
	public List<Item> getAplicantes(Venta venta) {
		List<Item> items = new ArrayList<Item>();
		try {
			Item encontrado = venta.getItem(new FiltroProducto(itemBuscado.getProducto()));
			double diferencia = encontrado.getCantidad() - venta.getCantidadDescontada(itemBuscado.getProducto());
			if (diferencia >= itemBuscado.getCantidad())
				items.add(itemBuscado);
		}
		catch (ProductoNoEncontradoException e) {}
		return items;
	}
}
