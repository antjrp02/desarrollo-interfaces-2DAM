package application;

import java.sql.Date;
import java.text.SimpleDateFormat;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Producto {
	private SimpleStringProperty nombre;
	private SimpleFloatProperty precio;
	private SimpleStringProperty categoria;
	private SimpleStringProperty fecha;
	private int id;
	
	public Producto(String nombre, float precio, String categoria,
			String fecha, int id) {
		super();
		this.id = id;
		this.nombre = new SimpleStringProperty(nombre);
		this.precio = new SimpleFloatProperty(precio);
		this.categoria =  new SimpleStringProperty(categoria);
		this.fecha = new SimpleStringProperty(fecha);
		
	}

	
	
	public Producto(String nombre, Float precio, String categoria,
			String fecha) {
		super();
		this.nombre = new SimpleStringProperty(nombre);
		this.precio = new SimpleFloatProperty(precio);
		this.categoria =  new SimpleStringProperty(categoria);
		this.fecha = new SimpleStringProperty(fecha);
	}



	public String getNombre() {
		return nombre.get();
	}
	public void setNombre(String nombre) {
		this.nombre =  new SimpleStringProperty(nombre);
	}
	public Float getPrecio() {
		return precio.get();
	}
	public void setPrecio(float precio) {
		this.precio = new SimpleFloatProperty(precio);
	}

	public String getCategoria() {
		return categoria.get();
	}

	public void setCategoria(String categoria) {
		this.categoria =  new SimpleStringProperty(categoria);
	}

	public String getFecha() {
		return fecha.get();
	}

	public void setFecha(String fecha) {
		this.fecha = new SimpleStringProperty(fecha);
	}

	public int getId() {
		return id;
	}

	
	
}
