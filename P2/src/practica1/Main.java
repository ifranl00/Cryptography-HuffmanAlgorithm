package practica1;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;


public class Main {
	static String abe="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	public static void main(String[] args) {
		abe = abe + abe.toLowerCase()+"., ";
		
		/* CAMBIAR DIRECCION DEPENDIENDO DEL PC */
		String texto = leeTexto("/home/dona/eclipse-workspace/P2/src/textos/datos_02_texto.txt");

		ArrayList<Integer> frec = cuentaSimbolos(texto.length(),texto,abe);
		ArrayList<Float> prob = calculaProbabilidades(frec, texto.length());
		ArrayList<Simbolo> fuente = new ArrayList<Simbolo>();
		ArrayList<Node> arbol = new ArrayList<Node>(); 
		
		
		
		String s = "";
		for (int i = 0; i < frec.size(); i++) {
			s = ""+abe.charAt(i);
			fuente.add(new Simbolo(s,frec.get(i),prob.get(i)));
			
		}
		ordena(fuente);
		
		System.out.println("S/ F/ P");
		for (int i = 0; i < fuente.size(); i++) {
			System.out.println(fuente.get(i).getSim()+" "+fuente.get(i).getFrec()+" "+fuente.get(i).getProb());
		}
		
		System.out.println("\nCALCULOS:");
		double entropia = calculaEntropia(fuente);
		System.out.println("Entropia: "+entropia);
		
		arbol = initialTree(fuente);
		construyeHuffmann(arbol, sumaTotalFrec(fuente));
 		
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
	
	public static ArrayList<Node> initialTree(ArrayList<Simbolo> f) {
		ArrayList<Node> ini = new ArrayList<Node>();
		Node node = null;
		for (int i = 0; i < f.size(); i++) {
			if(f.get(i).getFrec() != 0) {
				node = new Node();
				node.setContent(f.get(i).getFrec());
				ini.add(node);
			}
		}
		
		return ini;
	}
	/*
	 * 1) ENCONTRAR 2 MAS PEQUEÑOS
	 * 2) SUMA Y NODO NUEVO
	 * 3) REPETIR HASTA QUE SE ALCANCE EL NUMERO TOTAL DE LA
	 * SUMA DE LAS FRECUENCIAS*/
	
	public static void construyeHuffmann(ArrayList<Node> arbolInicial, int totalFrec) {
		Node aux = new Node();
		Node aux2 = new Node();
		while(aux.getContent() < totalFrec) {
			aux = escogerMenor(arbolInicial);
			aux2 = escogerMenor(arbolInicial);
			
			crearNodoIntermedio((aux.getContent()+aux2.getContent()), aux, aux2);
		}
	}
	
	private static void crearNodoIntermedio(int frec, Node r, Node l) {
		Node u = new Node(frec);
		u.setLeft(l);
		u.setRight(r);
		u.getLeft().setCode("0");
		u.getRight().setCode("1");
	}
	
	private static Node escogerMenor(ArrayList<Node> arbol) {
		Node min = arbol.get(0);
		boolean found  = false;
		int i = 0;
		while(i < arbol.size() && found == false) {
			
		}
			
			/* ESTO CONSIGUE EL MINIMO DEL ARRAY PERO CLARO
			 * HACE FALTA MIRAR LOS NODOS DE ABAJO Y ME CAGO EN MIS PUTOS MUERTOS
			if(min.getContent() > arbol.get(i).getContent()) {
				if(!arbol.get(i).wasVisited()) {
					min = arbol.get(i);
				}	
			}*/	
		
		min.setVisited();
		return min;
	}

}
