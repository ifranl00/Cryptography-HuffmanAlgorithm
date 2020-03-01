package practica1;

public class Node {
	private int frecuencia;
	private boolean visited;
	private Node right;
	private Node left;
	private Node son;
	private String codigo; 
	
	/* NODO INICIALIZADO CON FRECUENCIA */
	public Node(int frec) {
		this.frecuencia = frec;
		this.visited = false;
		this.right = null;
		this.left = null;
		this.son = null;
		this.codigo = "";
	}
	/* NODO VACIO */
	public Node() {
		this.frecuencia = 0;
		this.visited = false;
		this.right = null;
		this.left = null;
		this.son = null;
		this.codigo = "";
	}
	
	/* INTRODUCIR FRECUENCIA EN NODO */
	public void setContent(int frec) {
		this.frecuencia = frec;
	}
	
	/* INTRODUCIR NODOS DERECHA E IZQUIERDA EL NODO */
	public void setRight(Node right) {
		this.right = right;
	}
	
	public void setLeft(Node left) {
		this.left = left;
	}
	
	/* CONCATENA LO QUE HAYA PARA IR JUNTANDO CODIGO */
	public void setCode(String code) {
		this.codigo = this.codigo+""+code;
	}
	
	public void setVisited() {
		this.visited = true;
	}
	
	/* GETTERS */
	public Node getRight() {
		return this.right;
	}
	
	public Node getLeft() {
		return this.left;
	}

	public int getContent() {
		return this.frecuencia;
	}
	
	public String getCode() {
		return this.codigo;
	}

	/* BASICO */
	public boolean isEmpty() {
		return (this.frecuencia == 0);
	}
	
	public boolean isLeaf() {
		return (this.left == null && this.right == null);
	}
	
	public boolean wasVisited() {
		return (this.visited == true);
	}
	
	public boolean hasSon() {
		return (this.son != null);
	}
}
