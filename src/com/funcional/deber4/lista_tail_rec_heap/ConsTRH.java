package com.funcional.deber4.lista_tail_rec_heap;


public final class ConsTRH<T> implements ListaTRH{
    
	private final T head;
    private final ListaTRH<T> tail;
	
	ConsTRH(T head, ListaTRH<T> tail) {
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
	public ListaTRH<T> tail() {
		// TODO Auto-generated method stub
		return tail;
	}
	
	@Override
	public String toString() {
		return String.format("[%s, %s]", head, tail.toString());
	}

}
