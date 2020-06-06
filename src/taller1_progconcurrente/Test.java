/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taller1_progconcurrente;

/**
 *
 * @author Leonardo González G.
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        
        long numDiv = DivisorConc.numOfPosDivisors(19876543937L, 20);
        
        System.out.println("Numero de divisores: "+numDiv);
        
        
    }
    
}
