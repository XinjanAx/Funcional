package com.funcional.recursion;


public class TestRecursion {

	static int sumar1(int x, int y) {
		if(y==0) return x;
		else return sumar1(x+1, y-1);
	}
	
	static TailCall<Integer> sumar2(Integer x, Integer y) {
		if(y==0) {
			return TailCall.ret(x);
		}
		else {	
			return TailCall.sus(()-> sumar2(x+1, y-1));		
		}
	}
	
	public static void main(String[] args) {
		/*
		Return<Integer> ret = new Return<Integer>(5);
		Suspend<Integer> n1 = new Suspend<>(ret);
		Suspend<Integer> n2 = new Suspend<>(n1);
		Suspend<Integer> ini = new Suspend<>(n2);
		
		TailCall<Integer> tmp = ini;
		while(tmp.isSuspended()) {
			tmp=tmp.resume();
			System.out.println(".i.");
		}
		System.out.println(tmp);
		*/
		TailCall<Integer> sum = sumar2(5, 10_000_000);
		
		System.out.println(sum.eval());
		
		
	}

}
