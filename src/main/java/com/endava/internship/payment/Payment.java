package com.endava.internship.payment;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Simple payment entity used in JUnit exercises.
 * <p>
 * It keeps only a few basic fields so it can be tested with assertions such as
 * {@code assertEquals}, {@code assertThrows}, and Hamcrest {@code assertThat}.
 */
public class Payment {

    /**
     * Small set of states that make status transition tests easy to write.
     */
    public enum Status { PENDING, COMPLETED, REFUNDED }

    private final String id;
    private final String from;
    private final String to;
    private final double amount;
    private final LocalDate date;
    private Status status;

    /**
     * Creates a new payment in {@link Status#PENDING} state.
     *
     * @param id unique payment identifier
     * @param from sender name or account
     * @param to receiver name or account
     * @param amount payment amount, must be positive
     * @throws IllegalArgumentException if any text value is blank, if the amount is not positive,
     *                                  or if sender and receiver are the same
     */
    public Payment(String id, String from, String to, double amount) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("id is required");
        if (from == null || from.isBlank()) throw new IllegalArgumentException("from is required");
        if (to == null || to.isBlank()) throw new IllegalArgumentException("to is required");
        if (amount <= 0) throw new IllegalArgumentException("amount must be positive");
        if (from.equals(to)) throw new IllegalArgumentException("from and to must differ");

        this.id = id;
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.date = LocalDate.now();
        this.status = Status.PENDING;
    }

    /** @return the payment identifier */
    public String getId()      { return id; }

    /** @return the sender */
    public String getFrom()    { return from; }

    /** @return the receiver */
    public String getTo()      { return to; }

    /** @return the payment amount */
    public double getAmount()  { return amount; }

    /** @return the creation date */
    public LocalDate getDate() { return date; }

    /** @return the current payment status */
    public Status getStatus()  { return status; }

    /**
     * Marks the payment as completed.
     *
     * @throws IllegalStateException if the payment is not pending
     */
    public void complete() {
        if (status != Status.PENDING) throw new IllegalStateException("Only PENDING payments can be completed");
        status = Status.COMPLETED;
    }

    /**
     * Marks the payment as refunded.
     *
     * @throws IllegalStateException if the payment is not completed
     */
    public void refund() {
        if (status != Status.COMPLETED) throw new IllegalStateException("Only COMPLETED payments can be refunded");
        status = Status.REFUNDED;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payment p)) return false;
        return Objects.equals(id, p.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Payment{" + id + ", " + from + " -> " + to + ", " + amount + ", " + status + '}';
    }
}
