package gui;

public class Usuario {
	
	private String nombre;
	private String apellido;
	private int edad;
	private int id;

	
	public Usuario(int id, String nombre, String apellido, int edad) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
	}
	
	public Usuario(String nombre, String apellido, int edad) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public int getId() {
		return this.id;
	}
	
	public String toString() {
		return this.nombre + " " + this.apellido + " " + this.edad;
	}
	
	public boolean isSet() {
		return this.nombre != "" && this.apellido != "" && this.edad != 0;
	}
	


}
