package com.uba.tecnicas.promo.domain.condiciones;

import java.util.Calendar;
import java.util.List;

import com.uba.tecnicas.promo.domain.CondicionOferta;
import com.uba.tecnicas.promo.domain.Item;
import com.uba.tecnicas.promo.domain.Venta;

public class CondicionDia implements CondicionOferta {
	private int diaSemana;
	private CondicionOferta decorado;
	
	private CondicionDia(int diaSemana, CondicionOferta decorado) {
		this.diaSemana = diaSemana;
		this.decorado = decorado;
	}

	@Override
	public boolean seCumple(Venta venta, List<Item> aplicantes) {
		if (diaSemana == venta.getFechaVenta().get(Calendar.DAY_OF_WEEK))
			return decorado.seCumple(venta, aplicantes);
		else
			return false;
	}
	
	public static CondicionDia Lunes(CondicionOferta decorado) {
		return new CondicionDia(Calendar.MONDAY, decorado);
	}
	
	public static CondicionDia Martes(CondicionOferta decorado) {
		return new CondicionDia(Calendar.TUESDAY, decorado);
	}
	
	public static CondicionDia Miercoles(CondicionOferta decorado) {
		return new CondicionDia(Calendar.WEDNESDAY, decorado);
	}
	
	public static CondicionDia Jueves(CondicionOferta decorado) {
		return new CondicionDia(Calendar.THURSDAY, decorado);
	}
	
	public static CondicionDia Viernes(CondicionOferta decorado) {
		return new CondicionDia(Calendar.FRIDAY, decorado);
	}
	
	public static CondicionDia Sabado(CondicionOferta decorado) {
		return new CondicionDia(Calendar.SATURDAY, decorado);
	}
	
	public static CondicionDia Domingo(CondicionOferta decorado) {
		return new CondicionDia(Calendar.SUNDAY, decorado);
	}
}
