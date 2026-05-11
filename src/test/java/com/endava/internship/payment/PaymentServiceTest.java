package com.endava.internship.payment;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class PaymentServiceTest {

    private PaymentService service;
    private Payment payment;

    @BeforeEach
    void setUp() {
        service = new PaymentService();
        payment = new Payment("P-100", "Alice", "Bob", 125.50);
    }

    @AfterEach
    void tearDown() {
        assertNotNull(service);
    }

    @Test
    @DisplayName("Payment starts in pending status with the provided values")
    void paymentCreationHasExpectedValues() {
        assertAll(
                () -> assertEquals("P-100", payment.getId()),
                () -> assertEquals("Alice", payment.getFrom()),
                () -> assertEquals("Bob", payment.getTo()),
                () -> assertEquals(Payment.Status.PENDING, payment.getStatus()),
                () -> assertEquals(java.time.LocalDate.now(), payment.getDate())
        );
    }

    @Test
    @DisplayName("Submitting a payment completes it and stores it in the service")
    void submitCompletesAndStoresPayment() {
        service.submit(payment);

        assertEquals(Payment.Status.COMPLETED, payment.getStatus());
        assertEquals(payment, service.findById("P-100"));
        assertIterableEquals(List.of(payment), service.all());
        assertThat(service.totalCompleted(), is(closeTo(125.50, 0.0001)));
    }

    @Test
    @DisplayName("Refunding a completed payment changes its status")
    void refundChangesStatus() {
        service.submit(payment);

        service.refund(payment);

        assertEquals(Payment.Status.REFUNDED, payment.getStatus());
        assertThat(service.totalCompleted(), is(closeTo(0.0, 0.0001)));
    }

    @Test
    @DisplayName("Creating a payment with invalid input throws an exception")
    void invalidPaymentThrowsException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Payment("", "Alice", "Alice", 0)
        );

        assertEquals("id is required", exception.getMessage());
    }

    @Test
    @DisplayName("Returned payments list is read only")
    void allReturnsUnmodifiableList() {
        service.submit(payment);

        assertThrows(UnsupportedOperationException.class, () -> service.all().add(payment));
    }
}

