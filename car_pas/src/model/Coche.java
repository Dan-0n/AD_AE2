package model;

import java.util.Objects;

public class Coche {
	
	private int id;
	private String marca, matricula, modelo, color;
	
	public Coche(int id, String marca, String matricula, String modelo, String color) {
		this.id = id;
		this.marca = marca;
		this.matricula = matricula;
		this.modelo = modelo;
		this.color = color;
	}
	
	public Coche() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "Coche [id=" + id + ", marca=" + marca + ", matricula=" + matricula + ", modelo=" + modelo + ", color=" + color + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Coche other = (Coche) o;
		return id == other.id &&
				Objects.equals(marca, other.marca) &&
				Objects.equals(matricula, other.matricula) &&
				Objects.equals(modelo, other.modelo) &&
				Objects.equals(color, other.color);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, marca, matricula, modelo, color);
	}
}



