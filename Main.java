package com.company;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

       int principal = (int)readNumber("Principal ($1K - $1M): ", 1000, 1_000_000);
       float annualInterestRate =  (float)readNumber("Annual Interest Rate: ", 0.0,30.0);
       byte period = (byte)readNumber("Period(Years): ", 1,30);

       float r =(annualInterestRate/100)/12;
       int numberOfPayments = period*12;


       System.out.println("\nMORTGAGE \n--------");
       double mortgage = mortgageCalculations(annualInterestRate, period, principal, r, numberOfPayments);
       double mortgageSchedule = mortgagePayments(principal, r, numberOfPayments);
       System.out.println("\nPAYMENT SCHEDULE\n-----------------\n");
    }
    public  static double readNumber(String prompt, double min, double max){
        Scanner scanner = new Scanner(System.in);
        System.out.print(prompt);
        double value = scanner.nextDouble();
        while ((value<min) || (value>max)) {
            System.out.println("Enter a value between " + min + " and " + max);
            System.out.print(prompt);
            value = scanner.nextDouble();
            if (value>=min && value<=max)
                break;
        }
        return value;
    }
    public static double mortgageCalculations(float annualInterestRate, byte period, int principal, float r, int numberOfPayments){
        double x = Math.pow((r+1), numberOfPayments);
        double mortgage = principal*( (r*x)/(x-1) );
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        String result = currency.format(mortgage);
        System.out.println("MONTHLY PAYMENTS: " + result);
        return mortgage;
    }

    public static double mortgagePayments(int principal, float r, int numberOfPayments){
        double var1 = Math.pow( (1+r), numberOfPayments );
        int numberOfPaymentsMade =1;
        double mortgageSchedule=1;
        double var2 = 1;
        NumberFormat mortgageCurrency = NumberFormat.getCurrencyInstance();
        for (int i = numberOfPaymentsMade; i<=numberOfPayments; i++) {
            var2= var2 = Math.pow( (r+1), numberOfPaymentsMade );
            mortgageSchedule = principal * (var1 - var2) / (var1 - 1);
            String payment = mortgageCurrency.format(mortgageSchedule);
            System.out.println(payment);
            numberOfPaymentsMade++;
        }
        return  mortgageSchedule;
    }
}
