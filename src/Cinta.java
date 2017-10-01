import java.util.ArrayList;


public class Cinta {
	private ArrayList<String> cinta = new ArrayList<String>();
	private int puntero = 0;
	
	public Cinta(){}
	
	public Cinta(String entrada) {
		setCinta(entrada);
	}
	
	public void setPuntero(int p) {
		puntero = p;
	}
	
	public int getPuntero() {
		return puntero;
	}
	
	public ArrayList<String> getCinta() {
		return cinta;
	}
	
	public void setCinta(String entrada) {
		cinta.add("$");
		char c[] = entrada.toCharArray();
		for(int i = 0; i < c.length; i++) {
			cinta.add(Character.toString(c[i]));
		}
		cinta.add("$");
	}
	
	public void modificarCinta(String caracter, int pos) {
		cinta.set(pos, caracter);
	}
	
	public String getElementoCinta(int pos) {
		return cinta.get(pos);
	}
	
	public void mostrarCinta() {
		for(int i = 0; i < cinta.size(); i++) {
			System.out.print(cinta.get(i) + " ");
		}
		System.out.println();
		for(int j = 0; j < cinta.size(); j++) {
			if(j == puntero)
				System.out.print("╝");
			else
				System.out.print("  ");
		}
		System.out.println();
	}

}
