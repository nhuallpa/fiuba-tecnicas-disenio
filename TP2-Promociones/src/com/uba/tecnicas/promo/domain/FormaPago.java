package com.uba.tecnicas.promo.domain;

public class FormaPago {
	
	public static final FormaPago EFECTIVO = new FormaPago(1,"Efectivo");
	public static final FormaPago CREDITO = new FormaPago(2,"Credito");
	public static final FormaPago DEBITO = new FormaPago(2,"Debito");
	private int codigo;
	private String descripcion;

	private FormaPago(int codigo, String descripcion) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	@Override
	public String toString() {
		return "FormaPago [codigo=" + codigo + ", descripcion=" + descripcion
				+ "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		result = prime * result
				+ ((descripcion == null) ? 0 : descripcion.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FormaPago other = (FormaPago) obj;
		if (codigo != other.codigo)
			return false;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		return true;
	}
	
}
