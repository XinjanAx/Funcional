package com.funcional.listatailrecheap;


public final class ConsTR<T> implements ListaTR{
    
	private final T head;
    private final ListaTR<T> tail;
	
	ConsTR(T head, ListaTR<T> tail) {
		super();
		this.head = head;
		this.tail = tail;
	}

	@Override
	public T head() {
		// TODO Auto-generated method stub
		return head;
	}

	@Override
	public ListaTR<T> tail() {
		// TODO Auto-generated method stub
		return tail;
	}
	
	@Override
	public String toString() {
		return String.format("[%s, %s]", head, tail.toString());
	}

}
