package org.example;

import unsignedNumbers.UByte;

public class Main {
    public static void main(String[] args) {

        UByte uByte = new UByte(0);
        for (int i = 0; i < 10000; i++) {
            System.out.printf("UByte: %d, i: %d\n",uByte.add(1).getValue(),i);
        }
    }
}