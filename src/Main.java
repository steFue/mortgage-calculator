import java.text.NumberFormat;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        double principal = 0;
        double annualInterestRate = 0;
        int periodInYears = 0;

        Scanner scanner = new Scanner(System.in);

        do {
            System.out.print("Principal: ");
            principal = scanner.nextDouble();

            if (principal < 1_000 || principal > 1_000_000) {
                System.out.println("Enter a number between 1,000 and 1,000,000 kr: ");
            }
        } while (principal < 1_000 || principal > 1_000_000);

        do {
            System.out.print("Annual Interest Rate: ");
            annualInterestRate = scanner.nextDouble();

            if (annualInterestRate < 1 || annualInterestRate > 30) {
            System.out.println("Enter a value greater than 0 and less than or equal to 30: ");
            }
        } while (annualInterestRate < 1 || annualInterestRate > 30);

        do {
            System.out.print("Period: ");
            periodInYears = scanner.nextInt();

            if (periodInYears < 1 || periodInYears > 30) {
                System.out.print("Enter a value greater than 0 and less than or equal to 30: ");
            }
        } while (periodInYears < 1 || periodInYears > 30);

        double mortgage = calculateMortgage(principal, annualInterestRate, periodInYears);

        String mortgageFormatted = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println("Mortgage: " +  mortgageFormatted);

        scanner.close();

    }
    public static double calculateMortgage (double principal,
                                            double annualInterestRate,
                                            int periodInYears) {
        final byte MONTHS_IN_YEAR = 12;
        final byte PERCENT = 100;

        double monthlyInterest =  annualInterestRate / PERCENT / MONTHS_IN_YEAR;
        int numberOfPayments = periodInYears * MONTHS_IN_YEAR;

        double factor = Math.pow(1 + monthlyInterest, numberOfPayments);
        double mortgage = principal * (monthlyInterest * factor) / (factor - 1);

        return mortgage;
    }
}
