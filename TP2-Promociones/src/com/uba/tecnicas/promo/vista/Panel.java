package com.uba.tecnicas.promo.vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

import com.uba.tecnicas.promo.domain.Caja;
import com.uba.tecnicas.promo.domain.Producto;
import com.uba.tecnicas.promo.domain.exception.MarketException;

import java.util.Calendar;


@SuppressWarnings("serial")
public class Panel extends JPanel implements Observer{
	private DefaultListModel<String> listaProductos;
	private JScrollPane scroll;
	private JList<String> listaDesplegable; 
	private JButton botonAbrirCaja;
	private JButton botonCerrarCaja;
	private JButton botonIniciarVenta;
	private JButton botonCerrarVenta;
	private JButton botonAgregarProd;
	private JLabel labelProdComprados;
	private JLabel labelProd;
	private JLabel labelDescuentos;	
	private JLabel labelTotal;
	private JLabel labelDesc;
	private JLabel labelTotalVenta;
	private JLabel labelUnidades;	
	private JLabel labelFormaPago;
	private JLabel labelSubtotal;
	private JLabel labelTotalDscto;
	private JLabel labelValorSubtotal;
	private JSpinner spinnerCantidad;
	private JRadioButton jrEfectivo;
	private JRadioButton jrTarjeta;
	private JSeparator separador;
	private JSeparator separator;
	private JTable tablaProdComp;
	private JTable tablaDsctos;
	private JScrollPane panelProdComp;
	private JScrollPane panelDsctos;
	private String formaPago;
	
	

