package com.funcional.heap;

import java.util.function.Supplier;

//nodo intermedio
//solo debe tener un puntero al siguiente enlace 
record Suspend<T>(Supplier<TailCall<T>> res) implements TailCall<T> {


	@Override
	public T eval() {
        TailCall<T> tmp = this;
        while(tmp.isSuspended()) {
            tmp = tmp.resume();
        }
        return tmp.eval();	
	}

	@Override
	public TailCall resume() {
		return res.get();
	}

	@Override
	public boolean isSuspended() {
		// TODO Auto-generated method stub
		return true;
	}
 
}
