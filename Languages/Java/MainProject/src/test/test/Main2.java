package test.test;

public class Main2 {

    public static void main(String[] args) {


        int x = 0;

        while (x < 100) {
            x++;
            if (x == 80) {
                x = x / 0;
            }
        }

    }


}
