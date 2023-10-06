package models;

import java.util.ArrayList;

public class ColaClientes {//este seria mi buffer en productor consumidor

	private ArrayList<Cliente> colaDeClientes;//representaria el buffer,recurso compartido
	private String nombre; //se le asignara un nombre para identificarlo
	private int numeroSillas; //cantidad de espacio dispocnible en el buffer
	
	public ColaClientes(String nombre, int numeroSillas){//constructor de la clase
		this.nombre = nombre;//setea atributos
		this.numeroSillas = numeroSillas;
		this.colaDeClientes = new ArrayList<Cliente>();//inicializa la lista
	}
	
	public ColaClientes() {
		super();
	}

	

	public synchronized ArrayList<Cliente> getColaDeClientes() {
		return colaDeClientes;
	}

	public synchronized void setColaDeClientes(ArrayList<Cliente> colaDeClientes) {
		this.colaDeClientes = colaDeClientes;
	}

	public synchronized String getNombre() {
		return nombre;
	}

	public synchronized void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public synchronized int getNumeroSillas() {
		return numeroSillas;
	}

	public synchronized void setNumeroSillas(int numeroSillas) {
		this.numeroSillas = numeroSillas;
	}

	public synchronized void insertar (Cliente cliente){//metodo sincronizado para asegurar la exclusion mutua
		if (colaDeClientes.size()!= numeroSillas){//verifico que haya espacio en la cola
			System.out.println("****Clientes que esperan en la cola: ");
			colaDeClientes.add(cliente);//agrego el nuevo cliente
			for (Cliente c : colaDeClientes) {// de ser asi,muestro los clientes en espera
				System.out.println(c.toString());
				}
			System.out.println("**********************************");
			this.notify();//desbloque un hilo en espera pasandolo al estado de listo
		}else {
			//en caso de no habre sillas libres el cliente se va 
			System.out.println("cliente "+cliente.toString()+" se fue por que no habia sillas");
		}
		/*este metodo sera utilizado por el consumidor en este caso insertarClientes
		  */
		
	}
	
	public synchronized Cliente extraer(){//este metodo lo utilzara el consumidor en este caso barbero
		if (colaDeClientes.isEmpty()){//si la cola esta vacia es decir no hay clienes
			try {
				System.out.println("no hay clientes en la cola de espera");
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("error al ejecutar el this.wait");
			}
		}
		Cliente cli=null;
		if(colaDeClientes.size()> 0){
			cli = colaDeClientes.get(0);
			colaDeClientes.remove(0);
			//si la cola tiene clientes atiende y lo borra
			//el barbero siempre tomara al elemento 0 de la cola 
			//el primero en llegar el primero en salir,estrctura tipo fifo
			
		}
		return cli;
		
	}
}
