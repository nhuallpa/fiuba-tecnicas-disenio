package com.uba.tecnicas.promo.vista;

import com.uba.tecnicas.promo.domain.Caja;
import com.uba.tecnicas.promo.domain.Descuento;
import com.uba.tecnicas.promo.domain.FormaPago;
import com.uba.tecnicas.promo.domain.Producto;
import com.uba.tecnicas.promo.domain.repositories.ProductoRepository;
import com.uba.tecnicas.promo.controlador.Controlador;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import java.util.Calendar;


@SuppressWarnings("serial")
public class Panel extends JPanel implements Observer{
	private DefaultListModel<String> listaProductos;
	private JScrollPane scroll;
	private JList<String> listaDesplegable; 
	private JLabel labelProd;
	private JLabel labelFormaPago;
	private JLabel labelUnidades;
	private JButton botonAgregarProd;
	private JButton botonCerrarCaja;
	private JButton botonIniciarVenta;
	private JButton botonFinalizarVenta;
	private JButton botonAbrirCaja;
	private JLabel labelDescuentos;
	private JSpinner spinnerCantidad;
	private String []lista={"Efectivo", "Debito","Credito"};
	private JComboBox<String> comboBox;
	private JTextArea textArea;
	private JScrollPane scrollTicket;
	private Boolean open;
	private Boolean primerProducto = true;
	private Boolean inicioVenta;
	private double precioTotal;
	private DecimalFormat df;
	private Calendar dia;
	private Caja caja;
	private ProductoRepository repositorio;
	
	private Controlador controlador;
	
	public Panel(Caja caja, Controlador controlador) {
		setBackground(SystemColor.activeCaption);
		setBounds(0, 0, 701, 424);
		setLayout(null);
		
		this.caja = caja;
		caja.addObserver(this);
		
		this.controlador = controlador;
		construirComponentes();
		enlazarControlador();
		
		repositorio = ProductoRepository.getInstance();
		cargarListaProductos();
		
	}
	
	private void enlazarControlador() {
		botonIniciarVenta.addActionListener(controlador.getListenerBotonIniciarVenta());
		botonCerrarCaja.addActionListener(controlador.getListenerBotonCerrarCaja());
		botonAbrirCaja.addActionListener(controlador.getListenerBotonAbrirCaja());
		
	}
	
