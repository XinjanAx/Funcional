package com.funcional.funcionesrec;

import java.util.function.Function;

public class FuncionesMemorize {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Function<Integer, Integer> dpubleValue = x->x*2;
		Memorizer<Integer, Integer> memo = new Memorizer<>();
		var doubleValueCache = memo.memorize(dpubleValue);
		
	}

}
