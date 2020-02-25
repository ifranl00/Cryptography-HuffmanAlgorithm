package tree;

import java.util.List;

/**
 * Ã�rbol binario.
 * 
 * @author profesor
 *
 * @param <T>
 */
public abstract class AbstractBinaryTreeADT<T> extends AbstractTreeADT<T> {
	
	//	Como Ã¡rbol binario, tiene dos sub-Ã¡rboles binarios
	//	"izquierdo" y "derecho"
	//
	//	PodrÃ­an ser vacÃ­os
	protected AbstractBinaryTreeADT<T> leftSubtree;
	protected AbstractBinaryTreeADT<T> rightSubtree;
	
	@Override
	public int getMaxDegree() {
		return 2;
	}

	@Override
	public TreeADT<T> getSubtree(int n) {
		//	El sub-Ã¡rbol izquierdo es el "0"
		switch (n) {
		case 0:
			return leftSubtree;
		case 1:
			return rightSubtree;
		}
		
		throw new IllegalStateException("getSubtree(n) on a binary tree needs n in {0,1}");
	}

	/**
	 * BÃºsqueda en un Ã¡rbol, no necesariamente binario de bÃºsqueda.
	 * 
	 * @param element el elemento a buscar
	 * @param elementsChecked la lista de elementos comprobados durante la bÃºsqueda
	 * @return <tt>true</tt> si el Ã¡rbol contiene al elemento dado
	 */
	public boolean findInBinaryTree(T element, List<T> elementsChecked) {
		
		if (! isEmpty()) {
			//	Comprueba el valor en la raÃ­z del sub-Ã¡rbol actual
			elementsChecked.add(getContent());
			
			if (getContent().equals(element)) {
				//	El sub-Ã¡rbol actual contiene el elemento
				return true;
			} else {
				//	Hay que seguir buscando en sub-Ã¡rboles
				if (leftSubtree.findInBinaryTree(element, elementsChecked)) {
					return true;
				} else {
					//	En la rama izquierda no estÃ¡, quizÃ¡ en la derecha
					return rightSubtree.findInBinaryTree(element, elementsChecked);
				}
			}
		} else {
			//	No estÃ¡ en un Ã¡rbol vacÃ­o
			return false;
		}
	}
	
}
