package com.funcional.deber1;


public class BinTreeD1 <T> {
	T value;
	BinTreeD1 L;
	BinTreeD1 R;
	
	public static final BinTreeD1 Leaf = new LeafNode();
	
	//-----------contructores-----
	public BinTreeD1(T value) {
		super();
		this.value = value;
		this.L = BinTreeD1.Leaf;
	}
    public BinTreeD1(T value, BinTreeD1 l, BinTreeD1 r) {
		super();
		this.value = value;
		this.L = l;
		this.R = r;
	}
    //------------metodos------------------------
	public Integer size() {
		return isEmpty()?0:1+L.size()+R.size();
	}
    public static <T> BinTreeD1<T> of(T value, BinTreeD1<T> left, BinTreeD1<T> right) {
        return new BinTreeD1<>(value, left, right);
    }
    public boolean isEmpty() {
    	return !(this==Leaf)?true:false;
    }
	public String toString() {
		return String.format("copa:%s\nRama1:%s Rama2:%s\n",value,L,R);
    }
}


class LeafNode<T> extends BinTreeD1<T>{

	public LeafNode() {
		super(null);
		// TODO Auto-generated constructor stub
	}
    public String toString() {
        return String.format("Leaf");
    }
}
