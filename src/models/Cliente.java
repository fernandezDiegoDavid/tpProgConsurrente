package models;

public class Cliente {//el elemento producido

	private int nombre;

	public Cliente(int nombre) {
		super();
		this.nombre = nombre;
	}

	public int getNombre() {
		return nombre;
	}

	public void setNombre(int nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ""+this.nombre;
	}
	
	
	
}
