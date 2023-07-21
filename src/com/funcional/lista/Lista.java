package com.funcional.lista;

import java.lang.StackWalker.Option;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import com.funcional.deber1.BinTreeD1;

public sealed interface Lista<T> permits Nil, Cons {
	
    Lista NIL = new Nil();
    T head();
    Lista<T> tail();
    
//Cosntructores
    static <T> Lista <T> of (T h, Lista<T> t){
        return new Cons(h, t);
    }
    static<T> Lista of(T... elems) {
        Lista<T> ret = Lista.NIL;
        for(int i = elems.length-1; i >= 0; i--) {
            T h = elems[i];
            ret = Lista.of(h, ret);
        }
        return ret;
    }
    
//Nulidad?
    default boolean isEmpty() {
        return this == NIL;
    }

//----------------------------Metodos----------------------------------
    default Lista<T> append(T elem){

        return isEmpty()?
                Lista.of(elem):
                Lista.of(head(), tail().append(elem));
    }
    
//-------------    
    default Lista<T> prepend(T elem){
        return Lista.of(elem,this);
    }
    
//-------------
    default Lista<T> remove(T elem) {
    	return !isEmpty()? 
    			head()==elem?
    					tail().remove(elem)
    					:Lista.of(head(),tail().remove(elem))
    					:NIL;
    }
//    	if(!isEmpty()) {
//    		if(head()==elem) {
//    			return tail().remove(elem);
//    		}else {
//    			return Lista.of(head(), tail().remove(elem));
//    		}
//    	}else return Lista.of(Lista.NIL);
//    }
    
//-------------    
    default Lista<T> drop(int n){
    	return !isEmpty()?
    			n!=1?
    			tail().drop(n-1):tail()
    			:this;
    }
//    	if(!isEmpty()) {
//    		if(n!=1) return tail().drop(n-1);
//    		else return tail();   		
//    	} else return this;
    
//-------------    
    default Lista<T> dropWhile(Predicate <T> cond){
    	return !isEmpty()?cond.test(head())?tail():tail().dropWhile(cond):this;
    }
//    	if(!isEmpty()) {
//    		if(cond.test(head())) return tail();
//    		else return tail().dropWhile(cond);   		
//    	}else return this;			
    
//-------------
    default Lista<T> take(Integer n){
        return isEmpty()||n==0?
        		NIL
        		:Lista.of(head(),tail().take(n-1));
    }
        //return isEmpty()?NIL:n!=0?Lista.of(head(),tail().take(n-1)):NIL;
//        if(isEmpty()) {
//            return NIL;
//        }else {
//            if(n!=0) {
//                return Lista.of(head(),tail().take(n-1));            
//            }else {
//                return NIL;
//            }
//        }         

//----------------------

    default Lista<T>takeWhile(Predicate<T>p){
    	return !isEmpty()?!p.test(head())?Lista.of(head(),tail().takeWhile(p)):Lista.of(head()):NIL;
    }

//----------------------
    default Lista<T> concat(Lista<T>other){
    	return isEmpty()?other:Lista.of(head(),tail().concat(other));
    }
//    if(isEmpty()) return other;
//    else return Lista.of(head(),tail().concat(other));

//------------------------------

    default Lista<T> invertir() {

        Lista<T> tmp = this;
        Lista<T> retList=NIL;

        while(!tmp.isEmpty()) {
            retList = Lista.of(tmp.head(), retList);
            tmp = tmp.tail();
        }
        return retList;
    }
    
    default void forEach(Consumer<T> fn) {
        if(!isEmpty()) {
            fn.accept(head());
            tail().forEach(fn);
        }
    }
    
//------------------------------
    default Lista<T> invertirRec() {
    	return !isEmpty()?tail().invertirRec().append(head()):NIL;
    }
//	if(isEmpty()) {
//	return NIL;
//} else {
//	return tail().invertir().append(head());
//}
//----------Funciones----------------------
    
    public static Integer sumatoria(Lista<Integer>l) {
    	return !l.tail().isEmpty()?l.head()+sumatoria(l.tail()):l.head();
   }
//    if(!l.tail().isEmpty()) {
//        return l.head() + sum(l.tail());
//    }else return l.head();
    
//--------------
    public static Integer multip(Lista<Integer>l) {
    	return !l.tail().isEmpty()?l.head()*multip(l.tail()):l.head();
    }
    
//--------------
	static Integer maximo(Lista<Integer>l) {
		return !l.tail().isEmpty()? Math.max(l.head(),maximo(l.tail())):l.head();
	}
//-------------
	default Lista<T> ordenar(){	
		return null;
	}
//------------- Leer texto largo----------------------
	
	
	default Lista<T> reemplace(T elem,T newElem){
		
		return !isEmpty()?
				head().equals(elem)?
						Lista.of(newElem,tail())
						:Lista.of(head(),tail().reemplace(elem, newElem))
				:NIL;
		
	}
	
	default Optional<T> contain(T elem) {

        return isEmpty()?
                Optional.empty():
                    head().equals(elem)?
                            Optional.of(head()):
                                tail().contain(elem);

    }
	
//--------------BinTree----------------

	default BinTreeD1<T> buildTree() {
        if (!isEmpty()) {
        	T h = head();
            int k = tail().isEmpty() ? 0: (tail().size() == 1) ? 1 : tail().size() / 2;
            Lista<T> leftList = tail().take(k);
            Lista<T> rightList = tail().drop(k);
            return BinTreeD1.of(h, leftList.buildTree(), rightList.buildTree());
            
        } else {
        	return BinTreeD1.Leaf;
        }
    }
	

    default int size() {
        return isEmpty()
                ? 0
                : 1 + tail().size();
    }
    //-----------------------Mapping------------
    
    default <U> Lista <U> map (Function<T,U> fn){
    	return this.isEmpty()?
    			Lista.NIL
    			:Lista.of(fn.apply(head()),tail().map(fn));
    			//:tail().map(fn).prepent(fn.apply(head());
    }
    default <U> Lista <U> mapIt (Function<T,U> fn){
    	var tmp = this;
    	Lista<U> retTmp = Lista.NIL;
    	
    	while(!tmp.isEmpty()) {
    		T elem = tmp.head();
    		U newE = fn.apply(elem);
    		
    		//retTmp = retTmp.prepend(newE);
    		retTmp=Lista.of(newE,retTmp);
    		
    		tmp=tmp.tail();
    	}
    	return retTmp.invertir();
    }
	
	
//----------------------------------Fin----------------------------------------------    
}
final class Nil<T> implements Lista<T> {
    @Override
    public T head() {
        return null;
    }

    @Override
    public Lista<T> tail() {
        return null;
    }

	@Override
	public String toString() {
		return "NIL";
	}
	
}