package com.devops.loan;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CreditAssessmentTest {

    private final CreditAssessment assessment = new CreditAssessment();

    @Test
    public void testLowRiskLoanApproval() throws Exception {
        Customer customer = new Customer("C101", "John", 30, "GOVT1234", 8000.0, 780, 1000.0);
        LoanApplication app = new LoanApplication("L101", customer, 40000.0);
        
        CreditAssessment.AssessmentResult result = assessment.processApplication(app);
        assertEquals(CreditAssessment.RiskClassification.LOW_RISK, result.getRiskClassification());
        assertTrue(result.getRejectionReasons().isEmpty());
    }

    @Test
    public void testBoundaryConditionsExactThresholds() throws Exception {
        // Test boundary values: age = 21, credit score = 600, DTI = 40% (2000 / 5000)
        Customer customer = new Customer("C102", "Jane", 21, "GOVT5678", 5000.0, 600, 2000.0);
        LoanApplication app = new LoanApplication("L102", customer, 50000.0);

        CreditAssessment.AssessmentResult result = assessment.processApplication(app);
        assertEquals(CreditAssessment.RiskClassification.MEDIUM_RISK, result.getRiskClassification());
    }

    @Test
    public void testMultipleRejectionReasons() throws Exception {
        Customer customer = new Customer("C103", "Mark", 18, "GOVT9999", 2000.0, 500, 1000.0);
        LoanApplication app = new LoanApplication("L103", customer, 30000.0);

        CreditAssessment.AssessmentResult result = assessment.processApplication(app);
        assertEquals(CreditAssessment.RiskClassification.HIGH_RISK_REJECTED, result.getRiskClassification());
        assertTrue(result.getRejectionReasons().size() >= 3);
    }
}
