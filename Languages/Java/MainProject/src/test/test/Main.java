package test.test;

public class Main {

    public static void main(String[] args) {

        double result = findMaxPrimeFactor(600851475143.0);
        System.out.print(result);

    }

    public static double findMaxPrimeFactor(double number) {
        double maxPrime = 0;

        double factor = 31.0;
        double resultOfDivision;
        while (factor < number / 2) {
            resultOfDivision = number / factor;
            if (resultOfDivision % 2 == 0) {
                factor++;
                continue;
            }

            if (resultOfDivision % 1 == 0) {

                double factorForPrime = 2;
                double resultForPrime;
                boolean isPrime = true;
                while (factorForPrime < resultOfDivision / 2) {
                    resultForPrime = resultOfDivision / factorForPrime;
                    if (resultForPrime % 1 == 0) {
                        isPrime = false;
                        break;
                    }
                    factorForPrime++;
                }

                if (isPrime) {
                    System.out.print(resultOfDivision);
                    System.exit(1);
                }
            }

            factor++;
        }

        return maxPrime;
    }



}