	// **//
	private Caja caja;
	private Calendar dia;	
	private Repositorio repositorio;
	
	
	public Panel(Caja caja) {
		setBackground(Color.LIGHT_GRAY);
		setBounds(0, 0, 594, 383);
		setLayout(null);
		
		this.caja = caja;
		caja.addObserver(this);
		
		construirComponentes();
		agregarListener();
		
		repositorio = Repositorio.getInstance();
		agregarProductos(); //agrega productos a la fabrica
		cargarListaProductos(); // carga la lista de productos  a comprar
	}
	
	
	/*
	 * ESTO DEBERIA ESTAR EN EL CONTROLADOR
	 */
	private void agregarListener() {
		jrEfectivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jrTarjeta.setSelected(false);
				formaPago = "Efectivo";
			}
		});
		
		jrTarjeta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jrEfectivo.setSelected(false);
				formaPago = "Tarjeta";
			}
		});
	
		
		botonAbrirCaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dia = Calendar.getInstance();
				int day = Calendar.getInstance().get(Calendar.DATE);
				int mes = Calendar.getInstance().get(Calendar.MONTH);
				int annio = Calendar.getInstance().get(Calendar.YEAR);
				dia.set(annio, day, mes);
				caja.abrir(dia);
				
			}
		});
		
		botonIniciarVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					caja.iniciarVenta();
				} catch (MarketException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				limpiar();
			}
		});
		
		botonAgregarProd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombreProducto = listaDesplegable.getSelectedValue();
				int cantidad = (Integer)spinnerCantidad.getValue();
				
				if(cantidad > 0 && nombreProducto.length() != 0) {
					Producto producto = repositorio.getProducto(nombreProducto);
					
					Object[] fila = {producto.getNombre(), producto.getPrecio(), cantidad*producto.getPrecio()};					
					DefaultTableModel modelo = (DefaultTableModel)tablaProdComp.getModel();
					modelo.addRow(fila);
					tablaProdComp.setModel(modelo);
					
					caja.agregarProducto(producto, cantidad);
				}
			}
		});
		
		botonCerrarVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("aun no llama a finalizar caja");
			}
		});
		
		botonCerrarCaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				caja.cerrar();
			}
		});
	}
	
	
	// cargando productos al repositorio
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
	
	// se carga los productos del repositorio
	private void cargarListaProductos() {
		listaProductos.clear();
		Set<String> keys = repositorio.getNombreProductos();
		for(final String key:keys)
			listaProductos.addElement(key);
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		/*
		double total = caja.getVentaTotal();
		labelTotalVenta.setText("$ " + new DecimalFormat("#").format(total));
		*/
		
	}
	
	// limpia la lista de ventas y descuentos
	void limpiar() {
		  DefaultTableModel modelo = (DefaultTableModel) tablaProdComp.getModel();
		  int filas = tablaProdComp.getRowCount();
		  
		  for (int i = 0;filas>i; i++) 
		  modelo.removeRow(0);
		  
		  modelo = (DefaultTableModel) tablaDsctos.getModel();
		  filas = tablaDsctos.getRowCount();
		  
		  for (int i = 0;filas>i; i++) 
		  modelo.removeRow(0);
	}
	
	
	// Se construye todos los componentes del panel
	private void construirComponentes() {
		listaProductos = new DefaultListModel<String>();

		listaDesplegable = new JList<String>();
		listaDesplegable.setFont(new Font("Arial", Font.BOLD, 12));		
		listaDesplegable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaDesplegable.setModel(listaProductos);
		
		scroll = new JScrollPane();	
		scroll.setBounds(378, 98, 195, 117);
		scroll.setViewportView(listaDesplegable);
		add(scroll);		
		
		botonAbrirCaja = new JButton("Abrir Caja");
		botonAbrirCaja.setFont(new Font("Tahoma", Font.PLAIN, 10));
		botonAbrirCaja.setBounds(20, 21, 102, 23);
		add(botonAbrirCaja);
		
		botonCerrarCaja = new JButton("Cerrar Caja");
		botonCerrarCaja.setFont(new Font("Tahoma", Font.PLAIN, 10));
		botonCerrarCaja.setBounds(132, 21, 102, 23);
		add(botonCerrarCaja);
		
		botonAgregarProd = new JButton("Agregar");
		botonAgregarProd.setBounds(501, 223, 72, 23);
		botonAgregarProd.setFont(new Font("Tahoma", Font.PLAIN, 10));
		add(botonAgregarProd);
		
		botonIniciarVenta = new JButton("Iniciar Venta");
		botonIniciarVenta.setFont(new Font("Tahoma", Font.PLAIN, 10));
		botonIniciarVenta.setBounds(348, 21, 102, 23);
		add(botonIniciarVenta);
		
		botonCerrarVenta = new JButton("Finalizar Venta");
		botonCerrarVenta.setFont(new Font("Tahoma", Font.PLAIN, 10));
		botonCerrarVenta.setBounds(464, 21, 109, 23);
		add(botonCerrarVenta);
		
		labelProd = new JLabel("Productos");
		labelProd.setFont(new Font("Tahoma", Font.PLAIN, 12));
		labelProd.setBounds(378, 70, 72, 21);
		add(labelProd);
		
		labelFormaPago = new JLabel("Forma de Pago");
		labelFormaPago.setBounds(378, 266, 102, 14);
		labelFormaPago.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(labelFormaPago);
		
		labelProdComprados = new JLabel("Venta");
		labelProdComprados.setFont(new Font("Tahoma", Font.PLAIN, 12));
		labelProdComprados.setBounds(20, 70, 150, 21);
		add(labelProdComprados);
		
		labelDescuentos = new JLabel("Descuentos");
		labelDescuentos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		labelDescuentos.setBounds(20, 226, 89, 21);
		add(labelDescuentos);
		
		spinnerCantidad = new JSpinner(new SpinnerNumberModel(0,0,100,1));
		spinnerCantidad.setBounds(439, 224, 46, 20);
		add(spinnerCantidad);
		
		labelTotal = new JLabel("Total");
		labelTotal.setFont(new Font("Tahoma", Font.BOLD, 12));
		labelTotal.setBounds(378, 355, 46, 14);
		add(labelTotal);
		
		labelTotalVenta = new JLabel();
		labelTotalVenta.setBounds(472, 331, 101, 21);
		labelTotalVenta.setForeground(Color.BLACK);
		labelTotalVenta.setFont(new Font("Tahoma", Font.BOLD, 12));
		//labelTotal.setBorder(border);
		add(labelTotalVenta);
		
		labelUnidades = new JLabel("Unidades");
		labelUnidades.setFont(new Font("Tahoma", Font.PLAIN, 12));
		labelUnidades.setBounds(378, 224, 65, 21);
		add(labelUnidades);

		jrEfectivo = new JRadioButton("Efectivo");
		jrEfectivo.setFont(new Font("Tahoma", Font.PLAIN, 10));
		jrEfectivo.setBounds(378, 287, 72, 20);
		add(jrEfectivo);
		
		jrTarjeta = new JRadioButton("Tarjeta");
		jrTarjeta.setFont(new Font("Tahoma", Font.PLAIN, 10));
		jrTarjeta.setBounds(501, 287, 72, 20);
		add(jrTarjeta);
		
		separador = new JSeparator();
		separador.setBounds(378, 253, 195, 2);
		add(separador);
		
		separator = new JSeparator();
		separator.setBounds(20, 55, 553, 2);
		add(separator);	
		
		tablaProdComp = new JTable();
		tablaProdComp.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] {"Producto", "PrecXuni", "Precio"}
		));
		tablaProdComp.getTableHeader().setReorderingAllowed(false);
		tablaProdComp.getColumnModel().getColumn(0).setPreferredWidth(190);
		tablaProdComp.getColumnModel().getColumn(1).setPreferredWidth(57);
		tablaProdComp.getColumnModel().getColumn(2).setPreferredWidth(47);
		
		panelProdComp = new JScrollPane();
		panelProdComp.setBounds(20, 98, 322, 117);
		panelProdComp.setViewportView(tablaProdComp);
		add(panelProdComp);
		

		tablaDsctos= new JTable();
		tablaDsctos.setModel(new DefaultTableModel(
				new Object[][] {},
				new String[] {"Descripción", "Monto"}
			));
		tablaDsctos.getTableHeader().setReorderingAllowed(false);
		tablaDsctos.getColumnModel().getColumn(0).setPreferredWidth(220);
		tablaDsctos.getColumnModel().getColumn(1).setPreferredWidth(30);
	
		panelDsctos = new JScrollPane();
		panelDsctos.setBounds(20, 252, 322, 117);
		panelDsctos.setViewportView(tablaDsctos);
		add(panelDsctos);
		
		
		labelSubtotal = new JLabel("Subtotal");
		labelSubtotal.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelSubtotal.setBounds(378, 313, 72, 21);
		add(labelSubtotal);
		
		labelDesc = new JLabel("Descuento");
		labelDesc.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelDesc.setBounds(378, 335, 72, 14);
		add(labelDesc);
		

		labelTotalDscto = new JLabel();
		labelTotalDscto.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelTotalDscto.setBounds(471, 335, 102, 14);
		add(labelTotalDscto);
		
		
		labelValorSubtotal = new JLabel();
		labelValorSubtotal.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelValorSubtotal.setBounds(472, 315, 89, 14);
		add(labelValorSubtotal);
	
	}
	



}
