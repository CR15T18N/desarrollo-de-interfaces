package application;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;

public class Coche {
	
	private int id;
	private SimpleStringProperty marca;
	private SimpleStringProperty modelo;
	private SimpleStringProperty estado;
	private SimpleFloatProperty precio;
	
	public Coche(String marca, String modelo, String estado, float precio) {
		this.marca = new SimpleStringProperty(marca);
		this.modelo = new SimpleStringProperty(modelo);
		this.estado = new SimpleStringProperty(estado);
		this.precio = new SimpleFloatProperty(precio);
	}
	
	public Coche(int id, String marca, String modelo, String estado, float precio) {
		this.id = id;
		this.marca = new SimpleStringProperty(marca);
		this.modelo = new SimpleStringProperty(modelo);
		this.estado = new SimpleStringProperty(estado);
		this.precio = new SimpleFloatProperty(precio);
	}

	public String getMarca() {
		return marca.get();
	}

	public void setMarca(String marca) {
		this.marca = new SimpleStringProperty(marca);
	}

	public String getModelo() {
		return modelo.get();
	}

	public void setModelo(String modelo) {
		this.modelo = new SimpleStringProperty(modelo);
	}

	public String getEstado() {
		return estado.get();
	}

	public void setEstado(String estado) {
		this.estado = new SimpleStringProperty(estado);
	}

	public Float getPrecio() {
		return precio.get();
	}

	public void setPrecio(Float precio) {
		this.precio = new SimpleFloatProperty(precio);
	}

	public int getId() {
		return id;
	}
	
	
}
