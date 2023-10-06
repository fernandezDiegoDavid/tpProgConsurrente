package models;

public class Semaphore {

	protected int contador=0;
	
	public Semaphore(int valorInicial){
		this.contador = valorInicial;
	}
	
	synchronized public void Wait(){
		while (contador==0)
			try {
				wait();
			} catch (Exception e) {
				// TODO: handle exception
			}
			contador--;
			
	}
	
	synchronized public void Signal(){
		contador=1;
		notify();
	}
}
