package com.funcional.practicaFinal;

import java.util.function.Function;

import com.funcional.heap.TailCall;
import com.funcional.lista.Lista;

public class Prueba22_23ezFoldsMalos {
	
	record Tupla<T> (T x , T y){
		
		public String toString() {
			return "(" + x + "," + y + ")";
		}	
		public String toString3() {
			return "Lista1"+ x + "\n		    Lista2" + y;
		}
	}
//1	& 5
	static TailCall<Lista<Tupla<Integer>>>  gen1ListaT(Lista<Integer>l1,Lista<Integer>l2){
		return genListaTuplaAux(l1,l2,Lista.NIL);
	}
	
	private static TailCall<Lista<Tupla<Integer>>>  genListaTuplaAux(Lista<Integer>l1,Lista<Integer>l2,Lista<Tupla<Integer>>acc){
		var tupla = new Tupla<>(l1.head(),l2.head());
		if(l1.isEmpty()) {
			return TailCall.ret(acc.invertir()) ;
						//añarid el método invertir a la lista
		}else {
			return TailCall.sus(()->genListaTuplaAux(l1.tail(), l2.tail(), Lista.of(tupla,acc))) ;
		}	
	}

//2 & 6
		
	private static Lista<Integer> gen2ListaSuma(Lista<Tupla<Integer>> lst) {
				//TxU->U
		return lst.foldRight(Lista.NIL, t->ls-> ls.prepend(t.x+t.y));
	}
//3	
	static Tupla<Lista<Integer>> gen3TuplaL(Lista<Integer>ls){
		var tupla = genTuplaLAux(ls);
		return new Tupla<>(tupla.x.invertir(),tupla.y.invertir());
	}

	private static Tupla<Lista<Integer>> genTuplaLAux(Lista<Integer> ls) {
					//UxT->U
		return ls.foldLeft(new Tupla<>(Lista.NIL,Lista.NIL), t->elem->{
			Integer x,y;
			if(elem>=10) {
				 x = elem-10;
				 y = 1;
			}else {
				 x = elem;
				 y = 0;
			}
			return new Tupla<>(t.x.prepend(x),t.y.prepend(y));
			}
		);
	}
//4
	static Tupla<Lista<Integer>> addZero(Tupla<Lista<Integer>> t){
		return new Tupla<>(t.x.prepend(0),t.y.append(0));
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Lista<Integer> l1 = Lista.of(3,2,3,7,5);
		Lista<Integer> l2 = Lista.of(6,8,4,2,8);
		System.out.println("1)");
		System.out.println("	Lista1:"+l1);
		System.out.println("	Lista2:"+l2);

		var listaTupla = gen1ListaT(l1, l2).eval();
		System.out.println("	Lista de Tuplas:\n	"+listaTupla);
		
		System.out.println("\n2)");
		var listaSuma = gen2ListaSuma(listaTupla);
		System.out.println("	Lista Suma:"+ listaSuma);
		
		System.out.println("\n3)");
		var tuplaListas = gen3TuplaL(listaSuma);
		System.out.println("	Tupla Lista:"+tuplaListas.toString3());
		
		System.out.println("\n4)");
		var tuplsZero = addZero(tuplaListas);
		System.out.println("	Lista add 0:"+tuplsZero.toString3());
		
		System.out.println("\n5)");
		var lista0 = gen1ListaT(tuplsZero.x,tuplsZero.y).eval();
		System.out.println("	Lista Tupla:"+lista0);
		
		System.out.println("\n6)");
		var listaSuma6 = gen2ListaSuma(lista0);
		System.out.println("	Lista suma 6:"+listaSuma6);
		
	}

}
