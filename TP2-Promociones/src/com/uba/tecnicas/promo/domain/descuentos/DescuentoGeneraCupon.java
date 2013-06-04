package com.uba.tecnicas.promo.domain.descuentos;

import java.util.List;

import com.uba.tecnicas.promo.domain.Cupon;
import com.uba.tecnicas.promo.domain.DescuentoOferta;
import com.uba.tecnicas.promo.domain.Item;
import com.uba.tecnicas.promo.domain.Venta;

public class DescuentoGeneraCupon implements DescuentoOferta {
	private double porcentajeImporte;
	private double porcentajeMaximoVenta;
	
	public DescuentoGeneraCupon(double porcentajeImporte, double porcentajeMaximoVenta) {
		this.porcentajeImporte = porcentajeImporte;
		this.porcentajeMaximoVenta = porcentajeMaximoVenta;
	}
	
	@Override
	public void aplicarDescuento(String nombre, Venta venta,
			List<Item> aplicantes) {
		double monto = 0;
		for (Item aplicante : aplicantes) {
			monto += aplicante.getImporte();
		}
		venta.generarCupon(new Cupon("Cupon: " + nombre, monto*porcentajeImporte, porcentajeMaximoVenta));
	}
}
