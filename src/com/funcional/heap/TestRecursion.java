package com.funcional.heap;

import java.math.BigInteger;


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
	
	//recusion clasica
	static Integer fiboC (Integer n) {
		/*
		if(n==0||n==1) {
			return TailCall.ret(n);
		}else {
			return 
		}
		 */
		return n==0||n==1?n:fiboC(n-1)+fiboC(n-2);
	}
	
	
	//recucion TailCall
	
	/*
	 * fibo(0,1,20)
	 * fibo(1,2,19)
	 * .
	 * .
	 * fibo(18,sum,1)
	 */
	static BigInteger fiboTC (BigInteger n,BigInteger ret,BigInteger sum) {
		if(n.equals(BigInteger.ZERO)) {
			return BigInteger.ZERO;
		}
		else if(n.equals(BigInteger.ONE)){
			return ret.add(sum);	
		}
		else {
			return fiboTC(n.subtract(BigInteger.valueOf(1)),sum,ret.add(sum));
		}	
	}
	static BigInteger fibonacciTC (long n) {
		return fiboTC(BigInteger.valueOf(n), BigInteger.ONE, BigInteger.ZERO);
	}
	
	//recucion en el heap
	
	static TailCall<BigInteger> fiboTcHeap(BigInteger n,BigInteger ret,BigInteger sum) {
		if(n.equals(BigInteger.ZERO)) {
			return TailCall.ret(BigInteger.ZERO) ;
		}
		else if(n.equals(BigInteger.ONE)){
			return TailCall.ret(ret.add(sum));	
		}
		else {
			//Supplier<TailCall<BigInteger>> ret = fibo...
			return TailCall.sus(()->fiboTcHeap(n.subtract(BigInteger.valueOf(1)),sum,ret.add(sum)));
		}	
	}
	static BigInteger fibonacciHeap (int n) {
		return fiboTcHeap(BigInteger.valueOf(n), BigInteger.ONE, BigInteger.ZERO).eval();
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
		TailCall<Integer> sum = sumar2(5, 10_000_000);		
		System.out.println(sum.eval());		
		//System.out.println("Fibo(20) = "+fibo(20));
		//primero la llamada al metodo 
		long n = 50;
		var fibo= fibonacci(n);
		System.out.printf("FiboTC(%s) = %s",n,fibo);
		*/
		int n = 2_000_000;
		var fibo= fibonacciHeap(n);
		System.out.printf("Fibonacci TailcallHeap(%s) = %s",n,fibo);
		
	}

}
