package com.funcional.bintree;

import java.util.function.Consumer;

import com.funcional.lista.Lista;

public sealed interface BinTree<T> permits ConsBinTree,LeafBinTree {
		
	T value();
	BinTree<T> left();
	BinTree<T> right();
	Integer size();
	final BinTree Leaf = new LeafBinTree();
	 
	
	//----------------contructores
	static <T> BinTree<T> of (T value,BinTree left, BinTree right) {
		return new ConsBinTree<>(value, left, right);
	}
	static <T> BinTree<T> of (T value) {
		return new ConsBinTree<>(value);
	}
	//------------------metodos
	static <T> BinTree<T> buildTree(Lista<T> lista) {
		if(lista.isEmpty())return BinTree.Leaf;
		else {
			int k = lista.size()/2;
			var l = lista.tail().take(k);
			var r = lista.tail().drop(k);          
			return BinTree.of(lista.head(), buildTree(l), buildTree(r));
		}	
	}
	
    default <T> void forEach(Consumer<T> fn) {
    	if(!(this==BinTree.Leaf)) {
    		fn.accept((T) value());
    		this.left().forEach(fn);
    		this.right().forEach(fn);
    	}
	
    }
    default <T> boolean isLeaf() {
    	return this==BinTree.Leaf?true:false;
    }
}

final class LeafBinTree<T> implements BinTree<T>{

	public LeafBinTree() {
		super();
	}
	
	@Override
	public String toString() {
		return "Leaf";
	}

	@Override
	public T value() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BinTree<T> left() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BinTree<T> right() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer size() {
		// TODO Auto-generated method stub
		return 0;
	}	
}
