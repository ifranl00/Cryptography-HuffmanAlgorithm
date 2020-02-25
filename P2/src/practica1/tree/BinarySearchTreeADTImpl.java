package tree;


import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * Ã�rbol binario de bÃºsqueda (binary search tree, BST).
 * 
 * El cÃ³digo fuente estÃ¡ en UTF-8, y la constante 
 * EMPTY_TREE_MARK definida en AbstractTreeADT del
 * proyecto API deberÃ­a ser el sÃ­mbolo de conjunto vacÃ­o: âˆ…
 * 
 * Si aparecen caracteres "raros", es porque
 * el proyecto no estÃ¡ bien configurado en Eclipse para
 * usar esa codificaciÃ³n de caracteres.
 *
 * En el toString() que estÃ¡ ya implementado en AbstractTreeADT
 * se usa el formato:
 * 
 * 		Un Ã¡rbol vacÃ­o se representa como "âˆ…". Un Ã¡rbol no vacÃ­o
 * 		como "{(informaciÃ³n raÃ­z), sub-Ã¡rbol 1, sub-Ã¡rbol 2, ...}".
 * 
 * 		Por ejemplo, {A, {B, âˆ…, âˆ…}, âˆ…} es un Ã¡rbol binario con 
 * 		raÃ­z "A" y un Ãºnico sub-Ã¡rbol, a su izquierda, con raÃ­z "B".
 * 
 * El mÃ©todo render() tambiÃ©n representa un Ã¡rbol, pero con otro
 * formato; por ejemplo, un Ã¡rbol {M, {E, âˆ…, âˆ…}, {S, âˆ…, âˆ…}} se
 * muestra como:
 * 
 * M
 * |  E
 * |  |  âˆ…
 * |  |  âˆ…
 * |  S
 * |  |  âˆ…
 * |  |  âˆ…
 * 
 * Cualquier nodo puede llevar asociados pares (clave,valor) para
 * adjuntar informaciÃ³n extra. Si es el caso, tanto toString() como
 * render() mostrarÃ¡n los pares asociados a cada nodo.
 * 
 * Con {@link #setTag(String, Object)} se inserta un par (clave,valor)
 * y con {@link #getTag(String)} se consulta.
 * 
 * 
 * Con <T extends Comparable<? super T>> se pide que exista un orden en
 * los elementos. Se necesita para poder comparar elementos al insertar.
 * 
 * Si se usara <T extends Comparable<T>> serÃ­a muy restrictivo; en
 * su lugar se permiten tipos que sean comparables no sÃ³lo con exactamente
 * T sino tambiÃ©n con tipos por encima de T en la herencia.
 * 
 * @param <T>
 *            tipo de la informaciÃ³n en cada nodo, comparable.
 */
public class BinarySearchTreeADTImpl<T extends Comparable<? super T>> extends
		AbstractBinaryTreeADT<T> {

	/**
	 * Devuelve el Ã¡rbol binario de bÃºsqueda izquierdo.
	 */
	protected BinarySearchTreeADTImpl<T> getLeftBST() {
		//	El atributo leftSubtree es de tipo AbstractBinaryTreeADT<T> pero
		//	aquÃ­ se sabe que es ademÃ¡s de bÃºsqueda binario
		//
		return (BinarySearchTreeADTImpl<T>) leftSubtree;
	}

	private void setLeftBST(BinarySearchTreeADTImpl<T> left) {
		this.leftSubtree = left;
	}
	
	/**
	 * Devuelve el Ã¡rbol binario de bÃºsqueda derecho.
	 */
	protected BinarySearchTreeADTImpl<T> getRightBST() {
		return (BinarySearchTreeADTImpl<T>) rightSubtree;
	}

	private void setRightBST(BinarySearchTreeADTImpl<T> right) {
		this.rightSubtree = right;
	}
	
	/**
	 * Ã�rbol BST vacÃ­o
	 */
	public BinarySearchTreeADTImpl() {
		
		setContent(null);
		
		setLeftBST(null);
		setRightBST(null);
	}

	private BinarySearchTreeADTImpl<T> emptyBST() {
		return new BinarySearchTreeADTImpl<T>();
	}
	
	/**
	 * Inserta todos los elementos de una colecciÃ³n en el Ã¡rbol.
	 * 
	 * No se permiten elementos null.
	 * 
	 * @param elements
	 *            valores a insertar.
	 */
	public void insert(Collection<T> elements) {
		//	O todos o ninguno; si alguno es 'null', ni siquiera se comienza a insertar
		boolean isNull = false;
		
		for (T elem : elements) {
			
			if(elem == null) {
				isNull = true;
			}
		}
		
		if(isNull == false) {
			for (T elem2 : elements) {
				this.insert(elem2);
			}
		}
		
	}

	/**
	 * Inserta todos los elementos de un array en el Ã¡rbol.
	 * 
	 * No se permiten elementos null.
	 * 
	 * @param elements elementos a insertar.
	 */
	public void insert(T ... elements) {
		
		boolean isNull = false;
		//	O todos o ninguno; si alguno es 'null', ni siquiera se comienza a insertar
		for (T elem : elements) {
			
			if(elem == null) {
				isNull = true;
			}
		}
		
		if(isNull == false) {
			for (T elem2 : elements) {
				this.insert(elem2);
			}
		}
	}
	
	/**
	 * Inserta de forma recursiva (como hoja) un nuevo elemento en el Ã¡rbol de bÃºsqueda.
	 * 
	 * No se permiten elementos null. Si el elemento ya existe en el Ã¡rbol NO lo inserta.
	 * 
	 * @param element
	 *            valor a insertar.
	 */
	public void insert(T element) {
		//	No se admiten null
		 if(this.isEmpty()) {
			this.emptyBST();
			this.setContent(element);
			this.setLeftBST(emptyBST());
			this.setRightBST(emptyBST());	
		}else {
		
			if(this.getContent().equals(element) == false) {
				
					if(element.compareTo(this.getContent()) > 0) {
						getRightBST().insert(element);
					} else if(element.compareTo(this.getContent()) < 0) {
						getLeftBST().insert(element);
					}
			}
		} 
	}
	
	
	
	/**
	 * Elimina los elementos de la colecciÃ³n del Ã¡rbol.
	 */
	public void withdraw(Collection<T> elements) {
        //		O todos o ninguno; si alguno es 'null', no se eliminarÃ¡ ningÃºn elemento
		boolean isNull = false;
		
		for (T elem : elements) {
			
			if(elem == null) {
				isNull = true;
			}
		}
		
		if(isNull == false) {
			for (T elem2 : elements) {
				withdraw(elem2);
			}
		}
		
	}

	/**
	 * Elimina los valores en un array del Ã¡rbol.
	 */
	public void withdraw(T ... elements) {
	    //		O todos o ninguno; si alguno es 'null', no se eliminarÃ¡ ningÃºn elemento
		
		boolean isNull = false;
		 BinarySearchTreeADTImpl<T> root = this;
		//	O todos o ninguno; si alguno es 'null', ni siquiera se comienza a insertar
		for (T elem : elements) {
			
			if(elem == null) {
				isNull = true;
			}
		}
		
		if(isNull == false) {
			for (T elem2 : elements) {
				
				withdraw(elem2);
				
			}
		}
	}
	
	/**
	 * Elimina un elemento del Ã¡rbol.
	 * 
	 * @throws NoSuchElementException si el elemento a eliminar no estÃ¡ en el Ã¡rbol           
	 */
	public void withdraw(T element) {
		
		
		// 	Si el elemento tiene dos hijos, se tomarÃ¡ el criterio de sustituir el elemento por el mayor de sus menores y eliminar el mayor de los menores.
		if(isEmpty() == true) {
			throw new NoSuchElementException();
		}else{
			if(this.getContent().equals(element) == false) { //buscamos el elemento
				if(element.compareTo(this.getContent()) > 0) {
					getRightBST().withdraw(element);
				} else if(element.compareTo(this.getContent()) < 0) {
					getLeftBST().withdraw(element);
				}
			}else {
			//tres casos:
				
			//sin hijos
				if(this.isLeaf() == true) {
					this.setContent(null);
					this.setLeftBST(null);
					this.setRightBST(null);
				
				//un hijo por la izq	
				}else if(this.isLeaf() == false){
				 if(this.hasSon() == 10) {
					this.setContent(getLeftBST().getContent());//copio el content del nodo izq	
					this.setRightBST(getLeftBST().getRightBST());
					this.setLeftBST(getLeftBST().getLeftBST());
					
				//un hijo por la dcha	
				 }else if(this.hasSon() == 01) {
					 this.setContent(getRightBST().getContent());
					 this.setLeftBST(getRightBST().getLeftBST());
					this.setRightBST(getRightBST().getRightBST());
					
				 }else {
					 //dos hijos
					 //mayor de los menores
					 BinarySearchTreeADTImpl<T> aux = this.getLeftBST();
					 while(aux.rightSubtree.isEmpty() == false) {
						 aux = aux.getRightBST();
					 }
					 this.content = aux.content;
					 this.getLeftBST().withdraw(aux.content);
					 
				 }
	
				}
			}
		}
	
	}
	
	private int hasSon() {
		int hasSon = 0;
		
		if(this.getLeftBST().isEmpty() == false && this.getRightBST().isEmpty() == true){ //tiene uno a la izq y no a la dcha
			
			hasSon = 10;
			
		}else if(this.getRightBST().isEmpty() == false && this.getLeftBST().isEmpty() == true){// tiene uno a la dcha y no la izq
			
			hasSon = 01;
		}
		else {//tiene dos hijos
			
			hasSon = 11;
		}
			
		
		return hasSon;
	}
	
	
	/**
	 * Devuelve el sub-Ã¡rbol indicado. (para tests)
	 * path serÃ¡ el camino para obtener el sub-arbol. EstÃ¡ formado por 0 y 1.
	 * Si se codifica "bajar por la izquierda" como "0" y
	 * "bajar por la derecha" como "1", el camino desde un 
	 * nodo N hasta un nodo M (en uno de sus sub-Ã¡rboles) serÃ¡ la
	 * cadena de 0s y 1s que indica cÃ³mo llegar desde N hasta M.
     *
     * Se define tambiÃ©n el camino vacÃ­o desde un nodo N hasta
     * Ã©l mismo, como cadena vacÃ­a.
	 * 
	 * Si el subarbol no existe lanzarÃ¡ la excepciÃ³n NoSuchElementException.
	 * 
	 * @param path
	 * @return
	 * @throws NoSuchElementException si el subarbol no existe
	 */
	public BinarySearchTreeADTImpl<T> getSubtreeWithPath(String path) {
		//charAt, ir haciendo la llamada rec (se puede ir borrando el path cada vez que pasa y si se encuentra un nodo vacio que lance la exception
		BinarySearchTreeADTImpl<T> node;
		int counter = 0;
		if(this.isEmpty() == true) {
			
			throw new NoSuchElementException();
			
		}else if(path == "" || path == null){
			
			node = this;
			
		}else {
			
			node  = getSubtreeWithPath(path, counter, path.length());
			
		}
		return node;
		
	}
	
	
	private BinarySearchTreeADTImpl<T> getSubtreeWithPath(String path, int counter, int length){
		int i = 0;
		
		if(this.isEmpty() == true) {
			
			throw new NoSuchElementException();
			
		}else if(length > counter && this.content != null) {
			
			if(path.charAt(counter) == '0') {
				length = length -1;
				return getLeftBST().getSubtreeWithPath(path.substring(i+1, length+1), counter, length);
			}else {
				length = length -1;
				return getRightBST().getSubtreeWithPath(path.substring(i+1, length+1), counter, length);
			}
					
		}else {
			return this;
		}
		
	}
	
	
	
	/**
	 * Acumula en orden descendente, una lista con los pares 'padre-hijo' en este Ã¡rbol.
	 * 
	 * Por ejemplo, sea un Ã¡rbol "A":
	 * 
	 * {10, {5, {2, âˆ…, âˆ…}, âˆ…}, {20, âˆ…, {30, âˆ…, âˆ…}}}
	 * 
     * 10
     * |  5
     * |  |  2
     * |  |  |  âˆ…
     * |  |  |  âˆ…
     * |  |  âˆ…
     * |  20
     * |  |  âˆ…
     * |  |  30
     * |  |  |  âˆ…
     * |  |  |  âˆ…
     * 
	 * el resultado serÃ­a una lista de cadenas:
	 * 
	 * 	[(20,30), (10,20), (10,5), (5,2)]
	 * 
	 * y ademÃ¡s quedarÃ­a etiquetado como:
	 * 
	 *  {10 [(descend, 3)], 
	 *       {5 [(descend, 4)], {2 [(descend, 5)], âˆ…, âˆ…}, âˆ…}, 
	 *       {20 [(descend, 2)], âˆ…, {30 [(descend, 1)], âˆ…, âˆ…}}}
	 * 
	 * @param buffer lista con el resultado.
	 */
	public void parentChildPairsTagDescend(List<String> buffer) {
	
		int[] pos = {1};
		
		
			parentChildPairsTagDescendRec(buffer,  pos);
		
	}
	
	private void parentChildPairsTagDescendRec(List<String> buffer, int[] pos) {
		
		if(this.isEmpty() == false) {
			
			
			
				this.getRightBST().parentChildPairsTagDescendRec(buffer, pos);
			
				this.setTag("descend",pos[0]);
				pos[0]++;
			
				if(this.getRightBST().isEmpty() == false) {
				
					buffer.add("("+this.content+", "+this.getRightBST().content+")");
				
				}
			
				if(this.getLeftBST().isEmpty() == false) {
			
					buffer.add("("+this.content+", "+this.getLeftBST().content+")");
				}
			
				this.getLeftBST().parentChildPairsTagDescendRec(buffer, pos);
			
			}
		
	
	}
		
	
	
	/**
	 * Importante: Solamente se debe recorrer el Ã¡rbol una vez
	 * 
	 * Comprueba si los elementos de la lista coinciden con algÃºn camino desde la raiz.
	 * AdemÃ¡s, si existe algÃºn camino que coincida con los elementos de la lista, los etiqueta en el Ã¡rbol,
	 * numerandolos empezando por la raiz como 1.
	 * 
	 * Por ejemplo, el Ã¡rbol
	 * 
	 * {50, {30, {10, âˆ…, âˆ…}, {40, âˆ…, âˆ…}}, {80, {60, âˆ…, âˆ…}, âˆ…}}
	 * 
	 * si path = [50, 30, 10]
	 * 
	 * devolverÃ­a true y el Ã¡rbol quedarÃ­a asÃ­ etiquetado:
	 * 
	 * {50 [(path, 1)], {30 [(path, 2)], {10 [(path, 3)], âˆ…, âˆ…}, {40, âˆ…, âˆ…}}, {80, {60, âˆ…, âˆ…}, âˆ…}}
	 * 
	 * Para el mismo Ã¡rbol, si path es [50, 40]  devolverÃ­a false y el Ã¡rbol no se etiqueta:
	 * {50, {30, {10, âˆ…, âˆ…}, {40, âˆ…, âˆ…}}, {80, {60, âˆ…, âˆ…}, âˆ…}}
	 *
	 * Para el mismo Ã¡rbol, si path es [50, 80]  devolverÃ­a true y el Ã¡rbol quedarÃ­a asÃ­ etiquetado:
	 *
	 * {50 [(path, 1)], {30, {10, âˆ…, âˆ…}, {40, âˆ…, âˆ…}}, {80 [(path, 2)], {60, âˆ…, âˆ…}, âˆ…}}

 
	 * 
	 * @return  true si los elementos de la lista coinciden con algÃºn camino desde la raiz,  falso si no es asÃ­
	 */
	public boolean isPathIn(List<T> path) {
		boolean isPath = false;
		
		if(this.isEmpty() == false && isPath == false) {
			int[] count = {1};
			T elem = null;
			if(path.get(0).compareTo(this.content) == 0) {
				
				
					
					elem = path.get(0);
					path.remove(0);
					count[0]++;
					
				if(path.size() == 0) {
					this.setTag("path", count[0]-1);
					count[0]++;
					return true;
				}else {
					
					if(path.get(0).compareTo(elem) < 0) {
						
						isPath = this.getLeftBST().isPathInRec(path, count);
						
					}else if(path.get(0).compareTo(elem)> 0) {
						
						isPath = this.getRightBST().isPathInRec(path, count);
						
					}
				}
				
				if(isPath == true) {
					count[0]--;
					this.setTag("path", count[0]);
				}
				
			}else {
				return false;
			}
		}
		return isPath;
	}
	
	private boolean isPathInRec( List<T> path, int [] count) {
		boolean isPath = false;

		if(path.size() != 0) {
			
			if(path.get(0).compareTo(this.content) == 0) {
			
				path.remove(0);
				
				if(path.size() == 0) {
					isPath = true;
					this.setTag("path", count[0]);
					return true;
				}
				count[0]++;
				
				if(path.get(0).compareTo(this.content) < 0) {
					
					isPath = this.getLeftBST().isPathInRec(path, count);
				
				}else if(path.get(0).compareTo(this.content) > 0) {
					
					isPath = this.getRightBST().isPathInRec(path, count);
					
				} 
				
				
			}else { //cuando el elemento es diferente, no hay camino
				return false;
			}
		} 
		
		if(isPath == true) {

			count[0]--;
			this.setTag("path", count[0]);
		}
		
		return isPath;
	}

	
	/**
	 * 
	 * Etiqueta cada nodo con su posiciÃ³n en el recorrido en anchura, con la etiqueta "width"
	 * 
	 *  Por ejemplo, el Ã¡rbol
	 * 
	 * {50, {30, {10, âˆ…, âˆ…}, {40, âˆ…, âˆ…}}, {80, {60, âˆ…, âˆ…}, âˆ…}}
	 * 
	 *  queda etiquetado como 
	 * 
	 *   {50 [(width, 1)], 
                 {30 [(width, 2)], {10 [(width, 4)], âˆ…, âˆ…},{40 [(width, 5)], âˆ…, âˆ…}},
                 {80 [(width, 3)], {60 [(width, 6)], âˆ…, âˆ…}, âˆ…}}
	 * 

	 */	
	public void tagWidth(){
		
		LinkedList<BinarySearchTreeADTImpl<T>> cola = new LinkedList<BinarySearchTreeADTImpl<T>>();
		int count = 1;
		BinarySearchTreeADTImpl<T> elem;
		
		if(this.isEmpty() == false) {
			
			cola.add(this);
			
			while(cola.isEmpty() == false) {
				elem = cola.get(0);
	
				cola.removeFirst();
				elem.setTag("width", count++);
				
				if(elem.getLeftBST().isEmpty() == false) {	
					
					cola.addLast(elem.getLeftBST());
				}	
				if(elem.getRightBST().isEmpty() == false) {	
					cola.addLast(elem.getRightBST());
				}
				
			}
			
		}
	}
	
	
	/**	
	 * Devuelve un iterador que recorre los elementos del arbol en inorden (de menor a mayor)
	 * 
	 * Por ejemplo, con el Ã¡rbol
	 * 
	 * 		{50, {30, {10, âˆ…, âˆ…}, {40, âˆ…, âˆ…}}, {80, {60, âˆ…, âˆ…}, âˆ…}}
	 * 
	 * y devolverÃ­a el iterador que recorrerÃ­a los nodos en el orden: 10, 30, 40, 50, 60, 80
	 * 
	 * 		
	 * 
	 * @return iterador para el recorrido inorden o ascendente
	 */
	public Iterator<T> iteratorInorden() {
			return new iteratorInorden(this);
	}	
	
	
	private class iteratorInorden implements Iterator<T> {


		Stack<BinarySearchTreeADTImpl<T>> pila;
		
		public iteratorInorden(BinarySearchTreeADTImpl<T> npadre) {
			
			pila = new Stack<BinarySearchTreeADTImpl<T>>();
			while (npadre.isEmpty()== false) {
				pila.push(npadre);
				npadre = npadre.getLeftBST();
			}
			
		}
		
		@Override
		public boolean hasNext() {
			
			return pila.isEmpty() == false;
		
		}
		
		@Override
		public T next() {
			
			if(hasNext() == false) {
				throw new NoSuchElementException();
			}else {
				BinarySearchTreeADTImpl<T> auxNode = pila.pop();
				
				T element = auxNode.content;
			
				if (auxNode.getRightBST().isEmpty() == false) {
					auxNode = auxNode.getRightBST();
					
					while (auxNode.isEmpty() == false) {
					
						pila.push(auxNode);
						auxNode = auxNode.getLeftBST();
					}
				}
			
			return element;
			}
		}
	
		@Override  
		public void remove() {  
		 
			throw new UnsupportedOperationException();  
	   
		} 
	};
	
}

	

