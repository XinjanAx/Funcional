package com.funcional.lista;

import java.util.Objects;

record Tupla<T,U> (T x ,U y){
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
