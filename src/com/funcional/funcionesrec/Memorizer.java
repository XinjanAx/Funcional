package com.funcional.funcionesrec;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Memorizer<T, R> {
	
	private final Map<T, R> cache= new HashMap<>();
	
	public Function<T, R> memorizeInternal(Function<T, R>fn){
		return x-> cache.computeIfAbsent(x,fn::apply);
		/*
		return x->{
			if(cache.containsKey(x))return cache.get(x);
			else {
				R tmp = fn.apply(x);
				cache.put(x, tmp);
				return tmp;
			}
		};
		*/
	}
	
	public static <T,R> Function<T, R> memorize(Function<T, R>fn){
		return new Memorizer<T, R>().memorizeInternal(fn);
	}
}
