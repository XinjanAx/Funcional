package com.funcional.deber1;

import com.funcional.lista.Lista;

public class MainD2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Lista<Integer> l1 = Lista.of(1,2,3,4,5,6,7,8);
		System.out.println("L1: "+l1.toString()+"\n");
		System.out.println("Tree(L1)\n"+l1.buildTree());
	}

}
