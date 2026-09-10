package com.devops.loan;

public class Customer {
    private String id;
    private String name;
    private int age;
    private String govtId;
    private double monthlyIncome;
    private int creditScore;
    private double existingMonthlyDebt;

    public Customer(String id, String name, int age, String govtId, 
                    double monthlyIncome, int creditScore, double existingMonthlyDebt) 
                    throws InvalidLoanApplicationException {
        if (age < 0 || monthlyIncome < 0 || creditScore < 0 || existingMonthlyDebt < 0) {
            throw new InvalidLoanApplicationException("Numeric values cannot be negative.");
        }
        if (govtId == null || govtId.trim().isEmpty()) {
            throw new InvalidLoanApplicationException("Government ID cannot be empty.");
        }
        this.id = id;
        this.name = name;
        this.age = age;
        this.govtId = govtId;
        this.monthlyIncome = monthlyIncome;
        this.creditScore = creditScore;
        this.existingMonthlyDebt = existingMonthlyDebt;
    }

    public int getAge() { return age; }
    public String getGovtId() { return govtId; }
    public double getMonthlyIncome() { return monthlyIncome; }
    public int getCreditScore() { return creditScore; }
    public double getExistingMonthlyDebt() { return existingMonthlyDebt; }
}
