package com.funcional.lista;

import java.util.Objects;

public class Tupla {
	
	private String x;
	private int y;
	
	public Tupla(String x, int y) {
		this.x = x;
		this.y = y;
	}
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
	public void of(String x , int Y) {
		this.x = x;
		this.y = y;
	}
	@Override
	public String toString() {
	return String.format("%s:   %s\n",x,y);
}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tupla other = (Tupla) obj;
		return Objects.equals(x, other.x) && Objects.equals(y, other.y);
	}
	
}
