package com.devops.loan;

public class InvalidLoanApplicationException extends Exception {
    public InvalidLoanApplicationException(String message) {
        super(message);
    }
}
