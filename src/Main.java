import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        double principal = readNumber("Principal: ", 1000, 1_000_000, scanner);
        double annualInterestRate = readNumber("Annual Interest Rate: ", 1,30, scanner);
        int periodInYears = readNumberIntoYears("Period (years): ", 1, 30, scanner);

        double mortgage = calculateMortgage(principal, annualInterestRate, periodInYears);

        String mortgageFormatted = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println("Mortgage: " +  mortgageFormatted);

        scanner.close();

    }

    public static int readNumberIntoYears (String prompt, int min, int max, Scanner input) {
        int number = 0;

        do {
            System.out.print(prompt);
            if (!input.hasNextInt()) {
                System.out.println("Enter a value between " + min + " and " + max + ": ");
                input.next();
            } else {
                number = input.nextInt();
                if (number < min || number > max) {
                    System.out.println("Enter a value between " + min + " and " + max + ": ");
                }
            }
        } while (number < min || number > max);
        return number;
    }

    public static double readNumber (String prompt, double min, double max, Scanner input) {
        double number;

        do {
            System.out.print(prompt);
            number = input.nextDouble();

            if (number < min || number > max) {
                System.out.println("Enter a value between " + min + " and " + max + ": ");
            }
        } while (number < min || number > max);
        return number;
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
