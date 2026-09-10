package com.devops.loan;

public class LoanApplication {
    private String applicationId;
    private Customer customer;
    private double requestedAmount;

    public LoanApplication(String applicationId, Customer customer, double requestedAmount) 
            throws InvalidLoanApplicationException {
        if (requestedAmount <= 0) {
            throw new InvalidLoanApplicationException("Requested loan amount must be positive.");
        }
        this.applicationId = applicationId;
        this.customer = customer;
        this.requestedAmount = requestedAmount;
    }

    public Customer getCustomer() { return customer; }
    public double getRequestedAmount() { return requestedAmount; }
}
