package ru.kuryakin;

import java.util.*;

public class SplayTree<T extends Comparable<T>> extends AbstractSet<T> {

    private Node<T> root;

    private int size = 0;

    private class Node<T>{
        private T value;
        private Node<T> left, right;

        public Node(T value){
            this.value   = value;
        }
    }

    public boolean contains(T value){
        return get(value) != null;
    }

    public T get(T value){
        root = splay(root, value);
        int cmp = value.compareTo(root.value);
        if (cmp == 0)
            return root.value;
        else
            return null;
    }


    @Override
    public boolean add(T value){

        if (root == null){
            root = new Node<T>(value);
            size++;
            return true;
        }

        root = splay(root, value);

        int cmp = value.compareTo(root.value);

        if (cmp < 0){
            Node<T> n = new Node<T>(value);
            n.left = root.left;
            n.right = root;
            root.left = null;
            root = n;
            size++;
            return true;
        }
        else if (cmp > 0){
            Node<T> n = new Node<T>(value);
            n.right = root.right;
            n.left = root;
            root.right = null;
            root = n;
            size++;
            return true;
        }
        return false;
    }

    public boolean remove(T value){
        if (root == null)
            return false;

        root = splay(root, value);

        int cmp = value.compareTo(root.value);

        if (cmp == 0){
            if (root.left == null){
                root = root.right;
                size--;
                return true;

            }
            else{
                Node<T> x = root.right;
                root = root.left;
                splay(root, value);
                root.right = x;
                size--;
                return true;
            }
        }
        return false;
    }

    public boolean splay(T value){
        root = splay(root, value);
        if (root.value == value) {
            return true;
        }else {
            return false;
        }
    }

    private Node<T> splay(Node<T> node, T value){
        if (node == null) return null;

        int cmp1 = value.compareTo(node.value);

        if (cmp1 < 0) {

            if (node.left == null){
                return node;
            }
            int cmp2 = value.compareTo(node.left.value);
            if (cmp2 < 0){
                node.left.left = splay(node.left.left, value);
                node = rotateRight(node);
            }
            else if (cmp2 > 0){
                node.left.right = splay(node.left.right, value);
                if (node.left.right != null)
                    node.left = rotateLeft(node.left);
            }

            if (node.left == null)
                return node;
            else
                return rotateRight(node);
        }
        else if (cmp1 > 0){

            if (node.right == null){
                return node;
            }

            int cmp2 = value.compareTo(node.right.value);
            if (cmp2 < 0){
                node.right.left  = splay(node.right.left, value);
                if (node.right.left != null)
                    node.right = rotateRight(node.right);
            }
            else if (cmp2 > 0){
                node.right.right = splay(node.right.right, value);
                node = rotateLeft(node);
            }

            if (node.right == null)
                return node;
            else
                return rotateLeft(node);
        }
        else return node;
    }

    private Node<T> rotateRight(Node<T> node){
        Node<T> x = node.left;
        node.left = x.right;
        x.right = node;
        return x;
    }

    private Node<T> rotateLeft(Node<T> node){
        Node<T> x = node.right;
        node.right = x.left;
        x.left = node;
        return x;
    }

    @Override
    public Iterator<T> iterator() {
        return new SplayTreeIterator();
    }

    @Override
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return root == null;
    }

    private class SplayTreeIterator implements Iterator<T> {

        private final Stack<Node<T>> nodes;

        public SplayTreeIterator(){
            this.nodes = new Stack<Node<T>>();
            pushLeft(root);
        }

        @Override
        public boolean hasNext() {
            return !nodes.isEmpty();
        }


        @Override
        public T next() {
            Node<T> node = nodes.pop();
            if (node != null){
                pushLeft(node.right);
                return node.value;
            }
            throw new NoSuchElementException();

        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        private void pushLeft(Node<T> node) {
            while (node != null) {
                nodes.push(node);
                node = node.left;
            }
        }
    }
}