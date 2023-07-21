package com.funcional.deber3;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
	
	static List<String> mapeo(List<Integer> ls,Function<Integer,String>fn){
		if(ls.isEmpty())
			return List.of();
			else{
				Integer head = ls.get(0);
				List<Integer> tail = ls.subList(1, ls.size());
				return Stream.concat(List.of(fn.apply(head)).stream(), mapeo(tail,fn).stream()).collect(Collectors.toList());
				
			}
	}
	
	static <T,U> List<U> mapeoGenerico(List<T> ls,Function<T,U>fn){
		if(ls.isEmpty())
			return List.of();
			else{
				T head = ls.get(0);
				var tail = ls.subList(1, ls.size());
				return Stream.concat(List.of(fn.apply(head)).stream(), mapeoGenerico(tail,fn).stream()).collect(Collectors.toList());				
			}
	}
	
	static Integer suma(List<Integer> ls){
		if(ls.isEmpty())
			return 0;
			else{
				Integer head = ls.get(0);
				List<Integer> tail = ls.subList(1, ls.size());
				return head + suma(tail);
				
			}
	}
	
	static<T> T Generica(List<T> ls,T item){		
		if(ls.isEmpty())
			return ls.get(0);
			else{
				T head = ls.get(0);
				List<T> tail = ls.subList(1, ls.size());
				return head==item?head:Generica(tail,item);
				//el retorno depende de q es lo q se quiere hacer				
			}
	}
	
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		var ls = List.of(3,5,7,10,3,2,4,5);
		System.out.println("Lista:"+ls);
		
		
		Function<Integer,String> fn = x -> x+"txt";
		
		System.out.println("\nMap\nFuncion: x -> x + 'txt'");
		
		System.out.println("Mapping:"+mapeo(ls,fn)+" Int -> String");
		
		System.out.println("MapGenerico:"+mapeoGenerico(ls,fn)+" T -> U");
		
		
		System.out.println("\nReducing");
		System.out.println("Lista:"+ls);
		System.out.println("Suma:"+suma(ls)+"	 List -> Integer");
		
		System.out.println("Generico: "+Generica(ls,5));
		
		
		
		
	}

}
