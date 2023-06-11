package com.funcional;

import java.util.List;

import com.funcional.lista.*;
	

public class Main {
    public static void main(String[] args) {
    	
        Lista<Integer> ls = Lista.of(2,3,7,4,8);
        
        

        Lista<Integer> l1 = Lista.of(2, 3, 7, 4, 8);

        System.out.println("Lista:"+l1.toString());
       
        System.out.println("Prepend(1):     " + l1.prepend(1).toString());
        System.out.println("Append(1):      " + l1.append(1).toString());
        System.out.println("Remove(7):      " + l1.remove(7).toString());
        System.out.println("Drop(2):        " + l1.drop(2));
        System.out.println("DropWhile(x==4):" + l1.dropWhile(f->f==4));
        
        System.out.println("Lista:"+l1.toString());
    }
}
