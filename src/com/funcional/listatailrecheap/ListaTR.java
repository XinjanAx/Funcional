package com.funcional.listatailrecheap;

import com.funcional.lista.Lista;

public sealed interface ListaTR<T> permits NilTR<T>, ConsTR<T> {
	
	T head();
	ListaTR<T> tail();
	ListaTR Nil = new NilTR<>();
	
//Constructores
	static <T> ListaTR<T> of (T h, ListaTR<T> t){
		return new ConsTR(h,t);
	}
	static <T> ListaTR of(T... elem) {
		ListaTR<T> ret = Nil;
		for(int i = elem.length-1; i >= 0; i--) {
            T h = elem[i];
            ret = ListaTR.of(h, ret);
        }
		return ret;
	}
//Nulidad
	default boolean isEmpty() {
		return this == Nil;
	}

//-------Métodos TC-----------
//Agregar
	default ListaTR<T> append(T elem, ListaTR ret){
		if(isEmpty())return ListaTR.of(elem);
		else {
			 
			return ret.append(elem,ret);
		}
	}
	
	
	
	
}
