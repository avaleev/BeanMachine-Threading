package BeanMachine;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Arrays;

public class BeanMachine {
    static int ballAmt = 0;
    static int slotsAmt = 0;
    static boolean noErrors = false;

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        try {
            System.out.print("Enter the number of balls to drop: ");
            ballAmt = in.nextInt();
        } catch (InputMismatchException ex) {
            System.out.println("Only integer numbers are allowed. ");
            ex.printStackTrace();
        }

        try {
            System.out.print("Enter the number of slots in the bean machine: ");
            slotsAmt = in.nextInt();
        } catch (InputMismatchException ex) {
            System.out.println("Only integer numbers are allowed. ");
            ex.printStackTrace();
        }

        int[] ballsInSlot = new int[slotsAmt];
        for(int i = 0; i < slotsAmt; i++) {
            ballsInSlot[i] = 0;
        }

        int targetSlot = 0;
        for(int i = 0; i < ballAmt; i++){
            targetSlot = dropBall();
            ++ballsInSlot[targetSlot];

            System.out.println(" -> Ball fell in slot " + targetSlot);
        }
        renderField(ballsInSlot);
    }

    static int dropBall(){
        int side = -1;
        int targetSlot = 0;

        for(int i = 0; i < slotsAmt - 1; i++){
            side = (Math.random() < 0.5) ? 0 : 1;
            System.out.print((side == 0) ? "L" : "R");

            targetSlot += side;
        }

        return targetSlot;
    }

    static void renderField(int[] slots){
        int maxBallsInOneSlot = Arrays.stream(slots).max().getAsInt();

        for(int i = maxBallsInOneSlot + 1; i > 0; i--){
            System.out.print("|");
            for(int j = 0; j < slotsAmt; j++){
                if(slots[j] >= i){
                    System.out.print("O|");
                } else {
                    System.out.print(" |");
                }
            }
            System.out.println();
        }
    }
}
