package test.test;

import java.util.ArrayList;

public class Main3 {

    public static void main(String[] args) {

        ArrayList<Double> numbers = new ArrayList<Double>();

        int i = 0;
        while (i < 2) {
            double random = Math.random();
            numbers.add(random);
        }

        int j = 0;
        while (j < numbers.size()) {
            System.out.print(numbers.get(j));
        }












//        wow.Character[] players = new Character[1];
//        int i = 0;
//        while (i < players.length) {
//            players[i] = new Character();
//        }



//        int maxNumber = 0;
//
//        int i = 0;
//
//        while (i < numbers.length) {
//
//            if (numbers[i] > maxNumber) {
//                maxNumber = numbers[i];
//            }
//
//            i++;
//        }
//
//        System.out.print(maxNumber);


    }
}
