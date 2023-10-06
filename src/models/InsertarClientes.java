package models;



public class InsertarClientes extends Thread {//seria el productor
	
	private ColaClientes cola;//manejaremos los clientes con fifo mediante la cola
	private long tiempoinicial;//simula la hora de llegada
	private SillaBarbero silla;//recurso compartido 
	
	public InsertarClientes(ColaClientes cola,long tiempoInicial,SillaBarbero silla){//constructor
		this.cola = cola;
		this.tiempoinicial = tiempoInicial;
		this.silla=silla;
	}
	
	@Override
	public void run(){//definicion de codigo que se ejecutara concurrentemente
		int id = 1;
	
		while(true){
			try {
				int tiempoLlegada = (int)(Math.random()*2+1);//sera un timepo entre 1 y 2
				Thread.sleep(tiempoLlegada*1000);//seteando el slepp manejamos que cada cuanto genera clientes
				Cliente cliente = new Cliente(id);
				id++;
				System.out.println("llego el cliente: "+cliente.toString()+" a la barberia"+
						" en "+cola.getNombre()+" hay: "+cola.getColaDeClientes().size()+
						" tiempo de llegada: "+(System.currentTimeMillis()-tiempoinicial)/1000+" seg");
				
				if (silla.getSillaBarbero()==null){//si no hay nadie cortandose
					System.out.println("El clienete: "+cliente+" despierta al barbero.");
					
					silla.ocuparSillaBarbero(cliente);
				}else{
					System.out.println("el cliente "+cliente+" se va a la sala de espera");
					
					cola.insertar(cliente);
				}
			} catch (Exception e) {
				System.out.println("error en el try");
			}
		
		}
		
	}

	public ColaClientes getCola() {
		return cola;
	}

	public void setCola(ColaClientes cola) {
		this.cola = cola;
	}

	public long getTiempoinicial() {
		return tiempoinicial;
	}

	public void setTiempoinicial(long tiempoinicial) {
		this.tiempoinicial = tiempoinicial;
	}
	
	

}
