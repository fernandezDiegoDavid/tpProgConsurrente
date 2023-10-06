package models;

public class Barbero extends Thread {//seria el consumidor

	private String nombre;//atributo que identfica al barbero en caso de querer tener mas 
	private ColaClientes cola;
	private long tiempoInicial;
	private SillaBarbero silla;

	
	public Barbero(String nombre, ColaClientes cola ,SillaBarbero silla, long tiempoInicial){
		this.nombre = nombre;
		this.tiempoInicial = tiempoInicial;
		this.cola = cola;
		this.silla = silla;
	}
	
	public void run(){
		while (true){

			try {

				int tiempoDeAtencion = (int)(Math.random()*3+3);
				Thread.sleep(tiempoDeAtencion*1000);


				silla.extraerdesilla();
				System.out.println("el barbero: "+this.nombre+" comienza a atender a "+ 
						silla.getSillaBarbero()+" en: "+cola.getNombre()+
						" hay "+(cola.getColaDeClientes().size())+" personas esperando");

				System.out.println("el barbero "+this.nombre+" atendio al cliente "+
						silla.getSillaBarbero()+" tiempo de atencion: "+tiempoDeAtencion+" seg "+
						"tiempo en que termino la atencion:"+(System.currentTimeMillis()-tiempoInicial)/1000+" seg");

				silla.setSillaBarbero(cola.extraer());
				if (silla.getSillaBarbero()!=null){
					System.out.println("el cliente en la silla ahora es: "+silla.getSillaBarbero());
				}
				



			}catch (Exception e) {
				// TODO: handle exception
			}


		}

	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.nombre;
	}
	
	
	
	
	
}
