package com.funcional.deber1;


public class BinTree <T> {
	T value;
	BinTree L;
	BinTree R;
	
	public static final BinTree Leaf = new LeafNode();
	
	//-----------contructores-----
	public BinTree(T value) {
		super();
		this.value = value;
		this.L = BinTree.Leaf;
	}
    public BinTree(T value, BinTree l, BinTree r) {
		super();
		this.value = value;
		this.L = l;
		this.R = r;
	}
    //------------metodos------------------------
	public Integer size() {
		return isEmpty()?0:1+L.size()+R.size();
	}
    public static <T> BinTree<T> of(T value, BinTree<T> left, BinTree<T> right) {
        return new BinTree<>(value, left, right);
    }
    public boolean isEmpty() {
    	return !(this==Leaf)?true:false;
    }
	public String toString() {
		return String.format("copa:%s\nRama1:%s Rama2:%s\n",value,L,R);
    }
}


class LeafNode<T> extends BinTree<T>{

	public LeafNode() {
		super(null);
		// TODO Auto-generated constructor stub
	}
    public String toString() {
        return String.format("Leaf");
    }
}
