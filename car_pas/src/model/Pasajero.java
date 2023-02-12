package model;

import java.util.Objects;

public class Pasajero {
	

	private int id, edad, peso;
	private String nombre;
	
	public Pasajero(int id, String nombre, int edad, int peso) {
		this.id = id;
		this.nombre = nombre;
		this.edad = edad;
		this.peso = peso;
	}
	
	public Pasajero() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}


	@Override
	public String toString() {
		return "Pasajero [id=" + id + ", nombre=" + nombre + ", edad=" + edad + ", peso=" + peso + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Pasajero other = (Pasajero) o;
		return id == other.id &&
				Objects.equals(nombre, other.nombre) &&
				edad == other.edad && 
				peso == other.peso;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nombre, edad, peso);
	}
}


