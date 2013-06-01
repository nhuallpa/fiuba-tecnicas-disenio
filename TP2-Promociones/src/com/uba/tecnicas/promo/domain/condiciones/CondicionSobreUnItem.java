package com.uba.tecnicas.promo.domain.condiciones;

import java.util.List;

import com.uba.tecnicas.promo.domain.CondicionOferta;
import com.uba.tecnicas.promo.domain.Item;
import com.uba.tecnicas.promo.domain.ItemsUtils;
import com.uba.tecnicas.promo.domain.ProductoNoEncontradoException;
import com.uba.tecnicas.promo.domain.Venta;
import com.uba.tecnicas.promo.domain.filtros.FiltroProducto;

public class CondicionSobreUnItem implements CondicionOferta {
	private Item itemBuscado;
	
	public CondicionSobreUnItem(Item itemBuscado) {
		this.itemBuscado = itemBuscado;
	}

	@Override
	public boolean seCumple(Venta venta, List<Item> aplicantes) {
		try {
			Item encontrado = venta.getItemSinDescuento(new FiltroProducto(itemBuscado.getProducto()));
			if (encontrado.getCantidad() - ItemsUtils.getCantidad(aplicantes, itemBuscado.getProducto()) >= itemBuscado.getCantidad()) {
				ItemsUtils.agregar(aplicantes, itemBuscado);
				return true;
			}
			return false;
		}
		catch (ProductoNoEncontradoException e) {
			return false;
		}
	}
}
