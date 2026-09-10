package com.devops.loan;

import java.util.ArrayList;
import java.util.List;

public class CreditAssessment {

    public enum RiskClassification {
        LOW_RISK,
        MEDIUM_RISK,
        HIGH_RISK_REJECTED
    }

    public static class AssessmentResult {
        private final RiskClassification riskClassification;
        private final double maxPermissibleLoan;
        private final double dti;
        private final List<String> rejectionReasons;

        public AssessmentResult(RiskClassification riskClassification, double maxPermissibleLoan, 
                                double dti, List<String> rejectionReasons) {
            this.riskClassification = riskClassification;
            this.maxPermissibleLoan = maxPermissibleLoan;
            this.dti = dti;
            this.rejectionReasons = rejectionReasons;
        }

        public RiskClassification getRiskClassification() { return riskClassification; }
        public double getMaxPermissibleLoan() { return maxPermissibleLoan; }
        public double getDti() { return dti; }
        public List<String> getRejectionReasons() { return rejectionReasons; }
    }

    public AssessmentResult processApplication(LoanApplication app) {
        Customer c = app.getCustomer();
        List<String> reasons = new ArrayList<>();

        double minIncomeThreshold = 3000.0;
        int minCreditScoreThreshold = 600;
        double maxAllowedDti = 0.40;

        // Maximum permissible loan limit calculated as 10x monthly income
        double maxPermissibleLoan = c.getMonthlyIncome() * 10;
        
        // Debt-to-Income (DTI) ratio calculation
        double dti = c.getMonthlyIncome() > 0 ? (c.getExistingMonthlyDebt() / c.getMonthlyIncome()) : 1.0;

        // Rule evaluation with multiple rejection reasons collection
        if (c.getAge() < 21) {
            reasons.add("Customer age (" + c.getAge() + ") is below minimum requirement of 21.");
        }
        if (c.getMonthlyIncome() < minIncomeThreshold) {
            reasons.add("Monthly income (" + c.getMonthlyIncome() + ") is below threshold of " + minIncomeThreshold);
        }
        if (c.getCreditScore() < minCreditScoreThreshold) {
            reasons.add("Credit score (" + c.getCreditScore() + ") is below threshold of " + minCreditScoreThreshold);
        }
        if (dti > maxAllowedDti) {
            reasons.add("DTI ratio (" + String.format("%.2f", dti * 100) + "%) exceeds limit of 40%.");
        }
        if (app.getRequestedAmount() > maxPermissibleLoan) {
            reasons.add("Requested amount (" + app.getRequestedAmount() + ") exceeds limit of " + maxPermissibleLoan);
        }

        if (!reasons.isEmpty()) {
            return new AssessmentResult(RiskClassification.HIGH_RISK_REJECTED, maxPermissibleLoan, dti, reasons);
        }

        if (c.getCreditScore() >= 750 && dti <= 0.25) {
            return new AssessmentResult(RiskClassification.LOW_RISK, maxPermissibleLoan, dti, reasons);
        } else {
            return new AssessmentResult(RiskClassification.MEDIUM_RISK, maxPermissibleLoan, dti, reasons);
        }
    }
}
