package rs.advig.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.advig.rest.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
