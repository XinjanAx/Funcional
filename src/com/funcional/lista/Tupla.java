package com.funcional.lista;

public class Tupla {
	
	private String x;
	private Integer y;
	
	public String x() {
		return x;
	}
	public Integer y() {
		return y;
	}
	public void setX(String x) {
		this.x = x;
	}
	public void setY(Integer y) {
		this.y = y;
	}
	public void of(String x , Integer Y) {
		this.x = x;
		this.y = y;
	}
	@Override
	public String toString() {
		return String.format("[$x,$y]",x,y);
	}
	

	
	
}
