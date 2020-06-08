/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import taller1_progconcurrente.DivisorConc;

/**
 *
 * @author Leonardo González G.
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();

        long numDiv = DivisorConc.numOfPosDivisors(1987654393L, 20);
        long endTime = System.currentTimeMillis();
        System.out.println("Numero de divisores: " + numDiv);

        long diffTime = (endTime - startTime);

        System.out.println("Time: " + diffTime / 1000f);

    }

}
