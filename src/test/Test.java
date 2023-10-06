package test;

import java.util.Scanner;

import models.Barbero;
import models.ColaClientes;
import models.InsertarClientes;
import models.SillaBarbero;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		long tiempoInicial = System.currentTimeMillis();
		Scanner sc = new Scanner(System.in);
		System.out.println("ingrese el numero de sillas");
		int numeroDeSillas = sc.nextInt();
		SillaBarbero silla = new SillaBarbero();
		ColaClientes cola1 = new ColaClientes("sala de espera",numeroDeSillas);
		Barbero barbero = new Barbero("Diego",cola1,silla, tiempoInicial);
		barbero.start();
		InsertarClientes productor = new InsertarClientes(cola1,tiempoInicial,silla);
		productor.start();
		System.out.println("El estado inicial de la simulacion el barbero duerme");
		for (int i=0;i<10;i++){
		try {
			barbero.join();
			productor.join();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		}
		

	}

}
