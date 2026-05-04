package com.endava.internship.payment;

import java.time.LocalDate;
import java.util.Objects;

public class Payment {

    public enum Status { PENDING, COMPLETED, REFUNDED }

    private final String id;
    private final String from;
    private final String to;
    private final double amount;
    private final LocalDate date;
    private Status status;

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

    public String getId()      { return id; }
    public String getFrom()    { return from; }
    public String getTo()      { return to; }
    public double getAmount()  { return amount; }
    public LocalDate getDate() { return date; }
    public Status getStatus()  { return status; }

    public void complete() {
        if (status != Status.PENDING) throw new IllegalStateException("Only PENDING payments can be completed");
        status = Status.COMPLETED;
    }

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
