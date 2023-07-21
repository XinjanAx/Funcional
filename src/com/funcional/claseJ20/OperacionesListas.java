package com.funcional.claseJ20;

import java.util.List;
import java.util.ArrayList;
import java.util.function.Function;

import com.funcional.lista.Lista;

public class OperacionesListas {
	
	static List<Double> map(List<Integer>ls, Function<Integer,Double>fn){
		List<Double> ret = new ArrayList<>();
		for(Integer it:ls) {
			ret.add(fn.apply(it));
		}
		return ret;
	}
	static <T,U>List<U> mapG(List<T>ls, Function<T,U>fn){
		List<U> ret = new ArrayList<>();
		for(T it:ls) {
			ret.add(fn.apply(it));
		}
		return ret;
	}
	
	static <T> T reduce(List<T>ls, Function<T,Function<T,T>>fn){
		T acum = ls.get(0);
		for(int i = 1 ; i<ls.size();i++) {
			T elem = ls.get(i);
			acum = fn.apply(acum).apply(elem);
		}
		return acum;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> list = List.of(1,2,3,4,5);
		Function<Integer,Double> fn = x->x*1.2;
		Lista<Integer>ls = Lista.of(1,2,3,4,5);
		
		System.out.println(mapG(list, fn));
		System.out.println(ls.map(x->x*1.2));
		
		System.out.println(ls.mapIt(fn));
		System.out.println(ls.invertir());
		
		System.out.println("Suma:"+reduce(ls, x->y->x+y));
	}

}
