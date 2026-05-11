package com.endava.internship.patterns.singleton;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class DriverSingletonTest {

    @Test
    void driverReturnsSameInstanceAcrossConcurrentCalls() throws Exception {
        List<Driver> instances = collectInstances(Driver::getInstance);

        assertEquals(1, Set.copyOf(instances).size());
        assertSame(instances.getFirst(), instances.getLast());
    }

    @Test
    void driverThreadSafeReturnsSameInstanceAcrossConcurrentCalls() throws Exception {
        List<DriverThreadSafe> instances = collectInstances(DriverThreadSafe::getInstance);

        assertAll(
                () -> assertEquals(1, Set.copyOf(instances).size()),
                () -> assertSame(instances.getFirst(), instances.getLast())
        );
    }

    private static <T> List<T> collectInstances(Callable<T> supplier) throws Exception {
        int threadCount = 8;
        CountDownLatch ready = new CountDownLatch(threadCount);
        CountDownLatch start = new CountDownLatch(1);
        try (ExecutorService executor = Executors.newFixedThreadPool(threadCount)) {
            List<Future<T>> futures = new ArrayList<>();
            for (int i = 0; i < threadCount; i++) {
                futures.add(executor.submit(() -> {
                    ready.countDown();
                    start.await();
                    return supplier.call();
                }));
            }

            ready.await();
            start.countDown();

            List<T> instances = new ArrayList<>();
            for (Future<T> future : futures) {
                instances.add(future.get());
            }
            return instances;
        }
    }
}


