import java.util.ArrayList;


public class Estado {
	private int estado;
	private ArrayList<Transicion> transiciones = new ArrayList<Transicion>();
	
	public Estado (int numero) {
		estado = numero;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public ArrayList<Transicion> getTransiciones() {
		return transiciones;
	}
	
	public Transicion getTransicion(int pos) {
		return transiciones.get(pos);
	}

	public void setTransiciones(ArrayList<Transicion> transiciones) {
		this.transiciones = transiciones;
	}
	
	public int buscarTransicion(String lectura) {
		int i = 0;
		boolean salir = false;
		while(!salir && i < transiciones.size()) {
			if(transiciones.get(i).getLee().equals(lectura)) {
				salir = true;
			}
			i++;
		}
		if(salir == false) {
			return -1;
		}
		else {
			return i-1;
		}
	}
	
	public void mostrarEstado(int i) {
		transiciones.get(i).mostrarTransicion();
	}
	
	public void mostrarEstado() {
		System.out.println("Estado " + estado);
		for(int i  = 0; i < transiciones.size(); i++) {
			transiciones.get(i).mostrarTransicion();
		}
	}
}