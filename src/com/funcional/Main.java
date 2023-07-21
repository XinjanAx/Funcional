package com.funcional;

import java.util.function.Function;

import com.funcional.lista.*;
	

public class Main {
	//funcion
	static <T,U,V> Function<Function<T, U>,Function<Function<U, V>, Function<T, V>>> compH(){
		return f-> g -> x -> g.apply(f.apply(x));
	}
	
	//metodo funcional
    static Function<Integer, Integer> composicion(Function<Integer, Integer> f, Function<Integer, Integer> g){
        return x -> g.apply(f.apply(x));
    }
    
    public static void main(String[] args) {        

        Lista<Integer> l1 = Lista.of(2, 3, 7, 4, 8);
        Lista<Integer> l2 = Lista.of(22, 33, 77, 44, 88);

        System.out.println("Lista:"+l1.toString());
       
        System.out.println("Prepend(1):     " + l1.prepend(1).toString());
        System.out.println("Append(1):      " + l1.append(1).toString());
        System.out.println("Remove(7):      " + l1.remove(7).toString());
        System.out.println("Drop(2):        " + l1.drop(2));
        System.out.println("Take(2):        " + l1.take(2));
        System.out.println("DropWhile(x==4):" + l1.dropWhile(f->f==4));
        System.out.println("TakeWhile(x==4):" + l1.takeWhile(f->f==4));
        
        System.out.println("\n"+l1 +"  "+ l2);
        System.out.println("l1.Concat(l2):" + l1.concat(l2));
        System.out.println("l2.Concat(l1):" + l2.concat(l1));
        
        System.out.println("\n Lista 1:"+l1.toString());
        System.out.println(" Lista 2:"+l2+"\n");
        
        System.out.println("Invertir(I):" + l2.invertirRec());
        System.out.println("Invertir(R):" + l2.invertir());
        
        System.out.println("\nSuma de elementos de la Lista:\n"+l1+" = "+Lista.sumatoria(l1));
        System.out.println("\nMultiplicacion de elementos de la Lista:\n"+l1+" = "+Lista.multip(l1));
        System.out.println("\nMaximo elemento de la Lista:\n"+l1+" = "+Lista.maximo(l1));
        
        System.out.println("\n"+l1);
        
        //----------------------------------------------------------------------------
        
        System.out.println("Fucnion en el main");
        Function<Function<Integer, Integer>,
        	Function<Function<Integer, Integer>, Function<Integer, Integer>>> 
        		fn = f->g->x->g.apply(f.apply(x));
        		
        Function<Integer, Integer> tripe = x->3*x;
        Function<Integer, Integer> cuadrado = x ->x*x;
        
        System.out.println("\nf(x) = x->3*x;\ng(x) = x ->x*x;");
        
        
        System.out.println("fog(2): "+fn.apply(tripe).apply(cuadrado).apply(2));
        System.out.println("gof(2): "+fn.apply(cuadrado).apply(tripe).apply(2));
        
        //----------------------------------------------------------------------------
        
        System.out.println("\nMetodo funcional");
        System.out.println("fog(2): "+composicion(tripe, cuadrado).apply(2));
        //----------------------------------------------------------------------------
        
        System.out.println("\nFucion metodo");
        System.out.println("f(x) = x->3*x;\ng(x) = x ->x*x;");
        
        System.out.println("\nfog(2): "+Main.<Integer,Integer,Integer>
        								compH().apply(tripe).apply(cuadrado).apply(2));
        
        //----------------------------------------------------------------------------
        //recibe un texto y devuelve una tupla de la palabra y 
        //cuantas veces se repite la palabra
        

    	    	
    }
    
    
}
