package com.funcional.correccion;

import com.funcional.bintree.BinTree;
import com.funcional.lista.Lista;

public class MainCorreccion {

    static<T> Lista<T> preOrder(BinTree<T> tree){

        if(tree == BinTree.Leaf) {
            return Lista.NIL;
        }else {
            var lsLeft = preOrder(tree.left());
            var lsRight = preOrder(tree.right());
            var tmp = lsLeft.concat(lsRight);
            return Lista.of(tree.value(), tmp);
        }
    }
    
    static<T> Lista<T> inOrder(BinTree<T> tree){

        if(tree == BinTree.Leaf) {
            return Lista.NIL;
        }else {
            var lsLeft = inOrder(tree.left());
            var lsRight = inOrder(tree.right());
            var tmp = lsLeft.append(tree.value()).concat(lsRight);
            return tmp;
        }
    }
    

    static<T> Lista<T> postOrder(BinTree<T> tree){
        if(tree == BinTree.Leaf) {
            return Lista.NIL;
        }else {
            var lsLeft = postOrder(tree.left());
            var lsRight = postOrder(tree.right());      
            var tmp = lsRight.append(tree.value());
            return lsLeft.concat(tmp);
        }
    }

    public static void main(String[] args) {

        // TODO Auto-generated method stub

        var n2 = BinTree.of(2);
        var n9 = BinTree.of(9);
        var n5 = BinTree.of(5, n9, BinTree.Leaf);
        var n1 = BinTree.of(1, n2, n5);     
        var tree = n1;

        System.out.println(tree);

        //01
        System.out.println("pre-order: " + preOrder(tree));      

        //02
        System.out.println("in-order: " + inOrder(tree));

        //03
        System.out.println("post-order: " + postOrder(tree));

        //04
        System.out.println("forEach: ");
        tree.forEach(System.out::print);
    }
}