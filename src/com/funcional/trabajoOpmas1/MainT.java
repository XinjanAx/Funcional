package com.funcional.trabajoOpmas1;

import com.funcional.bintree.BinTree;

public class MainT {

	public static void main(String[] args) {
       
        var n4 = BinTree.of(4.0);
        var n2 = BinTree.of(2.0);
        var n10 = BinTree.of(10.0);
        var n5 = BinTree.of(6.0);
        var n3 = BinTree.of(3.0);
        
        var nM = BinTree.of(null,n4,n2);
        var nS = BinTree.of(null,nM,n10);
        var nR = BinTree.of(null,n5,n3);
        
        BinTree<Double> tree_total = BinTree.of(null,nS,nR);
        
              
       
        System.out.println(tree_total); 
        System.out.println("Niveles:"+MainT.level(tree_total)); 
        var res = evaluate(tree_total);
        System.out.println("Total:"+res);
        
        //se puede crear un metodo q contruya el arbol aritmetico y
        //otro metodo q genere el arbol con las respuestas en cada nodo correspondiente
    }

    public static Double evaluate(BinTree<Double> tree) {
    	return tree.left().isLeaf()?evaluateAux(tree, 'r'):evaluateAux(tree, 'l');
    }
    
    public static Double evaluateAux(BinTree<Double> tree,char lado) { 
    	
    	int lvl = MainT.level(tree);
        if (lvl==1) {
            return tree.value();
        }else {
        	double leftResult = evaluateAux(tree.left(),'l');
            double rightResult = evaluateAux(tree.right(),'r');
                
                if(lvl==2&&lado=='l')return leftResult*rightResult;
                else if(lvl==2&&lado=='r')
                	if (rightResult!=0)return Math.round(leftResult/rightResult * 100.0) / 100.0; 
                	else return 0.0;
                else if(lvl==3&&lado=='l')return leftResult+rightResult;
                else if(lvl==3&&lado=='r')return leftResult-rightResult;
                else if(lvl==4&&lado=='l')return leftResult+rightResult;
                else if(lvl==4&&lado=='r')return leftResult-rightResult;
                else return leftResult+rightResult;       	
        }
              
    }
    public static <T> int level(BinTree<T>tree) {    	
        if (tree.isLeaf()) {
            return 0;
        } else {
            int leftLevels = level(tree.left());
            int rightLevels = level(tree.right());
            return 1 + Math.max(leftLevels, rightLevels);
        }
    }

}
