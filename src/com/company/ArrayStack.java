package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ArrayStack {
    ArrayList stack;

    public ArrayStack() {
        stack = new ArrayList();

    }

    public int size() {
        return stack.size();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public void clear() {
        stack.clear();
    }

    public void push(Object e) {
        stack.add(e);
    }

    public Object peek() {
        if(stack.isEmpty())
            return null;
        return stack.get(stack.size() - 1);
    }

    public Object pop() {
        if(stack.isEmpty())
            return null;
        return stack.remove(stack.size() - 1);
    }
}

class BlockArrayStack extends ArrayStack{

    public Object peek()
    {
        synchronized(this) {
            while (stack.isEmpty()) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return stack.get(stack.size()-1);
    }

    public Object pop() {
        synchronized (this) {
            while (stack.isEmpty()) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return stack.remove(stack.size() - 1);
    }

    public void push(Object e) {
        synchronized(this) {
            stack.add(e);
            notify();
        }
    }
    
}