package com.endava.internship.payment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Small service class used together with {@link Payment} in unit tests.
 * <p>
 * It delegates to {@link Payment#complete()} and {@link Payment#refund()}, which makes it a good
 * candidate for tests that verify object interaction, state changes, exceptions, and list assertions.
 */
public class PaymentService {

    private final List<Payment> payments = new ArrayList<>();

    /**
     * Submits a payment, completes it, and stores it in memory.
     *
     * @param payment the payment to submit
     * @throws IllegalStateException if the payment cannot be completed
     */
    public void submit(Payment payment) {
        payment.complete();
        payments.add(payment);
    }

    /**
     * Refunds a payment that was already completed.
     *
     * @param payment the payment to refund
     * @throws IllegalStateException if the payment is not in completed state
     */
    public void refund(Payment payment) {
        payment.refund();
    }

    /**
     * Returns the submitted payments as an unmodifiable list.
     * This is useful for tests that verify read-only collections.
     *
     * @return immutable view of payments
     */
    public List<Payment> all() {
        return Collections.unmodifiableList(payments);
    }

    /**
     * Calculates the total amount of all currently completed payments.
     * Refunded payments are not included.
     *
     * @return sum of completed payment amounts
     */
    public double totalCompleted() {
        return payments.stream()
                .filter(payment -> payment.getStatus() == Payment.Status.COMPLETED)
                .mapToDouble(Payment::getAmount)
                .sum();
    }

    /**
     * Finds a payment by id.
     *
     * @param id payment identifier
     * @return the matching payment or {@code null} when not found
     */
    public Payment findById(String id) {
        return payments.stream()
                .filter(payment -> payment.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}

