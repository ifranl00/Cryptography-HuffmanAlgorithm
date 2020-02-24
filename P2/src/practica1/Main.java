package practica1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class Main {
	static String abe="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	public static void main(String[] args) {
		String txt = "La noche cae, brumosa ya y morada.  Vagas claridades malvas y verdes perduran tras la torre de la iglesia.  El camino sube, lleno de sombras, de campanillas, de fragancia de hierba,  de canciones, de cansancio y de anhelo.";
		int long_total = txt.length();
		/*
		String aux = "";
		ArrayList<String> txt2 = new ArrayList<String>();
		for (int i = 0; i < long_total; i++) {
			if(i%2==0) {
				aux =  ""+txt.charAt(i);
			}else if(i%2!= 0) {
				aux = aux+""+ txt.charAt(i);
				txt2.add(aux);
				aux = "";
			}
		}*/
		/* ALFABETO FUENTE EJERCICIO EXTRA */
		/*
		ArrayList<String> abe2 = new ArrayList<String>();
		for (int i = 0; i < txt2.size(); i++) {
			if(!abe2.contains(txt2.get(i))) {
				abe2.add(txt2.get(i));
			}
		}
		
		
		int long_total2 = txt2.size(); // longitud del texto
		ArrayList<Integer> frec2 = new ArrayList<Integer>();
		frec2 = cuentaSimbolos(long_total2,txt2,abe2);
		ArrayList<Float> probs2 = new ArrayList<Float>();
		probs2 = calculaProbabilidades(frec2, long_total);
		
		ArrayList<Simbolo> fuente2 = new ArrayList<Simbolo>();
		String s = "";
		for (int i = 0; i < frec2.size(); i++) {
			s = abe2.get(i);
			fuente2.add(new Simbolo(s,frec2.get(i),probs2.get(i)));
		}
		ordena(fuente2);
		for (int i = 0; i < fuente2.size(); i++) {
			System.out.println(fuente2.get(i).getSim()+" "+fuente2.get(i).getFrec()+" "+fuente2.get(i).getProb());
		}
		
		
		*/
		/* EJERCICIOS 1 Y 2*/
		abe = abe + abe.toLowerCase()+"., ";
		ArrayList<Integer> frec = new ArrayList<Integer>();
		frec = cuentaSimbolos(long_total,txt,abe);
		ArrayList<Float> probs = new ArrayList<Float>();
		probs = calculaProbabilidades(frec, long_total);
		
		abe = abe + abe.toLowerCase()+"., ";
		ArrayList<Simbolo> fuente = new ArrayList<Simbolo>();
		String s = "";
		for (int i = 0; i < frec.size(); i++) {
			s = ""+abe.charAt(i);
			fuente.add(new Simbolo(s,frec.get(i),probs.get(i)));
			
		}
		ordena(fuente);
		for (int i = 0; i < fuente.size(); i++) {
			System.out.println(fuente.get(i).getSim()+" "+fuente.get(i).getFrec()+" "+fuente.get(i).getProb());
		}
		
		
	}
	
	/*public static ArrayList<Integer> cuentaSimbolos2(){
		
		ArrayList<String> list_aux = new ArrayList<String>();
		
		return null;
	}*/
	
	
	public static ArrayList<Integer> cuentaSimbolos(int long_total, String txt, String abe) {
		
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
	
	public static ArrayList<Integer> cuentaSimbolos(int long_total, ArrayList<String> txt, ArrayList<String> abe2) {
			
			int contador = 0;
			ArrayList<Integer> frec = new ArrayList<Integer>();
			for(int i =0; i < abe2.size(); i++ ) { // RECORREMOS ABECEDARIO
				
				for(int j = 0 ; j < long_total;j++) { // recorremos texto
					if(abe2.get(i).equals(txt.get(j))) {
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

	public static void ordena(ArrayList<Simbolo> f) { 
		
		Collections.sort(f, new Comparator<Simbolo>() {
			@Override
			public int compare(Simbolo s1, Simbolo s2) {
				return Float.compare(s2.getProb(), s1.getProb());
			}
		});
	}

}
