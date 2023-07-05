package com.funcional.deber2.profe;

import com.funcional.bintree.BinTree;
import com.funcional.lista.Lista;

public class MainProf {

	public static void main(String[] args) {

//		BinTree<Integer> n2 = BinTree.of(2);
//		BinTree<Integer> n5 = BinTree.of(5);
//		BinTree<Integer> n1 = BinTree.of(1, n2, n5);
//		System.out.println(n1.size());
//		System.out.println(n1);
		
		Lista<Integer> ls = Lista.of(1, 2, 3, 4, 5, 6, 7, 8);
		
		System.out.println("Lista:"+ls);
		System.out.println("\nTree Chevere =D");
		
		var tree = BinTree.buildTree(ls);
		
		System.out.println("Size: "+tree.size());
		System.out.println(tree);
		

	}

}
