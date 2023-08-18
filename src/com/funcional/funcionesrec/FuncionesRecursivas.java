package com.funcional.funcionesrec;

import java.util.function.Function;

public class FuncionesRecursivas {
	
	static int addMetodo(int x, int y) {
		if(y==0)return x;
		else return addMetodo(x+1,y-1);
	}
	
	static Function<Integer, Function<Integer, Integer>> add = 
			x->y->{
				if(y==0)return x;
				else return FuncionesRecursivas.add.apply(x+1).apply(y-1);
							//usando el nombre de la clase
			};
	public static void main(String[] args) {	
		/*
		Function<Integer, Function<Integer, Integer>> add = 
				x->y->{
					if(y==0)return x;
					else return add.apply(x+1).apply(y-1);
							//Error:  necesitas ser inicializada
				};
				hay q sacar del main al un lvl sup 
				y usarla como metodo de clase
		 */
		var ret = add.apply(3).apply(5);
		System.out.println(ret);
	}

}
