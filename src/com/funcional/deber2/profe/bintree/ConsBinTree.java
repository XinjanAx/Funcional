package com.funcional.deber2.profe.bintree;

public final class ConsBinTree<T> implements BinTree<T> {
	
	private T value;
	private BinTree<T> left;
	private BinTree<T> right;

	
	public ConsBinTree(T value, BinTree<T> left, BinTree<T> right) {
		super();
		this.value = value;
		this.left = left;
		this.right = right;
	}
	
	public ConsBinTree(T value) {
		this.value = value;
		this.left = BinTree.Leaf;
		this.right = BinTree.Leaf;
	}

	@Override
	public T value() {
		// TODO Auto-generated method stub
		return this.value;
	}

	@Override
	public BinTree<T> left() {
		// TODO Auto-generated method stub
		return this.left;
	}

	@Override
	public BinTree<T> right() {
		// TODO Auto-generated method stub
		return this.right;
	}

	@Override
	public String toString() {
		return String.format("copa:%s\nRamaL:%s / RamaR:%s\n",value,left,right);
	}

	@Override
	public Integer size() {
		// TODO Auto-generated method stub
		return 1 + this.left.size()+this.right.size();
	}
	
	

}
