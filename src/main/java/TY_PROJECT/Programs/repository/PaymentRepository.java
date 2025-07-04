package TY_PROJECT.Programs.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import TY_PROJECT.ProgramsController.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment,Long>{
	
	boolean existsByAccountHolderName(String accountHolderName);
	boolean existsByAccountNumber(String accountNumber);
	boolean existsByIfscCode(String ifscCode);
	boolean existsByTransactionPassword(String transactionPassword);
	boolean existsByCardNumber(String cardNumber);
	boolean existsByCvv(String cvv);
	boolean existsByUpiId(String upiId);

}

