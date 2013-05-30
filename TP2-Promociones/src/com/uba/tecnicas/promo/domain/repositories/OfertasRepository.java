package com.uba.tecnicas.promo.domain.repositories;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.uba.tecnicas.promo.domain.Oferta;

public class OfertasRepository {
	private Map<Integer, Oferta> ofertas;
	static private OfertasRepository repository = null;
	
	private OfertasRepository() {
		ofertas = new HashMap <Integer, Oferta>();
	}
	static public OfertasRepository getInstance() {
		if(repository == null) 
			repository = new OfertasRepository();
		
		return repository;
	}
	public Oferta getOferta(Integer codigo) {
		Oferta oferta = ofertas.get(codigo);		
		return oferta;
	}
	
	public Collection<Oferta> getOfertas() {
		Collection<Oferta> oferta = ofertas.values();		
		return oferta;
	}
	
	public void agregarOfertas(Integer codigo,Oferta oferta) {
		ofertas.put(codigo, oferta);
	}
	public Set<Integer> getNombreProductos() {
		return ofertas.keySet();
	}
}
