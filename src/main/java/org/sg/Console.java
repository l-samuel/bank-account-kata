package org.sg;

public class Console implements Writer {
    @Override
    public void print(String message) {
        System.out.println(message);
    }
}
