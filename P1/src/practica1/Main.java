package practica1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Main {
	static String abe="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static void main(String[] args) {
		String txt = "La noche cae, brumosa ya y morada.  Vagas claridades malvas y verdes perduran tras la torre de la iglesia.  El camino sube, lleno de sombras, de campanillas, de fragancia de hierba,  de canciones, de cansancio y de anhelo.";
		int long_total = txt.length();
		ArrayList<Integer> frec = new ArrayList<Integer>();
		frec = cuentaSimbolos(long_total,txt);
		ArrayList<Float> probs = new ArrayList<Float>();
		probs = calculaProbabilidades(frec, long_total);
		
		abe = abe + abe.toLowerCase()+"., ";
		ArrayList<Simbolo> kk = new ArrayList<Simbolo>();
		for (int i = 0; i < frec.size(); i++) {
			kk.add(new Simbolo(abe.charAt(i),frec.get(i),probs.get(i)));
			System.out.println(kk.get(i).getSim()+" "+kk.get(i).getFrec()+" "+kk.get(i).getProb());
		}
		
	}
	
	public static ArrayList<Integer> cuentaSimbolos(int long_total, String txt) {
		abe = abe + abe.toLowerCase()+"., ";
		int contador = 0;
		ArrayList<Integer> frec = new ArrayList<Integer>();
		for(int i =0; i < abe.length(); i++ ) { // RECORREMOS ABECEDARIO
			
			for(int j = 0 ; j < long_total;j++) { // recorremos texto
				if(abe.charAt(i)==txt.charAt(j)) {
					contador++;
				}	
			}
			frec.add(contador);
			contador = 0;
			}
		
		return frec;
	}
	
	
	public static ArrayList<Float> calculaProbabilidades(ArrayList<Integer> frec,int long_total){
	
		ArrayList<Float> probs = new ArrayList<Float>();
		float d = 0;
		for (int i = 0; i < frec.size(); i++) {
			d = (float)frec.get(i)/long_total;
			probs.add(d);
			d= 0;
			
		}
		return probs;
	}

	public static void ordena(ArrayList<Simbolo> kk) {
	
		
	}

}
