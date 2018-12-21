package com.suremoon.start_param;

public class PopOnlyQueue<T> {
    T[] array;
    int index;
    int size;
    public PopOnlyQueue(T[] array) {
        this.array = array;
        index = 0;
        size = array.length;
    }

    public T pop(){
        if(index >= array.length){
            throw new IndexOutOfBoundsException();
        }
        --size;
        return array[index++];
    }

    public T topSee(){
        if(index >= array.length){
            throw new IndexOutOfBoundsException();
        }
        return array[index];
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }
}
