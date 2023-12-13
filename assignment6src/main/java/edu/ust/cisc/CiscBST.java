package edu.ust.cisc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class CiscBST<E extends Comparable<E>> implements CiscCollection {

    private BSTNode<E> root;
    private int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return contains(root, (E)o);
    }

    private boolean contains(BSTNode<E> node, E value) {
        if(node == null) {
            return false;
        } else if(node.data.compareTo(value) == 0) {
            return true;
        } else if(node.data.compareTo(value) < 0) {
            return contains(node.right, value);
        } else {
            return contains(node.left, value);
        }
    }

    @Override
    public Iterator iterator() {
        return new CiscBSTIterator(root);
    }

    @Override
    public Object[] toArray() {
        List<E> list = new ArrayList<E>();
        toArray(root, list);
        E[] newList = (E[]) new Comparable[size];
        list.toArray(newList);
        return newList;
    }

    private void toArray(BSTNode<E> node, List<E> list) {
        if(node != null) {
            toArray(node.left, list);
            list.add(node.data);
            toArray(node.right, list);
        }
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    public void add(E value) {
        root = add(root, value);

    }

    private BSTNode<E> add(BSTNode<E> node, E value) {
        if(node == null) {
            size++;
            return new BSTNode<E>(value);
        } else if(node.data.compareTo(value) == 0) {
            return node;
        } else if(node.data.compareTo(value) < 0) {
            node.right = add(node.right, value);
            return node;
        } else {
            node.left = add(node.left, value);
            return node;
        }
    }

    public void remove(E value) {
        root = remove(root, value);
    }

    private BSTNode<E> remove(BSTNode<E> node, E value) {
        if(node == null) {
            return null;
        } else if(node.data.compareTo(value) < 0) {
            node.right = remove(node.right, value);
        } else if(node.data.compareTo(value) > 0) {
            node.left = remove(node.left, value);
        } else {
            if(node.right == null) {
                size--;
                return node.left;
            } else if(node.left == null) {
                size--;
                return node.right;
            } else {
                node.data = getMax(node.left);
                node.left = remove(node.left, node.data);
            }
        }
        return node;
    }

    private E getMax(BSTNode<E> node) {
        if(node.right == null) {
            return node.data;
        } else {
            return getMax(node.right);
        }
    }

    private static class BSTNode<E> {
        private E data;
        private BSTNode<E> left;
        private BSTNode<E> right;

        public BSTNode(E data) {
            this(data, null, null);
        }

        public BSTNode(E data, BSTNode<E> left, BSTNode<E> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    private class CiscBSTIterator<E> implements Iterator<E> {

        private Stack<BSTNode<E>> stack;

        private CiscBSTIterator(BSTNode<E> node) {
            this.stack = new Stack<>();
            while(node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public E next() {
            BSTNode<E> node = stack.pop();
            E result = node.data;
            if(node.right != null) {
                node = node.right;
                while(node != null) {
                    stack.push(node);
                    node = node.left;
                }
            }
            return result;
        }
    }
}
