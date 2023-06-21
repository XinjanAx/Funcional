package com.funcional.lista;

import java.util.function.Predicate;

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
    	return !isEmpty()?n!=1?tail().drop(n-1):tail():this;
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
        return isEmpty()||n==0?NIL:Lista.of(head(),tail().take(n-1));
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

    default Lista<T> invertirIter() {

        Lista<T> tmp = this;
        Lista<T> retList=NIL;

        while(!tmp.isEmpty()) {
            retList = Lista.of(tmp.head(), retList);
            tmp = tmp.tail();
        }
        return retList;
    }
    
//------------------------------
    default Lista<T> invertir() {
    	return !isEmpty()?tail().invertir().append(head()):NIL;
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