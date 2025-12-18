import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        double principal = readNumber("Principal: ", 1000, 1_000_000, scanner);
        double annualInterestRate = readNumber("Annual Interest Rate: ", 1,30, scanner);
        int periodInYears = readNumberIntoYears("Period (years): ", 1, 30, scanner);

        double monthlyPayment = calculateMortgage(principal, annualInterestRate, periodInYears);

        String mortgageFormatted = NumberFormat.getCurrencyInstance().format(monthlyPayment);
        System.out.println("MORTGAGE\n"+"--------\n" + "Monthly Payments: " +   mortgageFormatted);
        printPaymentSchedule(principal, annualInterestRate, periodInYears, monthlyPayment);


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
        double number = 0;

        do {
            System.out.print(prompt);
            if (!input.hasNextDouble()) {
                System.out.println("Enter a value between " + min + " and " + max + ": ");
                input.next();
            } else {
                number = input.nextDouble();
                if (number < min || number > max) {
                    System.out.println("Enter a value between " + min + " and " + max + ": ");
                }
            }
        } while (number < min || number > max);
        return number;
    }

    public static double calculateMortgage (double principal,
                                            double annualInterestRate,
                                            int periodInYears) {
        final int MONTHS_IN_YEAR = 12;
        final int PERCENT = 100;

        double monthlyInterestRate =  annualInterestRate / PERCENT / MONTHS_IN_YEAR;
        int numberOfPayments = periodInYears * MONTHS_IN_YEAR;

        double factor = Math.pow(1 + monthlyInterestRate, numberOfPayments);
        double monthlyPayment = principal * (monthlyInterestRate * factor) / (factor - 1);

        return monthlyPayment;
    }
    public static void printPaymentSchedule (double principal,
                                             double annualInterestRate,
                                             int periodInYears,
                                             double monthlyPayment) {

        final int MONTHS_IN_YEAR = 12;
        final int PERCENT = 100;

        double monthlyInterestRate =  annualInterestRate / PERCENT / MONTHS_IN_YEAR;
        int numberOfPayments = periodInYears * MONTHS_IN_YEAR;

        double balance = principal;
        double totalInterest = 0;

        System.out.println();
        System.out.println("PAYMENT SCHEDULE");
        System.out.println("----------------");


        for (int month = 1; month <= numberOfPayments; month++) {
            double interest = balance * monthlyInterestRate;
            double principalPaid = monthlyPayment - interest;

            if (principalPaid > balance) {
                principalPaid = balance;
            }

            balance -= principalPaid;
            totalInterest += interest;

            System.out.println(NumberFormat.getCurrencyInstance().format(balance));

            if (balance <= 0.0000001) {
                break;
            }
        }

        System.out.println("---------------");
        System.out.println("Total interest paid: " + NumberFormat.getCurrencyInstance().format(totalInterest));

    }
}
