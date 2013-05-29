package com.uba.tecnicas.promo.vista;

import com.uba.tecnicas.promo.domain.Caja;
import com.uba.tecnicas.promo.domain.Producto;
import com.uba.tecnicas.promo.controlador.Controlador;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
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
	private Calendar dia;
	private Caja caja;
	private Repositorio repositorio;
	
	private Controlador controlador;
	
	public Panel(Caja caja, Controlador controlador) {
		setBackground(SystemColor.activeCaption);
		setBounds(0, 0, 597, 424);
		setLayout(null);
		
		this.caja = caja;
		caja.addObserver(this);
		
		this.controlador = controlador;
		construirComponentes();
		enlazarControlador();
		
		repositorio = Repositorio.getInstance();
		agregarProductos(); //agrega productos a la fabrica
		cargarListaProductos(); // carga la lista de productos  a comprar
		
	}
	
	private void enlazarControlador() {
		botonIniciarVenta.addActionListener(controlador.getListenerBotonIniciarVenta());
		botonCerrarCaja.addActionListener(controlador.getListenerBotonCerrarCaja());
		botonAbrirCaja.addActionListener(controlador.getListenerBotonAbrirCaja());
		
	}
	
	private void construirComponentes() {
		listaProductos = new DefaultListModel<String>();
		
		scroll = new JScrollPane();
		scroll.setBounds(380, 100, 195, 117);		
		add(scroll);	
		
		labelProd = new JLabel("Productos");
		labelProd.setBounds(380, 70, 72, 21);
		labelProd.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(labelProd);
		
		labelUnidades = new JLabel("Unidades");
		labelUnidades.setBounds(380, 229, 65, 21);
		labelUnidades.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(labelUnidades);
		
		botonAgregarProd = new JButton("Agregar");
		botonAgregarProd.setBounds(503, 229, 72, 23);
		botonAgregarProd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombreProducto = listaDesplegable.getSelectedValue();
				int cantidad = (Integer)spinnerCantidad.getValue();
				
				if(cantidad > 0 && nombreProducto != null && inicioVenta != null && inicioVenta) {
					Producto producto = repositorio.getProducto(nombreProducto);
					controlador.agregarProducto(producto, cantidad);
					imprimirAgregacionDeProductos(producto);
				}
			}
		});
		botonAgregarProd.setFont(new Font("Tahoma", Font.PLAIN, 10));
	
		add(botonAgregarProd);
		
		labelFormaPago = new JLabel("Forma de Pago");
		labelFormaPago.setBounds(380, 279, 89, 14);
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
				if(open != null && open) {
					System.out.println("\nCaja cerrada");
					open = false;
				}					
			}
		});
		add(botonCerrarCaja);
		
		botonIniciarVenta = new JButton("Iniciar Venta");
		botonIniciarVenta.setBounds(321, 21, 109, 23);
		botonIniciarVenta.setFont(new Font("Tahoma", Font.PLAIN, 10));
		botonIniciarVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inicioVenta = true;
			}
		});
		add(botonIniciarVenta);
		
		botonFinalizarVenta = new JButton("Finalizar Venta");
		botonFinalizarVenta.setBounds(440, 21, 109, 23);
		botonFinalizarVenta.setFont(new Font("Tahoma", Font.PLAIN, 10));
		botonFinalizarVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inicioVenta = false;
				spinnerCantidad.setValue(1);
				
				// si hay descuento se muestra esto
				//System.out.println("Descuentos y/o Promociones");
				
				System.out.println("\nForma de Pago                               " + String.valueOf(comboBox.getSelectedItem()));
				System.out.println("\nTOTAL                                               $" + caja.getVentaTotal());
				
				System.out.println("\n\n ¡¡¡¡Este boton solo le pide a caja el total\n" +
						"no invoca al metodo finalizar caja!!!");
			}
		});
		add(botonFinalizarVenta);
		
		labelDescuentos = new JLabel("Ticket");
		labelDescuentos.setBounds(22, 70, 89, 21);
		labelDescuentos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(labelDescuentos);
		
		spinnerCantidad = new JSpinner(new SpinnerNumberModel(0,0,100,1));
		spinnerCantidad.setBounds(441, 229, 46, 20);
		add(spinnerCantidad);
		
		
		comboBox = new JComboBox<String>(lista);
		comboBox.setBounds(473, 277, 102, 20);
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
		scrollTicket.setBounds(20, 100, 322, 300);
		scrollTicket.setViewportView(textArea);
		add(scrollTicket);
		
		PrintStream printStream = new PrintStream(new StreamOutput(textArea)); 
		System.setOut(printStream);
	}
	
	
	private void imprimirAgregacionDeProductos(Producto producto) {
		if(dia != null) {
			if(primerProducto) {
				System.out.println(String.format("Fecha de Venta    %1$tY-%1$tm-%1$td", dia.getTime()));
				System.out.println("");
				System.out.println("Producto\t\t PrecioxUnidad\t Precio");
				primerProducto = false;
			}
		
			System.out.println(producto.getNombre() + "\t\t $ " + producto.getPrecio() +
				"\t $ " + (int)spinnerCantidad.getValue()*producto.getPrecio());
		}
	}
	
	
	private void agregarProductos() {
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
	
	private void cargarListaProductos() {
		listaProductos.clear();
		Set<String> keys = repositorio.getNombreProductos();
		for(final String key:keys)
			listaProductos.addElement(key);
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
	//	double total = caja.getVentaTotal();
		//labelTotalVenta.setText("$ " + new DecimalFormat("#").format(total));
	}

	
}
