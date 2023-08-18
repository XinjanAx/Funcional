package com.funcional.deber4.lista_tail_recursiva;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

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

//-------Métodos TC en el Heap-----------
//Agregar
	default ListaTR<T> append(T elem){
		return appendAux(elem,ListaTR.Nil,this);
	}
	private ListaTR<T> appendAux(T elem,ListaTR<T> acc,ListaTR<T> curr){

		if(curr.isEmpty())return ListaTR.of(elem,acc).invertir();
		else {
			return appendAux(elem,ListaTR.of(curr.head(),acc),curr.tail());
		}
	}
	default ListaTR<T> prepend (T elem){
		return ListaTR.of(elem,this);
	}
//invertir	
    default ListaTR<T> invertir() {
        return invertirAux(this, ListaTR.Nil);
    }	
    private ListaTR<T> invertirAux(ListaTR<T> curr, ListaTR<T> acc){
        if(curr.isEmpty()) {
            return acc;
        } else {
            return invertirAux(curr.tail(), ListaTR.of(curr.head(), acc));
        }
        /*
         * ({1,2,3,},{})
         * ({2,3,},{1})
         * ({3,},{2,1})
         * ({},{3,2,1})
         * return {3,2,1}
         * 
         */
    }
//concatenar 2 listas
	default ListaTR<T> concat(ListaTR otr){
		return concatAux(otr, this.invertir());
	}
	private ListaTR<T> concatAux(ListaTR<T> otr, ListaTR<T> acc){
    	if(acc.isEmpty()) return otr;
    	else {
    		return concatAux(ListaTR.of(acc.head(),otr),acc.tail());
    	}
    	   /*
         * ({6,7,8},{1,2,3,4,5})
         * aux
         * ({6,7,8},{5,4,3,2,1})
         * ({5,6,7,8},{4,3,2,1})
         * ...
         * return {1,2,3,4,5,6,7,8}
         */	
    }
//eleminiar el elemento T de la lista
    default ListaTR<T> remove(T elem){
    	return removeAux(elem, Nil, this);
    }
    private ListaTR<T> removeAux( T elem, ListaTR<T> acc, ListaTR<T> curr){
        if(curr.isEmpty()) {
            return acc.invertir();
        } else {
            if (curr.head().equals(elem)) {
                return removeAux(elem,acc,curr.tail());
                	/*asi elimina todos los elemntos == elem
                	 * removeAux(null,...)si enviamos null
                	 * 			solo eliminaria el primer == elem
                	 * o
                	 * return acc.concat(curr);
                	 */
            } else {
                return removeAux(elem, ListaTR.of(curr.head(), acc), curr.tail());
            }
        }
        /*
         * L: (1,2,3,4,5)
         * 
         * (3,{nil},{1,2,3,4,5,nill})
         * (3,{1,nill},{2,3,4,5,nill})
         * (3,{2,1,nill},{3,4,5,nill})
         * return {5,4,2,1,nill}.invertir()
         */
    }
//eliminar los primero n numeros
    default ListaTR<T> drop(int n){
    	return dropAux(n,this);
    }
    default ListaTR<T> dropWhile(Predicate <T> cond){
    	return dropWhileAux(cond,this);
    }
    //auxiliares
    private ListaTR<T> dropAux(int n,ListaTR acc){
    	return !acc.isEmpty()?
    			n!=1?
    			dropAux(n-1,acc.tail()):acc.tail()
    			:acc;
    }
	private ListaTR<T> dropWhileAux(Predicate<T> cond, ListaTR<T> acc){
		if(acc.isEmpty())return acc;
		else {
			if(cond.test(acc.head()))return acc.tail();
			else return dropWhileAux(cond,acc.tail());
		}

	}
//tomar los primeros elementos
    default ListaTR<T> take(int n){
    	return takeAux(n,Nil,this);
    }
    default ListaTR<T> takeWhile(Predicate<T> cond){
    	return takeWhileAux(cond,Nil,this);
    }
    //auxiliares
    private ListaTR<T> takeAux(int n, ListaTR<T> acc,ListaTR<T> curr){
    	if(curr.isEmpty())return this;
    	else if(n==0)return acc.invertir();
    	else return takeAux(n-1,ListaTR.of(curr.head(), acc),curr.tail());
    }
	private ListaTR<T> takeWhileAux(Predicate<T> cond, ListaTR<T> acc,ListaTR<T> curr){
    	if(curr.isEmpty())return this;
    	else if(cond.test(curr.head()))return acc.invertir();
    	else return takeWhileAux(cond, ListaTR.of(curr.head(), acc),curr.tail());
    }
//reemplazar un elemento con otro
	default ListaTR<T> reemplace(T elem,T newElem){
		return reemplaceAux(elem,newElem,Nil,this);
	}
	private ListaTR<T> reemplaceAux(T elem,T newElem,ListaTR<T> acc, ListaTR<T> curr){
		if(curr.isEmpty())return acc.invertir();
		else {
			if(elem.equals(curr.head())) return reemplaceAux(elem,newElem,ListaTR.of(newElem, acc),curr.tail());
			else return reemplaceAux(elem,newElem,ListaTR.of(curr.head(), acc),curr.tail());
		}
		
	}
//verificar si contiene un elemento
	default boolean contain(T elem){
		return containAux(elem,Nil,this).isPresent();
	}
	private Optional<T> containAux(T elem, ListaTR acc, ListaTR<T> curr){
		if(curr.isEmpty())return Optional.empty();
		else if(elem.equals(curr.head()))return Optional.of(curr.head());	
		else return containAux(elem, acc.prepend(curr.head()), curr.tail());
	}
//forEach
	default void forEach(Consumer<T> fn) {
		forEachAux(fn,this);
	}
	private void forEachAux(Consumer<T> fn, ListaTR<T> curr) {
		if(!curr.isEmpty()) {
			fn.accept(curr.head());
			forEachAux(fn,curr.tail());
		}
	}
//Mostrar el tamaño de la lista
	default int size() {
		return sizeAux(0,this);
	}
	private int sizeAux(int acc,ListaTR<T> curr) {
		if(curr.isEmpty())return acc;
		else return sizeAux(acc+1,curr.tail());
	}
 
}
