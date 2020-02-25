package practica1;

public class Node {
	int frecuencia;
	boolean visited;
	Node right;
	Node left;
	
	public Node(int frec) {
		this.frecuencia = frec;
		this.visited = false;
		this.right = new Node();
		this.left = new Node();
	}
	/* NODO VACIO */
	public Node() {
		this.visited = false;
		this.right = new Node();
		this.left = new Node();
	}
	
	public void setContent(int frec) {
		this.frecuencia = frec;
	}

}
