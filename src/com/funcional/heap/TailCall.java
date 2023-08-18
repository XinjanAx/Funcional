package com.funcional.heap;

import java.util.function.Supplier;

//lista es para almacenar valores ...
//y el esto para almacenar operaciones intermedias 
public sealed interface TailCall<T> permits Suspend,Return{
	T eval();
	TailCall<T> resume();
	
	boolean isSuspended();
	
//------------------------
	static <T> TailCall<T> ret(T t){
		return new Return<>(t);
	}
	static <T> TailCall<T> sus(Supplier<TailCall<T>> s){
		return new Suspend<>(s);
	}
	
}
