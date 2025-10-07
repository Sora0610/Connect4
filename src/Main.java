package src;
import java.util.Scanner;
import java.lang.System;

public class Main {
    public static void main(String[] argc) {
        Scanner scan = new Scanner(System.in);
        Connect4 game = new Connect4();
        
        // Get input
        System.out.print("X: ");
        int x = scan.nextInt();
        System.out.print("Y: ");
        int y = scan.nextInt();



        scan.close();
    }
}