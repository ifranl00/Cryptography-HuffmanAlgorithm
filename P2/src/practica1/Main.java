package practica1;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import tree.BinarySearchTreeADTImpl;

public class Main {
	static String abe="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	public static void main(String[] args) {
		abe = abe + abe.toLowerCase()+"., ";
		
		String texto = leeTexto("C:\\Users\\alumno\\Desktop\\P2\\textos\\datos_02_texto.txt");

		ArrayList<Integer> frec = cuentaSimbolos(texto.length(),texto,abe);
		ArrayList<Float> prob = calculaProbabilidades(frec, texto.length());
		ArrayList<Simbolo> fuente = new ArrayList<Simbolo>();
		
		
		
		String s = "";
		for (int i = 0; i < frec.size(); i++) {
			s = ""+abe.charAt(i);
			fuente.add(new Simbolo(s,frec.get(i),prob.get(i)));
			
		}
		ordena(fuente);
		for (int i = 0; i < fuente.size(); i++) {
			System.out.println(fuente.get(i).getSim()+" "+fuente.get(i).getFrec()+" "+fuente.get(i).getProb());
		}
		double entropia = calculaEntropia(fuente);
		System.out.println("Entropia: "+entropia);
		
		initialTree(fuente);
 		
	}
	
	
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
	
	public static String leeTexto(String dir) {
		String texto="";
		try {
			BufferedReader r = new BufferedReader(new FileReader(dir));
			String tmp ="";
			String bfread;
			
			while((bfread = r.readLine())!=null) {
			
				tmp = tmp+bfread+"  ";
			}
			
			tmp = tmp.substring(0, tmp.length()-2);
			texto = tmp;
			
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
		return texto;
	}
	
	public static double calculaEntropia(ArrayList<Simbolo> fuente) {
		double e = 0;//ENTROPIA
		double totalFrec =(double) sumaTotalFrec(fuente);
		double sumatorio = 0;
		for (int i = 0; i < fuente.size(); i++) {
			if(fuente.get(i).getFrec()!=0) {
			sumatorio = sumatorio + (fuente.get(i).getFrec()*logaritmo(fuente.get(i).getFrec()));
			}
		}
		e = e + (Math.log(totalFrec)/Math.log((double)2))-(1/totalFrec)*sumatorio; 
		return e;
	}
	
	public static int sumaTotalFrec(ArrayList<Simbolo> fuente) {
		int total = 0;
		for (int i = 0; i < fuente.size(); i++) {
			total = total + fuente.get(i).getFrec();
		}
		return total;
	}
	
	public static double logaritmo(int f) {
		return (Math.log(f)/Math.log(2));
	}
	
	public static void initialTree(ArrayList<Simbolo> f) {
		ArrayList<BinarySearchTreeADTImpl<Integer>> ini = new ArrayList<BinarySearchTreeADTImpl<Integer>>();
		BinarySearchTreeADTImpl<Integer> node = null;
		for (int i = 0; i < f.size(); i++) {
			if(f.get(i).getFrec() != 0) {
				node = new BinarySearchTreeADTImpl<Integer>();
				node.setContent(f.get(i).getFrec());
				ini.add(node);
			}
		}
		
		for (int i = 0; i < ini.size(); i++) {
			System.out.println(ini.get(i).toString());
		}
	}

}
