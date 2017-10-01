
public class Transicion {
	private String lee;
	private String escribe;
	private String mueve;
	private int proximoEstado;
	
	public Transicion (String entrada, String escritura, String movimiento, int nuevoEstado) {
		lee = entrada;
		escribe = escritura;
		mueve = movimiento;
		proximoEstado = nuevoEstado;
	}

	public String getLee() {
		return lee;
	}

	public void setLee(String lee) {
		this.lee = lee;
	}

	public int getProximoEstado() {
		return proximoEstado;
	}

	public void setProximoEstado(int proximoEstado) {
		this.proximoEstado = proximoEstado;
	}

	public String getEscribe() {
		return escribe;
	}

	public void setEscribe(String escribe) {
		this.escribe = escribe;
	}

	public String getMueve() {
		return mueve;
	}

	public void setMueve(String mueve) {
		this.mueve = mueve;
	}
	
	public void mostrarTransicion() {
		System.out.println("("+ getLee() + " " + getEscribe() + " " + getMueve() + " " + getProximoEstado() + ")");
	}
}
