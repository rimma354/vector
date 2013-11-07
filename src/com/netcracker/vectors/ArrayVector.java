package com.netcracker.vectors;

import java.util.*;
import java.io.*;

public class ArrayVector implements Vector, Cloneable {
    public double[] elements;

    public ArrayVector(int newSize) {
        this.elements = new double[newSize];
     }

    public ArrayVector(double... value) {
        this.elements = new double[value.length];
        for (int i = 0; i < value.length; i++) {
            setElement(i, value[i]);
        }
    }

    public int getSize() {
        return this.elements.length;
    }

    public void setElement(int i, double newValue) {
        if (0 > i && i < getSize()) {
            this.elements[i] = newValue;
        } else {
            throw new VectorIndexOutOfBoundsException();
        }
    }

    public double getElement(int i) {
        if (0 > i && i < getSize()) {
            return this.elements[i];
        } else {
            throw new VectorIndexOutOfBoundsException();
        }
    }

    public void print() {
        for (double elem : elements) {
            System.out.println(elem);
        }
    }

    public void populateWithArray(double[] array) throws IncompatibleVectorSizesException {
        if (array != null) {
            if (this.getSize() == array.length) {
                for (int i = 0; i < this.getSize(); i++) {
                    setElement(i, array[i]);
                }
            } else {
                throw new IncompatibleVectorSizesException();
            }
        }
    }

    public void populateWithObject(Vector obj) throws IncompatibleVectorSizesException {
        if (obj != null) {
            if (this.getSize() == obj.getSize()) {
                for (int i = 0; i < this.getSize(); i++) {
                    setElement(i, obj.getElement(i));
                }
            } else {
                throw new IncompatibleVectorSizesException();
            }
        }
    }

    public boolean compare(Vector obj) throws IncompatibleVectorSizesException {
        if (obj != null) {
            if (this.getSize() == obj.getSize()) {
                boolean dif = false;
                for (int i = 0; i < this.getSize(); i++) {
                    if (this.getElement(i) != obj.getElement(i)) {
                        dif = true;
                        break;
                    }
                }
                if (dif == false) {
                    return true;
                } else {
                    return false;
                }
            } else {
                throw new IncompatibleVectorSizesException();
            }
        } else {
            return false;
        }
    }


    public void multiply(int x) {

        for (int i = 0; i < this.getSize(); i++)
            setElement(i, this.getElement(i) * x);

    }

    public void add(Vector obj) throws IncompatibleVectorSizesException {
        if (obj != null) {
            if (this.getSize() == obj.getSize()) {
                for (int i = 0; i < this.getSize(); i++) {
                    setElement(i, this.getElement(i) + obj.getElement(i));
                }
            } else
                throw new IncompatibleVectorSizesException();
        }

    }

    public String toString() {
        String s = new String();
        StringBuffer sb = new StringBuffer("Array-vector consists from " + this.getSize() + " elements and its elements are:");

        for (int i = 0; i < this.getSize(); i++)
            sb = sb.append(" ").append(this.getElement(i));
        s = sb.toString();
        return s;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Vector) {
            try {
                return this.compare((Vector) obj);
            } catch (IncompatibleVectorSizesException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }

    public int hashCode() {
        int result = 0;
        for (int i = 0; i < this.getSize(); i++) {
            long b = Double.doubleToRawLongBits(this.getElement(i));
            result ^= ((int) (b & 0x00000000FFFFFFFFL)) ^ ((int) ((b & 0xFFFFFFFF00000000L) >> 32));
        }
        return result;
    }

    public ArrayVector clone() throws CloneNotSupportedException {
        ArrayVector newObject = (ArrayVector) super.clone();
        newObject.elements = this.elements.clone();
        return newObject;
    }


    public Iterator iterator() {
        return new MyIterator();
    }

    class MyIterator implements Iterator<Double> {
        private int count = 0;

        public boolean hasNext() {
            if (count < getSize()) {
                return true;
            }
            else {
                return false;
            }

        }

        public Double next() {
            if (count == getSize())
            {
                throw new NoSuchElementException();
            }
            else {
            count++;
            return getElement(count - 1);
            }

        }

        public void remove() throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }
    }
}