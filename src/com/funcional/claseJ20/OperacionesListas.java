package com.funcional.claseJ20;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Function;
import java.util.function.Predicate;

import com.funcional.lista.Lista;

public class OperacionesListas {
	
	static List<Double> map(List<Integer>ls, Function<Integer,Double>fn){
		List<Double> ret = new ArrayList<>();
		for(Integer it:ls) {
			ret.add(fn.apply(it));
		}
		return ret;
	}
	static <T,U>List<U> mapG(List<T>ls, Function<T,U>fn){
		List<U> ret = new ArrayList<>();
		for(T it:ls) {
			ret.add(fn.apply(it));
		}
		return ret;
	}
	static <T> T reduce(List<T>ls, Function<T,Function<T,T>>fn){
		T acum = ls.get(0);
		for(int i = 1 ; i<ls.size();i++) {
			T elem = ls.get(i);
			acum = fn.apply(acum).apply(elem);
		}
		return acum;
	}
	
	static Integer foldIterLtoR(List<Integer> ls, Integer ident ,Function<Integer,Function<Integer,Integer> > fn) {
		/*
		 * {1,2,3,4}
		 * fold = (((0+1)+2)+3)+4
		 */
		
		Integer acum = ident;
		for(Integer elem :ls) {
			//System.out.printf("%s + %s\n",acum,elem);
			acum = fn.apply(acum).apply(elem);
		}
		return acum;
	}                                               //L->R      (((""+1)+2)+3)+4  
													//U x T -> U
	static <T,U> U foldLeft (List<T>ls,U ident,Function<U,Function<T, U>>fn) {
		var acum = ident;
		for(T elem :ls) {
			//System.out.printf("%s + %s\n",acum,elem);
			acum = fn.apply(acum).apply(elem);
		}
		return acum;
	}
	
	static <T,U> U foldRight (List<T>ls,U ident,Function<T,Function<U, U>>fn) {
		U acum = ident;
		
		for(int i =ls.size()-1;i>=0;i--) {
			acum = fn.apply(ls.get(i)).apply(acum);
		}
		return acum;
	}
	
	public static void main1(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> list = List.of(1,2,3,4,5);
		Function<Integer,Double> fnM = x->x*1.2;
		Lista<Integer>ls = Lista.of(1,2,3,4,5);
		
		System.out.println(mapG(list, fnM));
		System.out.println(ls.map(x->x*1.2));
		
		System.out.println(ls.mapIt(fnM));
		System.out.println(ls.invertir());
		
		Function<Integer,Function<Integer,Integer>> fn = x->y->x+y;
		System.out.println("Suma:"+reduce(list, fn));
	}
		
	public static void testFoldLtoR(String[] args) {
		List<Integer> list = List.of(1,2,3,4);
		System.out.println("List:"+list);
		System.out.println("la suma es:"+foldIterLtoR(list,0,x->y->x+y));
		
		Function<String, Function<Integer, String>>fn2 = str -> x -> str+x;
		System.out.println("List<Int> -> String\n Concat:"+foldLeft(list, "",fn2 ));
		/*
		 * {1,2,3,4}->{"1","2","3","4"}
		 * ls<S> x Int -> ls<S>
		 * 
		 * need
		 * ls,Vo={},fn
		 * f(Lsit,t)->list
		 * 
		 * 1. f(Vo,1)={"1"}
		 * 2. f({"1"},2)={"1","2"}
		 * .
		 * .
		 * 4. f(acum,4) = {"1","2","1","2"}
		 * 
		 
		Function<List<String>, Function<Integer, List<String>>>fn3 = 
					ls -> x -> ls.add(x.toString());
					por q sale boolean
*/
/*
 * lo msimo pero mas caro
 * 
		Function<List<String>, Function<Integer, List<String>>>fn3 = ls -> x -> {
			String tmp = "s"+x.toString();
			ls.add(tmp);
			return ls;
		};
*/
		
		var ret = foldLeft(list, new ArrayList<>(), ls->x->{
			ls.add("s"+x.toString());
			return ls;
		});
		System.out.println(list +"->"+ret);
		
		//---------------
		List<Integer> list2 = List.of(1,3,6,4,2,34,5,6,6,7,9,4,7,8,9,3,2,2,5,4,2,8);
		
		Function<Map<Integer, Integer>, Function<Integer, Map<Integer, Integer>>> fn3 =
                map -> t -> {
                    if(map.containsKey(t)) {
                        var cc = map.get(t);
                        map.put(t, cc+1);
                    } else {
                        map.put(t, 1);
                    }
                    return map;
                };

        var ret4 = foldLeft(list2, new HashMap<Integer, Integer>(), fn3);

        System.out.println(ret4);
		


				
				
		
	}

	public static void testFoldRtoL(String[] args) {
		List<Integer> list = List.of(1,2,3,4);
		var suma1=foldRight(list, 0, x->y->x+y);
		//(1+(2+(3+(4+(5+0)))))
		System.out.println("suma:"+suma1);
		
		Function<Integer,Function<String, String>> fn =
                x -> str -> String.format("(%d+s%)", x,str);
                
        System.out.println("Suma:\n"+foldRight(list, "0", fn));
	}
	
	public static void testalgo(String[] args) {
		Lista<Integer>ls = Lista.of(1,2,3,4,5);
		var suma = ls.reduce(0, x->y->x+y);
		System.out.println(suma);
		
		//--
		var str = ls.foldLeft("0",s->x->String.format("(%s+%s)",s,x));
		System.out.println(str);
		
		var str2 = ls.foldRight("0",x->s->String.format("(%s+%s)",x,s));
		System.out.println(str2);
		
	
		var lsInvert1 = ls.foldLeft(Lista.NIL, list->t->list.prepend(t));	
		System.out.println(lsInvert1);
		
		var lsInvert2 = ls.foldRight(Lista.NIL, t->list->list.append(t));	
		System.out.println(lsInvert2);
		
		//Function<Integer, Function<Integer, Integer>> fn = u->t->1+u;
									//fn
		Integer size = ls.foldLeft(0,u->t->1+u);
		System.out.println(ls.size());
		System.out.println(size);
		
		System.out.println(System.getProperty("java.version"));
	//mapping con fold RtoL LtoR		
	}
	
	public static void mapConFold(String[] args) {
		/*
		Lista<Integer>ls = Lista.of(1,2,3,4,5);
		
		Function<Lista<String>, Function<Lista<Integer>, Lista<String>>> fn = 
					lstr -> li -> lstr.prepend(String.format("%ds", li.head()));
		
		Lista<String> mapL_R = ls.foldLeft(Lista.NIL, lstr -> li ->lstr.prepend(String.format("%ds", li.head())));
		System.out.println("\nMap:"+mapL_R);
		
		Function<Lista<Integer>,Function<Lista<String>, Lista<String>>> fn2 = 
				li -> lstr -> lstr.prepend(String.format("%ds", li.head()));
	
		var mapR_L = ls.foldRight(Lista.NIL, fn2);
		System.out.println("\nMap:"+mapR_L);
		*/
	}
	public static void main(String[] args) {
		var ls = Lista.rangeRecInt(0,10);
		System.out.println(ls);
		
		
		//version imperativa generica de range
		
		var lsWhile = Lista.rangeWhileG(0, sus -> sus+1, elem -> elem <5);
		System.out.println(lsWhile);
	}
}