	private void construirComponentes() {
		listaProductos = new DefaultListModel<String>();
		
		scroll = new JScrollPane();
		scroll.setBounds(466, 100, 195, 117);
		add(scroll);	
		
		labelProd = new JLabel("Productos");
		labelProd.setBounds(466, 70, 72, 21);
		labelProd.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(labelProd);
		
		labelUnidades = new JLabel("Unidades");
		labelUnidades.setBounds(466, 229, 65, 21);
		labelUnidades.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(labelUnidades);
		
		botonAgregarProd = new JButton("Agregar");
		botonAgregarProd.setBounds(589, 229, 72, 23);
		botonAgregarProd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombreProducto = listaDesplegable.getSelectedValue();
				int cantidad = (Integer)spinnerCantidad.getValue();
				
				if(cantidad > 0 && nombreProducto != null && inicioVenta != null && inicioVenta) {
					Producto producto = repositorio.getProducto(nombreProducto);
					controlador.agregarProducto(producto, cantidad);
					imprimirAgregacionDeProductos(producto, cantidad);
					precioTotal += producto.getPrecio()*cantidad;
				}
			}
		});
		botonAgregarProd.setFont(new Font("Tahoma", Font.PLAIN, 10));
	
		add(botonAgregarProd);
		
		labelFormaPago = new JLabel("Forma de Pago");
		labelFormaPago.setBounds(466, 279, 89, 14);
		labelFormaPago.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(labelFormaPago);
		
		botonAbrirCaja = new JButton("Abrir Caja");
		botonAbrirCaja.setBounds(20, 21, 102, 23);
		botonAbrirCaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Caja abierta");
				open = true;
				dia = Calendar.getInstance();
			}
		});
		botonAbrirCaja.setFont(new Font("Tahoma", Font.PLAIN, 10));
		add(botonAbrirCaja);
		
		botonCerrarCaja = new JButton("Cerrar Caja");
		botonCerrarCaja.setBounds(132, 21, 102, 23);
		botonCerrarCaja.setFont(new Font("Tahoma", Font.PLAIN, 10));
		botonCerrarCaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(open != null && open) { // el open parece redundante pero no lo es
					System.out.println("\nCaja cerrada");
					open = false;
					inicioVenta = false;
					primerProducto = true;
					precioTotal = 0;
				}					
			}
		});
		add(botonCerrarCaja);
		
		botonIniciarVenta = new JButton("Iniciar Venta");
		botonIniciarVenta.setBounds(429, 21, 109, 23);
		botonIniciarVenta.setFont(new Font("Tahoma", Font.PLAIN, 10));
		botonIniciarVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				if(caja.estaAbierta() && primerProducto) {
					System.out.println("\n\n********************************************************************************");
					System.out.println(String.format("Fecha:   %1$tY-%1$tm-%1$td", dia.getTime()) 
							+ "                            Hora: " + 
							dia.get(Calendar.HOUR_OF_DAY) + ":" + dia.get(Calendar.MINUTE));
					System.out.println("\nProducto\t\t      Cantidad\tPrecioxUnidad\t Precio");
					primerProducto = false;
					inicioVenta = true;
				}
			}
		});
		add(botonIniciarVenta);
		
		botonFinalizarVenta = new JButton("Finalizar Venta");
		botonFinalizarVenta.setBounds(552, 21, 109, 23);
		botonFinalizarVenta.setFont(new Font("Tahoma", Font.PLAIN, 10));
		botonFinalizarVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(inicioVenta != null && inicioVenta) 					
					caja.finalizarVenta(StringToFormaPago());				
				
				inicioVenta = false;
				spinnerCantidad.setValue(1);
				primerProducto = true;
			}
		});
		add(botonFinalizarVenta);
		
		labelDescuentos = new JLabel("Ticket");
		labelDescuentos.setBounds(22, 70, 89, 21);
		labelDescuentos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(labelDescuentos);
		
		spinnerCantidad = new JSpinner(new SpinnerNumberModel(0,0,100,1));
		spinnerCantidad.setBounds(527, 229, 46, 20);
		add(spinnerCantidad);
		
		
		comboBox = new JComboBox<String>(lista);
		comboBox.setBounds(559, 277, 102, 20);
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 10));
		add(comboBox);
		
		
		listaDesplegable = new JList<String>();  
		listaDesplegable.setBounds(405, 98, 193, 115);
		
		
		listaDesplegable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaDesplegable.setFont(new Font("Arial", Font.BOLD, 12));
		listaDesplegable.setModel(listaProductos);
		
		scroll.setViewportView(listaDesplegable);

		textArea = new JTextArea();
		textArea.setBounds(22, 102, 320, 298);
		
		
		scrollTicket = new JScrollPane();
		scrollTicket.setBounds(20, 100, 421, 300);
		scrollTicket.setViewportView(textArea);
		add(scrollTicket);
		
		
		df= new DecimalFormat();
		df.setMaximumFractionDigits(2);
		
		
		PrintStream printStream = new PrintStream(new StreamOutput(textArea)); 
		System.setOut(printStream);
	}
	
	
	private void imprimirAgregacionDeProductos(Producto producto, int cantidad) {
		if(dia != null) 		
			System.out.println(producto.getNombre() + "\t\t       " + cantidad + "\t $" + producto.getPrecio() +
				"\t $ " + (int)spinnerCantidad.getValue()*producto.getPrecio());
	}
	
	
	private FormaPago StringToFormaPago() {
		if(String.valueOf(comboBox.getSelectedItem()).equals("Efectivo"))
			return FormaPago.EFECTIVO;
		if(String.valueOf(comboBox.getSelectedItem()).equals("Debito"))
			return FormaPago.DEBITO;
		return FormaPago.CREDITO;		
	}
	
	private void imprimirResultadoDeVenta() {		
		System.out.println("\nNumero de articulos:  " + caja.getCantidadProductos());
		System.out.println("\nSubTotal                                                $ " + precioTotal);
		precioTotal = 0;
		List<Descuento> descuentosGenerales = caja.getVenta().getDescuentos();

		if(descuentosGenerales.size() > 0) {
			
			System.out.println("Descuentos \n");
			System.out.println("Descripcion\t\t Monto\n");
			
			for(Descuento dscto:descuentosGenerales) {
				System.out.println(dscto.getNombre() + "\t\t $ " + dscto.getImporte());
			}
		}
		
		System.out.println("\nForma de Pago                                    " + String.valueOf(comboBox.getSelectedItem()));
		System.out.println("\nTOTAL                                                    $" + caja.getVentaTotal());
		System.out.println("\n********************************************************************************");
	}
	
	private void cargarListaProductos() {
		listaProductos.clear();
		Set<String> keys = repositorio.getNombreProductos();
		for(final String key:keys)
			listaProductos.addElement(key);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		imprimirResultadoDeVenta();
	}
}
