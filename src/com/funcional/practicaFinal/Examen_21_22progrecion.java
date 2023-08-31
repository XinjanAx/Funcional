package com.funcional.practicaFinal;

import com.funcional.lista.Lista;


public class Examen_21_22progrecion {
	
	record Tupla<T> (T x , T y){
		
		public String toString2() {
			return  x +"\n"+ y;
		}	
		public String toString3() {
			return  String.format("(Incice:%s, Min:%s)", y,x);
		}	
	}
//-----------------------------------------1-----------------------------------------------	
	static Lista<Integer> progreRecur(Integer v0,Integer n,Integer cons){
		if(n==0) {
			return Lista.NIL;
		}else
			return Lista.of(v0,progreRecur(v0+cons,n-1, cons));
	}
	static Lista<Integer> progreTailRec(Integer v0,Integer n,Integer cons){
		return progreTailRecAux(Lista.NIL, v0, n, cons);
	}
	
	private static Lista<Integer> progreTailRecAux(Lista<Integer> ls,Integer v0,Integer n,Integer cons){
		if (n==0) {
			return ls.invertir();
		}else {
			return progreTailRecAux(ls.prepend(v0), v0+cons, n-1, cons);
		}
	}
//-----------------------------------------2-----------------------------------------------	
	static <T> Tupla<Lista<T>> subListas(Lista<T>ls,Integer n){
		return subListasAux(ls,n,new Tupla<Lista<T>>(Lista.NIL, Lista.NIL));
	}

	private static <T> Tupla<Lista<T>> subListasAux(Lista<T> ls, Integer n, Tupla<Lista<T>> t) {
		if(ls.isEmpty()) {
			return new Tupla<>(t.x.invertir(), t.y.invertir());
		}
		else if(n>=0) {
			return subListasAux(ls.tail(), n-1, new Tupla<>(t.x.prepend(ls.head()), t.y));	
		}else {
			return subListasAux(ls.tail(),n-1,new Tupla<>(t.x, t.y.prepend(ls.head())));
		}
	}
//-----------------------------------------3-----------------------------------------------		
	static Tupla<Integer> minimo(Lista<Integer>ls){
		return minimoAux(ls,new Tupla<Integer>(ls.head(),0));
	}
	
	private static Tupla<Integer> minimoAux(Lista<Integer> ls, Tupla<Integer> tupla) {
		if(ls.isEmpty()) {
			return tupla; 
		}else {	
			Integer x,y;
			y=tupla.y+1;
			if(ls.head()<=tupla.x) {
				x=ls.head();
			}else {
				x=tupla.x;
			}
			return minimoAux(ls.tail(), new Tupla<Integer>(x,y));
		}
	
	}
	private static <T> Lista<T> concat(Lista<T> l1, Lista<T> l2) {
		if(l1.isEmpty())return l2;
		else return Lista.of(l1.head(),concat(l1.tail(), l2));
	}
	
	
	
	public static void main(String[] args) {
		System.out.println("1-----------------");
		var l1_a = progreRecur(2, 5, 3);
		System.out.println("a)Progrecion(v0=2,n=5,+2):"+l1_a);
		var l1_b = progreTailRec(13, 5, -3);
		System.out.println("b)Progrecion TailRec(v0=10,n=5,-3):"+l1_b);
		
		System.out.println("\n2-----------------");
		var l2 = Lista.of(2, 5, 8, 11, 14, 17, 20, 23, 26, 29);
		System.out.println("Lista:"+l2+"\n indice:5");
		var t2 = subListas(l2,5);
		System.out.println("Listas:\n"+t2.toString2());
		
		System.out.println("\n3-----------------");
		var l3 = Lista.of(12,10,16,11,9,7);
		System.out.println("Lista:"+l3);
		var t3 = minimo(l3);
		System.out.println(t3.toString3());
		
		System.out.println("\n4-----------------");
		System.out.println("Lista1:"+l1_a);
		System.out.println("Lista2:"+l1_b);
		var concat= concat(l1_a,l1_b);
		System.out.println("Concat:"+concat);
		
		
	}


}

