package com.funcional.practicaFinal;

import java.util.function.Predicate;

import com.funcional.heap.TailCall;
import com.funcional.lista.Lista;

public class Examen2_21_22monedas {
	

	record Tuplas<T> (T x , T y){
			@Override
			public String toString() {
				return "(" + x + "," + y + ")";
			}	
	}
	
	static <T> String sucesor(Lista<Tuplas<Character>> ls,Character val) {
		String acc = String.format("Los nodos de '%s' son ", val);
		return sucesorAux(ls, val, acc);
	}

	 
	private static <T> String sucesorAux (Lista<Tuplas<Character>> ls,Character val,String acc) {
		if(ls.isEmpty()) return acc.substring(0, acc.length() - 1)+"." ;
		else {
			if(val.equals(ls.head().x)) {
				return sucesorAux(ls.tail(), val, acc +"'"+ls.head().y+"',");
			}else return sucesorAux(ls.tail(), val, acc);
		}	
	}
	
	 
	private static <T> String sucesorFoldL (Lista<Tuplas<Character>> ls,Character val) {
		return ls.foldLeft(String.format("Los nodos de '%s' son ", val), str -> tup ->{
			if(val.equals(tup.x)) {
				return str.concat("'"+tup.y+"',");
			}else return str;
		});
	}
	private static <T> String sucesorFoldR (Lista<Tuplas<Character>> ls,Character val) {
		return ls.foldRight(String.format("Los nodos de '%s' son ", val), tup -> str ->{
			if(val.equals(tup.x)) {
				return str.concat("'"+tup.y+"',");
			}else return str;
		});
	}
	
	
	static Lista<Integer> descomponer(Lista<Integer>grup,Integer val){
		return descomponerAux(grup,val, Lista.NIL);
	}
	 
	private static Lista<Integer> descomponerAux(Lista<Integer>grup,Integer val,Lista<Integer> ls){
		if(val == 0) return ls.invertir();
		else {
			if(val>=grup.head()) {
				return descomponerAux(grup,val-grup.head(), ls.prepend(grup.head()));
			}else {
				return descomponerAux(grup.tail(),val, ls);
			}
		}
	}
	
	private static <T> Lista<T> dropWhile(Lista<T> ls, Predicate<T> fn ){
		return dropWhileAux(ls, fn).eval();
	}
	
	static <T> TailCall< Lista<T>> dropWhileAux(Lista<T> ls, Predicate<T> fn ){
		if(ls.isEmpty()) {
			return TailCall.ret(ls);
		}else {
			if(fn.test(ls.head())) {
				return TailCall.ret(ls.tail()) ;
			}else return TailCall.sus(()->dropWhileAux(ls.tail(),fn)) ;
		}
	}
	
	public static void main(String[] args) {
		
		System.out.println("1-----------------");
		Tuplas<Character> p1 = new Tuplas<Character>('m', 'n');
		Tuplas<Character> p2 = new Tuplas<Character>('m', 'p');
		Tuplas<Character> p3 = new Tuplas<Character>('m', 'o');
		Tuplas<Character> p4 = new Tuplas<Character>('n', 'q');
		Tuplas<Character> p5 = new Tuplas<Character>('p', 'q');
		Tuplas<Character> p6 = new Tuplas<Character>('o', 'r');
		Tuplas<Character> p7 = new Tuplas<Character>('q', 'r');
		Tuplas<Character> p8 = new Tuplas<Character>('q', 's');
		
		var l1 = Lista.of(p1,p2,p3,p4,p5,p6,p7,p8);
		System.out.println("Lista Tuplas:"+l1);
		var sucesores = sucesor(l1,'m');
		System.out.println(sucesores);
		var sucesoresfold = sucesorFoldR(l1, 'm');
		System.out.println("FoldR: "+sucesoresfold);
		
		System.out.println("\n2-----------------");
		var grupo = Lista.of(25,10,5,1);
		System.out.println("Grupo de monedas:"+grupo);
		Integer mony = 75;
		var cambio = descomponer(grupo,mony);
		System.out.println(String.format("Cambio de %s es: %s", mony,cambio));
	
		System.out.println("\n3-----------------");
		Lista<Integer> l3 = Lista.of(2,5,7,6,4,8,9,4);
		System.out.println("Lista:"+l3);
		var ldropW = dropWhile(l3,x->x>7);
		System.out.println("Drop While en Heap(x>7):"+ldropW);
		System.out.println("\nNota: El .eval() esta en el metodo auxiliar");
	
	}
}
