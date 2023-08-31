package com.funcional.lista;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainTextoD1 {
	
	//funcion
	static <T,U,V> Function<Function<T, U>,Function<Function<U, V>, Function<T, V>>> compH(){
		return f-> g -> x -> g.apply(f.apply(x));
	}
	
	//metodo funcional
    static Function<Integer, Integer> composicion(Function<Integer, Integer> f, Function<Integer, Integer> g){
        return x -> g.apply(f.apply(x));
    }
    
    public static Lista<Tupla> contar2(String[] vstr) {
		List<Tupla> ls = Stream.of(vstr)
		.collect(
				Collectors.groupingBy(Function.identity(),Collectors.counting())
				)
		.entrySet().stream()
		.map(
			s->new Tupla(s.getKey(),s.getValue().intValue())
				)
		.collect(Collectors.toList());
		
		Lista<Tupla> ret = Lista.NIL;
		for(Tupla p : ls) {
			ret = ret.prepend(p);
		}
		return ret;
	}
	
	public static void main(String[] args) {
		
		String texto = "hola mundo hola prro wachisn hola Como te va hola";
		Function<String, String[]>fn1 = str -> str.split(" ");
		
		Function<String[],Lista<Tupla>> fn2 = vstr ->{
			
            Lista<Tupla> ret = Lista.NIL;
            for(String s : vstr) {

            	Tupla p = new Tupla(s, 1);
                var tmp = ret.contain(p);
                if(tmp.isEmpty()) {
                    //no lo encontro
                    ret = ret.prepend(p);
                } else {
                    //encontro
                	int u =(int) tmp.get().y();
                	int t = u + 1;
                    ret = ret.reemplace(tmp.get(), new Tupla(s,t));
                }
            }
            return ret;
            
		};
		var fog = MainTextoD1.<String,String[],Lista<Tupla>>compH().apply(fn1).apply(fn2);
		Lista<Tupla> cc = fog.apply(texto);
		cc.forEach(s -> {
            System.out.printf("%-10s: %s\n",s.x(),s.y());
            }
		);
	
	}

}
