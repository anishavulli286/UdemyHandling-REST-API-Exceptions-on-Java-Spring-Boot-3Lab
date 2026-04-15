package com.myloanz.partnership.repository;

import com.myloanz.partnership.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface LoanRepository extends JpaRepository<Loan, UUID> {

    Optional<Loan> findByLoanIdAndCreatedBy(UUID loanId, String partnerSecret);

}
