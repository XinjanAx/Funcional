package com.funcional.deber4;

import java.util.function.Predicate;

import com.funcional.deber4.lista_tail_rec_heap.ListaTRH;
import com.funcional.deber4.lista_tail_recursiva.ListaTR;

public class Deber4Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListaTR ls = ListaTR.of(1,2,3,4,5);
		System.out.println("ListaTR:"+ls+"\n");
		
		System.out.println(" Append(6):"+ls.append(6));
		System.out.println(" Prepend(0):"+ls.prepend(0));

		ListaTR ls2 = ListaTR.of(6,7,8);
		System.out.println(" Concat([6,7,8]):"+ls.concat(ls2));
		
		System.out.println(" Remove(3):"+ls.remove(3));
		
		System.out.println(" Drop(3):"+ls.drop(3));
		Predicate<Integer> p1= x->x==4;
		System.out.println(" DropWhile(x==4):"+ls.dropWhile(p1));

		System.out.println(" Take(3):"+ls.take(3));
		Predicate<Integer> p2 = x->x==4;
		System.out.println(" TakeWhile(x==4):"+ls.takeWhile(p2));
		
		System.out.println(" Reemplace(3)->(99):"+ls.reemplace(3, 99));
		
		System.out.println(" Contain(4):"+ls.contain(4));
		
		System.out.print(" ForEach(syso):");
		ls.forEach(System.out::print);
		System.out.println();
		System.out.println(" Size():"+ls.size());
		
		System.out.println("\n----------------Tc Recursivo en el Heap-------------\n");
		
		ListaTRH lsH = ListaTRH.of(1,2,3,4,5);
		System.out.println("ListaTRH:"+lsH+"\n");

		System.out.println(" Append(6):"+lsH.append(6).eval());
		
		System.out.println(" Prepend(0):"+lsH.prepend(0).eval());

		ListaTRH lsH2 = ListaTRH.of(6,7,8);
		System.out.println(" Concat([6,7,8]):"+lsH.concat(lsH2).eval());
		
		System.out.println(" Remove(3):"+lsH.remove(3).eval());
		
		System.out.println(" Drop(2):"+lsH.drop(2).eval());
		Predicate<Integer> pH1= x->x==4;
		System.out.println(" DropWhile(x==4):"+lsH.dropWhile(pH1).eval());

		System.out.println(" Take(3):"+lsH.take(3).eval());
		Predicate<Integer> pH2 = x->x==4;
		System.out.println(" TakeWhile(x==4):"+lsH.takeWhile(pH2).eval());
		
		System.out.println(" Reemplace(3)->(99):"+lsH.reemplace(3, 99).eval());
		
		System.out.println(" Contain(4):"+lsH.contain(5));
		
		System.out.print(" ForEach(syso::print):");
		lsH.forEach(System.out::print);
		System.out.println();
		System.out.println(" Size():"+lsH.size().eval());
		
	
	}

}
