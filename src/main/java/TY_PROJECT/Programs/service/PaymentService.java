package TY_PROJECT.Programs.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TY_PROJECT.Programs.repository.PaymentRepository;
import TY_PROJECT.ProgramsController.entity.Payment;

@Service
public class PaymentService {
	@Autowired
	private PaymentRepository paymentRepository;

    public void processPayment(Payment payment) {
        resetUnusedFields(payment); 

        switch (payment.getPaymentMethod()) {
            case "credit_card":
                processCreditCardPayment(payment);
                break;
            case "debit_card":
                processDebitCardPayment(payment);
                break;
            case "upi":
                processUPIPayment(payment);
                break;
            case "net_banking":
                processNetBankingPayment(payment);
                break;
            default:
                throw new IllegalArgumentException("Invalid payment method selected.");
        }
    }

  
    private void resetUnusedFields(Payment payment) {
        String paymentMethod = payment.getPaymentMethod();

        if (!"credit_card".equals(paymentMethod) && !"debit_card".equals(paymentMethod)) {
            payment.setCardNumber("N/A");
            payment.setExpiryDate("N/A");
            payment.setCvv("N/A");
        }

        if (!"upi".equals(paymentMethod)) {
            payment.setUpiId("N/A");
        }

        if (!"net_banking".equals(paymentMethod)) {
            payment.setBank("N/A");
        }

       
        if (!"credit_card".equals(paymentMethod) && !"debit_card".equals(paymentMethod) &&
            !"upi".equals(paymentMethod) && !"net_banking".equals(paymentMethod)) {
            payment.setAccountHolderName("N/A");
            payment.setAccountNumber("N/A");
            payment.setIfscCode("N/A");
            payment.setTransactionPassword("N/A");
        }
    }

    private void processCreditCardPayment(Payment payment) {
        System.out.println("Processing Credit Card Payment...");
    }

    private void processDebitCardPayment(Payment payment) {
        System.out.println("Processing Debit Card Payment...");
    }

    private void processUPIPayment(Payment payment) {
        System.out.println("Processing UPI Payment...");
    }

    private void processNetBankingPayment(Payment payment) {
        System.out.println("Processing Net Banking Payment...");
    }
    
}
