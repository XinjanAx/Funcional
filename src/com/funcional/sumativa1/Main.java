package com.funcional.sumativa1;



import com.funcional.bintree.BinTree;
import com.funcional.lista.Lista;


public class Main<T> {
	
//-----------------------prueba
    static <T> Lista<T> preOrder(BinTree<T> arbol) {   	
    	if(arbol.left()==BinTree.Leaf&&arbol.left()==BinTree.Leaf) {
    		return Lista.of(arbol.value());
    	}	
    	else if(!(arbol.left()==BinTree.Leaf)) {
    		return Lista.of(arbol.value(),preOrder(arbol.left()));
    		}
    				
    	else if(!(arbol.right()==BinTree.Leaf)){    			 
    				return Lista.of(arbol.value(),preOrder(arbol.right()));    				   					
    				}
    	else {
    		return Lista.NIL;   	
    	}
    	
    	
    }

    static <T> String inOrder(BinTree<T> arbol) {
    	return String.format("[%s,%s,%s]", arbol.value(),arbol.left(),arbol.right());
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Lista<Integer> l = Lista.of(1,2,5,9);
		BinTree<Integer> arbol = BinTree.buildTree(l);
		
		System.out.println("arbol:"+arbol);
		
		System.out.println("\nPre-ordeer:"+Main.preOrder(arbol));
		
		arbol.forEach(System.out::print);

	}

}

