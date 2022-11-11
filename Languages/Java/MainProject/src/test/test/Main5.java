package test.test;


import java.util.Scanner;

public class Main5 {


	public static void main(String[] args) {

//		Scanner scanner = new Scanner(System.in);

//		double investmentAmount = scanner.nextInt();
//		double monthlyInterestRate = scanner.nextDouble();
//		double numberOfYears = scanner.nextInt();

		double investmentAmount = 1000;
		double monthlyInterestRate = 4.25;
		double numberOfYears = 1;

		double futureInvestmentValue = investmentAmount * (Math.pow((1 + (monthlyInterestRate / 100.0)), (numberOfYears * 12)));

		double futureInvestmentValueToPrint = (int) (futureInvestmentValue * 100) / 100.0;

		System.out.println("Accumulated value is " + futureInvestmentValueToPrint);


	}




}
