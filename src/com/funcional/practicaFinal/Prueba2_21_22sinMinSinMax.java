package com.funcional.practicaFinal;

import com.funcional.lista.Lista;

public class Prueba2_21_22sinMinSinMax {
	
//------------------------1----------------------------------	
	static Integer maximoRec(Lista<Integer> ls) {
		return maximoAux(ls, ls.head());
	}
	private static Integer maximoAux(Lista<Integer> ls,Integer max) {
		if(ls.isEmpty()) return max;
		else {
			if(ls.head()>max)return maximoAux(ls.tail(), ls.head());
			else return maximoAux(ls.tail(), max);
		}
	}
	
	static Integer maximoFoldLeft(Lista<Integer> ls) {
		//UxT->U
		return ls.foldLeft(ls.head(), max->elem->{
			if(elem>max)return elem;
			else return max;
		});
	}
	
//------------------------2----------------------------------	
	static Integer minimoRec(Lista<Integer> ls) {
		return minimoRecAux(ls,ls.head());
	}
	private static Integer minimoRecAux(Lista<Integer> ls, Integer min) {
		if(ls.isEmpty())return min;
		else return ls.head()<min?
				minimoRecAux(ls.tail(),ls.head())
				:minimoRecAux(ls.tail(), min);
	}
	
	private static Integer minimoFoldLeft(Lista<Integer> ls) {
		//TxU->U
		return ls.foldRight(ls.head(), elem->min->{
			if(elem<min)return elem;
			else return min;
		});
	}
//------------------------3----------------------------------	
	
	static Lista<Integer> sinMax(Lista<Integer>ls){
		if(ls.isEmpty())return Lista.NIL;
		else {
			if(ls.head()==maximoRec(ls)) return ls.tail();
			else return Lista.of(ls.head(), sinMax(ls.tail()));
		}
	}
	
//------------------------4----------------------------------	

	
	private static Lista<Integer> sinMinimo(Lista<Integer> ls) {
		if(ls.isEmpty())return Lista.NIL;
		else {
			if(ls.head()==minimoRec(ls)) return ls.tail();
			else return Lista.of(ls.head(), sinMinimo(ls.tail()));
		}
	}
	
	private static Lista<Integer> ordenar(Lista<Integer> ls) {	
		if(ls.isEmpty())return Lista.NIL;
		else {			
			return Lista.of(minimoRec(ls),ordenar(sinMinimo(ls)));
		}
	}


//------------------------Main----------------------------------	
	public static void main(String[] args) {
		System.out.println("1-----------------");
		var ls = Lista.of(4,6,2,3,8);
		System.out.println("Lista:"+ls);
		var max = maximoRec(ls);
		System.out.println("Maximo(ls):"+max);
		var maxf = maximoFoldLeft(ls);
		System.out.println("Maximo Fold Left(ls):"+maxf);
		
		System.out.println("\n2-----------------");
		System.out.println("Lista:"+ls);
		var min = minimoRec(ls);
		System.out.println("Minimo(ls):"+min);
		var minf = minimoFoldLeft(ls);
		System.out.println("Minimo Fold Right(ls):"+minf);
		
		System.out.println("\n3-----------------");
		System.out.println("Lista:"+ls);
		var sinMax = sinMax(ls);
		System.out.println("Sin Max"+sinMax);
		
		System.out.println("\n4-----------------");
		System.out.println("Lista:"+ls);
		var ordenar = ordenar(ls);
		System.out.println("Ordenar:"+ordenar);
		
		
		
	}





}
