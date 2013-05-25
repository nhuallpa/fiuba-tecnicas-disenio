package com.uba.tecnicas.promo.domain.condiciones;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.uba.tecnicas.promo.domain.CondicionOferta;
import com.uba.tecnicas.promo.domain.Item;
import com.uba.tecnicas.promo.domain.Venta;

public class CondicionDia implements CondicionOferta {
	public static final CondicionOferta LUNES = new CondicionDia(Calendar.MONDAY, "Lunes");
	public static final CondicionOferta MARTES = new CondicionDia(Calendar.TUESDAY, "Martes");
	public static final CondicionOferta MIERCOLES = new CondicionDia(Calendar.WEDNESDAY, "Miercoles");
	public static final CondicionOferta JUEVES = new CondicionDia(Calendar.THURSDAY, "Jueves");
	public static final CondicionOferta VIERNES = new CondicionDia(Calendar.FRIDAY, "Viernes");
	public static final CondicionOferta SABADO = new CondicionDia(Calendar.SATURDAY, "Sabado");
	public static final CondicionOferta DOMINGO = new CondicionDia(Calendar.SUNDAY, "Domingo");
	private int diaSemana;
	private String descripcion;
	
	private CondicionDia(int diaSemana, String descripcion) {
		this.diaSemana = diaSemana;
		this.descripcion = descripcion;
	}
	
	@Override
	public List<Item> getAplicantes(Venta venta) {
		return new ArrayList<Item>();
	}

	public int getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(int diaSemana) {
		this.diaSemana = diaSemana;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	@Override
	public boolean aplica(Venta venta) {
		Calendar diaVenta = Calendar.getInstance();
		diaVenta.setTime(venta.getFechaVenta());
		return this.diaSemana == diaVenta.get(Calendar.DAY_OF_WEEK);
	}
}
