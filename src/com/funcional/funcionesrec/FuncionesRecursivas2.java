package com.funcional.funcionesrec;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class FuncionesRecursivas2 {
	
	static Map<Integer, Integer> cache = new HashMap<>();
	
	static void pause() {
		try {
			Thread.sleep(1000);
		}catch (Exception e) {
			throw new RuntimeException(e);// TODO: handle exception
		}
	}

	static Integer doubleValue(Integer x) {
		pause();
		return x*2;
	}
	
	static Function<Integer, Integer> doubleValueM = 
		x->cache.computeIfAbsent(x, y->{
				pause();
				return y*2;
			});
	
	
	public static void main(String[] args) {
		
		Function<Integer, Integer> doubleValue2 = x->{
			pause();
			return x*2;
		};
		
		var dv = Memorizer.memorize(doubleValue2);
		
		System.out.println(dv.apply(1));
		System.out.println(dv.apply(2));
		System.out.println(dv.apply(3));
		System.out.println(dv.apply(3));
		System.out.println(dv.apply(4));
		System.out.println(dv.apply(5));
		
		
		
	}


}
