package com.funcional.deber4.lista_tail_rec_heap;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import com.funcional.heap.TailCall;

public sealed interface ListaTRH<T> permits NilTRH<T>, ConsTRH<T> {
	
	T head();
	ListaTRH<T> tail();
	ListaTRH Nil = new NilTRH<>();
	
//Constructores
	static <T> ListaTRH<T> of (T h, ListaTRH<T> t){
		return new ConsTRH(h,t);
	}
	static <T> ListaTRH of(T... elem) {
		ListaTRH<T> ret = Nil;
		for(int i = elem.length-1; i >= 0; i--) {
            T h = elem[i];
            ret = ListaTRH.of(h, ret);
        }
		return ret;
	}
//Nulidad
	default boolean isEmpty() {
		return this == Nil;
	}

//-------Métodos TC-----------
//Agregar
	default TailCall<ListaTRH<T>> append(T elem){
		return appendAux(elem,ListaTRH.Nil,this);
	}
	private TailCall<ListaTRH<T>> appendAux(T elem,ListaTRH<T> acc,ListaTRH<T> curr){

		if(curr.isEmpty())return TailCall.ret(ListaTRH.of(elem,acc).invertir()) ;
		else {
			return TailCall.sus(()->appendAux(elem,ListaTRH.of(curr.head(),acc),curr.tail()));
		}
	}
	default TailCall<ListaTRH<T>> prepend (T elem){
		return TailCall.ret(ListaTRH.of(elem,this));
	}
//invertir	
	/*
	 * al momento mandar al H elmetodo invertir 
	 * ahi q agregar el .eval() a todos los metodos 
	 * q usan el metodo invertir
	 * por eso me decante usar el invertir() iterativo
	 */
    default ListaTRH<T> invertir() {

    	ListaTRH<T> tmp = this;
    	ListaTRH<T> retList=Nil;

        while(!tmp.isEmpty()) {
            retList = ListaTRH.of(tmp.head(), retList);
            tmp = tmp.tail();
        }
        return retList;
    }
    
//concatenar 2 listas
	default TailCall<ListaTRH<T>> concat(ListaTRH otr){
		return concatAux(otr, this.invertir());
	}
	private TailCall<ListaTRH<T>> concatAux(ListaTRH<T> otr, ListaTRH<T> acc){
    	if(acc.isEmpty()) return TailCall.ret(otr);
    	else {
    		return TailCall.sus(()->concatAux(ListaTRH.of(acc.head(),otr),acc.tail())) ;
    	}
    }
//eleminiar el elemento T de la lista
    default TailCall<ListaTRH<T>>  remove(T elem){
    	return removeAux(elem, Nil, this);
    }
    private TailCall<ListaTRH<T>>  removeAux( T elem, ListaTRH<T> acc, ListaTRH<T> curr){
        if(curr.isEmpty()) {
            return TailCall.ret(acc.invertir()) ;
        } else {
            if (curr.head().equals(elem)) {
                return TailCall.sus(()->removeAux(elem,acc,curr.tail())) ;
            } else {
                return TailCall.sus(()->removeAux(elem, ListaTRH.of(curr.head(), acc), curr.tail()));
            }
        }
    }
//eliminar los primero n numeros
    default TailCall<ListaTRH<T>> drop(int n){
    	return dropAux(n,this);
    }
    default TailCall<ListaTRH<T>> dropWhile(Predicate <T> cond){
    	return dropWhileAux(cond,this);
    }
    //auxiliares
    private TailCall<ListaTRH<T>> dropAux(int n,ListaTRH acc){
    	return !acc.isEmpty()?
    			n!=1?
    				TailCall.sus(()->dropAux(n-1,acc.tail()))
    				:TailCall.ret(acc.tail()) 
    			:TailCall.ret(acc);
    }
	private TailCall<ListaTRH<T>> dropWhileAux(Predicate<T> cond, ListaTRH<T> acc){
		if(acc.isEmpty())return TailCall.ret(acc) ;
		else {
			if(cond.test(acc.head()))return TailCall.ret(acc.tail());
			else return TailCall.sus(()->dropWhileAux(cond,acc.tail())) ;
		}

	}
//tomar los primeros elementos
    default TailCall<ListaTRH<T>> take(int n){
    	return takeAux(n,Nil,this);
    }
    default TailCall<ListaTRH<T>> takeWhile(Predicate<T> cond){
    	return takeWhileAux(cond,Nil,this);
    }
    //auxiliares
    private TailCall<ListaTRH<T>> takeAux(int n, ListaTRH<T> acc,ListaTRH<T> curr){
    	if(curr.isEmpty())return TailCall.ret(this);
    	else if(n==0)return TailCall.ret(acc.invertir());
    	else return TailCall.sus(()->takeAux(n-1,ListaTRH.of(curr.head(), acc),curr.tail())) ;
    }
	private TailCall<ListaTRH<T>> takeWhileAux(Predicate<T> cond, ListaTRH<T> acc,ListaTRH<T> curr){
    	if(curr.isEmpty())return TailCall.ret(this);
    	else if(cond.test(curr.head()))return TailCall.ret(acc.invertir());
    	else return TailCall.sus(()->takeWhileAux(cond, ListaTRH.of(curr.head(), acc),curr.tail()));
    }
//reemplazar un elemento con otro
	default TailCall<ListaTRH<T>> reemplace(T elem,T newElem){
		return reemplaceAux(elem,newElem,Nil,this);
	}
	private TailCall<ListaTRH<T>> reemplaceAux(T elem,T newElem,ListaTRH<T> acc, ListaTRH<T> curr){
		if(curr.isEmpty())return TailCall.ret(acc.invertir()) ;
		else {
			if(elem.equals(curr.head())) return TailCall.sus(()->reemplaceAux(elem,newElem,ListaTRH.of(newElem, acc),curr.tail())) ;
			else return TailCall.sus(()-> reemplaceAux(elem,newElem,ListaTRH.of(curr.head(), acc),curr.tail()));
		}
		
	}
//verificar si contiene un elemento
	default boolean contain(T elem){
		return containAux(elem,Nil,this).eval().isPresent();
	}
	private TailCall<Optional<T>>  containAux(T elem, ListaTRH acc, ListaTRH<T> curr){
		if(curr.isEmpty())return TailCall.ret(Optional.empty());
		else if(elem.equals(curr.head()))return TailCall.ret(Optional.of(curr.head()));	
		else return TailCall.sus(()-> containAux(elem, ListaTRH.of(curr.head(), acc), curr.tail()));
	}
//forEach
	/*
	 * forEach no retorna nada
	 * no puede usar el metodo TailCall.sus/.ret
	 */
	default void forEach(Consumer<T> fn) {
		forEachAux(fn,this);
	}
	private void forEachAux(Consumer<T> fn, ListaTRH<T> curr) {
		if(!curr.isEmpty()) {
			fn.accept(curr.head());
			forEachAux(fn,curr.tail());
		}
	}
//Mostrar el tamaño de la lista
	default TailCall<Integer> size() {
		return sizeAux(0,this);
	}
	private TailCall<Integer> sizeAux(int acc,ListaTRH<T> curr) {
		if(curr.isEmpty())return TailCall.ret(acc) ;
		else return TailCall.sus(()->sizeAux(acc+1,curr.tail())) ;
	}
 
}
