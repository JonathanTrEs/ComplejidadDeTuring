import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
	ArrayList<Estado> estados1 = new ArrayList<Estado>();
	ArrayList<Estado> estados2 = new ArrayList<Estado>();
	ArrayList<ArrayList<Transicion>> tran1 = new ArrayList<ArrayList<Transicion>>();
	ArrayList<ArrayList<Transicion>> tran2 = new ArrayList<ArrayList<Transicion>>();
	int estadoInicial = 0;
	int estadoFinal = 0;
	int posArrayTransiciones1 = 0;
	Cinta cinta1 = new Cinta();
	Cinta cinta2 = new Cinta();
	
	public Main(){}
	
	public void ejecutar(int estadoActual, int i, int j){
		System.out.println("-------------------------------------");
		cinta1.mostrarCinta();
		System.out.println();
		cinta2.mostrarCinta();
		System.out.println("Estado inicial: " + estadoActual);
		System.out.println("Estado final: " + estadoFinal);
		System.out.println("-------------------------------------");
		
		//Comprobacion
		cinta1.setPuntero(i);
		cinta2.setPuntero(i);
		cinta1.mostrarCinta();
		System.out.println();
		cinta2.mostrarCinta();
		
		while(estados1.get(estadoActual).buscarTransicion(cinta1.getElementoCinta(i)) != -1) {
			posArrayTransiciones1 = estados1.get(estadoActual).buscarTransicion(cinta1.getElementoCinta(i)); //lo que lees
			cinta1.modificarCinta(estados1.get(estadoActual).getTransicion(posArrayTransiciones1).getEscribe(), i);
			
			cinta2.modificarCinta(estados2.get(estadoActual).getTransicion(posArrayTransiciones1).getEscribe(), j);
			
			if(estados1.get(estadoActual).getTransicion(posArrayTransiciones1).getMueve().equals("R")) //hacia que lado
				i++;
			else if(estados1.get(estadoActual).getTransicion(posArrayTransiciones1).getMueve().equals("L"))
				i--;
			
			if(estados2.get(estadoActual).getTransicion(posArrayTransiciones1).getMueve().equals("R")) //hacia que lado
				j++;
			else if(estados2.get(estadoActual).getTransicion(posArrayTransiciones1).getMueve().equals("L"))
				j--;
			
			
			estadoActual = estados1.get(estadoActual).getTransicion(posArrayTransiciones1).getProximoEstado(); //nuevo estado
			if(i >= cinta1.getCinta().size()-1) {
				cinta1.getCinta().add("$");
			}
			else if(i<0) {
				cinta1.getCinta().add(0, "$");
				i=0;
			}
			
			cinta1.setPuntero(i);
			System.out.println("=====================================");
			cinta1.mostrarCinta();
			
			if(j >= cinta2.getCinta().size()-1) {
				cinta2.getCinta().add("$");
			}
			else if(j<0) {
				cinta2.getCinta().add(0, "$");
				j=0;
			}
			cinta2.setPuntero(j);
			cinta2.mostrarCinta();
			System.out.println("=====================================");
			
		}	
		if(estadoActual == estadoFinal) {
			System.out.println("Cadena Aceptada");
		}
		else {
			System.out.println("Cadena Rechazada");
		}
	}
	
	public static void main(String[] args) {
		
		Main principal = new Main();
		//Introducir maquina
		try {
			File archivo = new File ("maquina.txt");
			FileReader fr = new FileReader (archivo);
			BufferedReader br = new BufferedReader(fr);
			String linea = new String();
			
			//Leemos el numero de estados
			int nEstados = 0;
			linea = br.readLine();
			nEstados = Integer.parseInt(linea);
			for(int i = 0; i < nEstados; i++) {
				principal.estados1.add(new Estado(i));
			}
			for(int i = 0; i < nEstados; i++) {
				principal.estados2.add(new Estado(i));
			}

			//Leemos el estado inicial
			linea = br.readLine();
			principal.estadoInicial = Integer.parseInt(linea);
			
			//Leemos el estado final
			linea = br.readLine();
			principal.estadoFinal = Integer.parseInt(linea);
			
			
			//Array de transiciones1
			for(int i = 0; i < nEstados; i++) {
				principal.tran1.add(new ArrayList<Transicion>());
			}
			//Array de transiciones2
			for(int i = 0; i < nEstados; i++) {
				principal.tran2.add(new ArrayList<Transicion>());
			}
			
			while (br.ready ()) {
			    linea = br.readLine ();
			    char c[] = linea.toCharArray();
			    if(Integer.parseInt(Character.toString(c[0])) == 1) {
			    	int est = Integer.parseInt(Character.toString(c[1]));
			    	principal.tran1.get(est).add(new Transicion(Character.toString(c[2]),Character.toString(c[3]),
			    			Character.toString(c[4]),  Integer.parseInt(Character.toString(c[5]))));
			    }
			    else {
			    	int est = Integer.parseInt(Character.toString(c[1]));
			    	principal.tran2.get(est).add(new Transicion(Character.toString(c[2]),Character.toString(c[3]),
			    			Character.toString(c[4]),  Integer.parseInt(Character.toString(c[5]))));
			    }
			}
			//añadimos cada transicion a su estado en la maquina 1
			for(int i = 0; i < principal.estados1.size(); i++) {
				principal.estados1.get(i).setTransiciones(principal.tran1.get(i));
			}
			//añadimos cada transicion a su estado en la maquina 2
			for(int i = 0; i < principal.estados2.size(); i++) {
				principal.estados2.get(i).setTransiciones(principal.tran2.get(i));
			}
		    br.close();
		}catch(Exception e) {
			System.out.println("ERROR leer fichero");
		}
		//mostrar maquina
		for(int i = 0; i < principal.estados1.size(); i++) {
			principal.estados1.get(i).mostrarEstado();
			principal.estados2.get(i).mostrarEstado();
		}
		
		//menu
		boolean exit = false;
		int opc = 0;
		int estadoActual = 0;
		Scanner in = new Scanner(System.in);
		while(!exit) {
			estadoActual = principal.estadoInicial;
			
			opc = 0;
			int i = 1;
			int j = 1;
			String aux = new String();
			
			System.out.println("1. Introducir cadena por teclado");
			System.out.println("2. Introducir cadena por fichero");
			System.out.println("3. Salir");
			System.out.print(">_");
			opc = in.nextInt();
			
			if(opc != 3) {
				//Introducir cinta
				if(opc == 1) { //leer por teclado
					System.out.print("Cadena a procesar: ");
					principal.cinta1 = new Cinta(in.next());
					principal.cinta2 = new Cinta("$");
					principal.ejecutar(estadoActual, i, j);
				}
				else { //leer desde fichero
					try {
						File archivo = new File ("cinta.txt");
						FileReader fr = new FileReader (archivo);
						BufferedReader br = new BufferedReader(fr);
						while (br.ready ()) {
							String linea = br.readLine();
							principal.cinta1 = new Cinta(linea);
							principal.cinta2 = new Cinta("$");
							principal.cinta1.mostrarCinta();
							principal.cinta2.mostrarCinta();
							System.out.println("=================================");
							principal.ejecutar(estadoActual, i, j);
							while(!aux.equals("c")) {
								System.out.println("Introduzca c para continuar");
								aux = in.next();
							}
							aux = "";
						}
						br.close();
					}catch(Exception e) {
						System.out.println("ERROR leer fichero maquina.txt");
					}
				}
			}
			else {
				System.out.println("Programa terminado");
				exit = true;
			}
		}
		in.close();
	}
}
