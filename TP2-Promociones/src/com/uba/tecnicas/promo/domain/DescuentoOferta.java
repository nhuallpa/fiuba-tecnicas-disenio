package com.uba.tecnicas.promo.domain;

import java.util.List;

public interface DescuentoOferta {
	public Descuento crearDescuento(String nombre, List<Item> aplicantes);
}
