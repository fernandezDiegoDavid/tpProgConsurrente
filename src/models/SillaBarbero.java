package models;

public class SillaBarbero {

	private Cliente sillaBarbero;
	
	public SillaBarbero(Cliente cliente){
		this.sillaBarbero=cliente;
		
	}
	public SillaBarbero(){
		
	}
	
	
	
	public synchronized Cliente getSillaBarbero() {
		return sillaBarbero;
	}
	public synchronized void setSillaBarbero(Cliente sillaBarbero) {
		this.sillaBarbero = sillaBarbero;
	}
	public synchronized Cliente extraerdesilla(){

		while(sillaBarbero==null){
			try {
				System.out.println("el barbero duerme por no haber clientes");
				this.wait();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return sillaBarbero;
	}
	
	public synchronized void ocuparSillaBarbero(Cliente sillaBarbero){
		this.sillaBarbero = sillaBarbero;
		System.out.println("el cliente en la silla es: "+this.sillaBarbero);
		System.out.println("cliente listo");
		this.notifyAll();
		
	}
	
	
}
