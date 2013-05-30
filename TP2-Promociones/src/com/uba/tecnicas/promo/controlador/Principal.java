package com.uba.tecnicas.promo.controlador;

import com.uba.tecnicas.promo.domain.Caja;
import com.uba.tecnicas.promo.domain.CondicionOferta;
import com.uba.tecnicas.promo.domain.DescuentoOferta;
import com.uba.tecnicas.promo.domain.FormaPago;
import com.uba.tecnicas.promo.domain.ItemComprado;
import com.uba.tecnicas.promo.domain.Oferta;
import com.uba.tecnicas.promo.domain.Producto;
import com.uba.tecnicas.promo.domain.condiciones.CondicionDia;
import com.uba.tecnicas.promo.domain.condiciones.CondicionFormaPago;
import com.uba.tecnicas.promo.domain.condiciones.CondicionSobreUnItem;
import com.uba.tecnicas.promo.domain.descuentos.DescuentoSobreProducto;
import com.uba.tecnicas.promo.domain.descuentos.DescuentoSobreTotal;
import com.uba.tecnicas.promo.domain.repositories.OfertasRepository;
import com.uba.tecnicas.promo.domain.repositories.ProductoRepository;
import com.uba.tecnicas.promo.vista.Vista;

public class Principal {
	
	public static void main(String[] args) {
		levantarProductos();
		levantarOfertas();
		Caja caja = new Caja(OfertasRepository.getInstance().getOfertas());
		Controlador controlador = new Controlador(caja);
		Vista vista = new Vista(caja, controlador);
		vista.ejecutar();
	}
	
	
	private static void levantarProductos() {
		ProductoRepository repositorio = ProductoRepository.getInstance();
		repositorio.agregarProducto(new Producto("CocaCola", 9.0, "Bebidas"));
		repositorio.agregarProducto(new Producto("Pepsi", 7.0, "Bebidas"));
		repositorio.agregarProducto(new Producto("Fanta", 9.0, "Bebidas"));
		repositorio.agregarProducto(new Producto("Leche", 5.5, "Lacteos"));
		repositorio.agregarProducto(new Producto("Yogurt", 8.0, "Lacteos"));
		repositorio.agregarProducto(new Producto("Fosforos", 2.3, "Cocina"));
		repositorio.agregarProducto(new Producto("Detergente", 8.0, "Limpieza"));
		repositorio.agregarProducto(new Producto("Jabon", 4.5, "Limpieza"));
		repositorio.agregarProducto(new Producto("Shampoo", 14.0, "Limpieza"));
		repositorio.agregarProducto(new Producto("Vino Malbec", 14.0, "Bebidas"));
		repositorio.agregarProducto(new Producto("Vino Trapiche", 14.0, "Bebidas"));
	}
	
	private static void levantarOfertas() {
		ProductoRepository repositorio = ProductoRepository.getInstance();
		Producto coca = repositorio.getProducto("CocaCola");
		OfertasRepository ofertaRepository = OfertasRepository.getInstance();
		CondicionOferta condicionDosPorUno = new CondicionSobreUnItem(new ItemComprado(coca, 2)); 
		CondicionOferta condicionDiaYPago = CondicionDia.Jueves(new CondicionFormaPago(FormaPago.CREDITO));;
		DescuentoOferta descuento = new DescuentoSobreTotal(0.1);
		DescuentoOferta descuentoCoca = new DescuentoSobreProducto(coca, 1);
		Oferta ofertaJueves = new Oferta("10% Con Credito", condicionDiaYPago, descuento, false);
		Oferta ofertaDosPorUno = new Oferta("2x1 Coca", condicionDosPorUno, descuentoCoca, false); 
		ofertaRepository.agregarOfertas(1, ofertaJueves);
		ofertaRepository.agregarOfertas(1, ofertaDosPorUno);
	}
}
