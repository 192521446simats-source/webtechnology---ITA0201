package com.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * CO3 EXP 10 WEB - Thread-Safe Concurrent Visitor Counter
 *
 * A single servlet instance is created by the container and REUSED to serve
 * every incoming request, each on its own request-handling thread pulled
 * from Tomcat's thread pool. That means any mutable field declared on this
 * class is shared, concurrently, by every thread currently handling a
 * request. This servlet deliberately keeps two counters side by side so the
 * difference is directly observable:
 *
 *   unsafeCounter -> a plain `int` instance variable, incremented with a
 *                     manual read -> sleep -> write sequence. This is NOT
 *                     atomic, so concurrent threads can race and lose
 *                     updates.
 *
 *   safeCounter    -> an AtomicInteger, incremented with incrementAndGet(),
 *                     which performs the read-modify-write as a single
 *                     indivisible CPU-level operation (compare-and-swap).
 *                     No update is ever lost, no matter how many threads
 *                     call it concurrently.
 *
 * Mapped to: /counter        (see web.xml)
 *   GET /counter              -> increments both counters, returns JSON
 *   GET /counter?reset=true   -> resets both counters to 0, returns JSON
 */
public class VisitorCounterServlet extends HttpServlet {

    // ---------------------------------------------------------------
    // UNSAFE: a plain instance variable. Shared by every request thread.
    // Reading, adding 1, and writing back are three separate, non-atomic
    // steps here - the exact gap a race condition needs to slip through.
    // ---------------------------------------------------------------
    private int unsafeCounter = 0;

    // A plain object monitor is used only to make unsafeCounter's read
    // and write individually visible/log-friendly for the demo; it does
    // NOT make the increment atomic (see doGet for why the race still
    // happens even though we touch the field under here).
    private final Object unsafeLock = new Object();

    // ---------------------------------------------------------------
    // THREAD-SAFE: AtomicInteger performs increments as a single atomic
    // compare-and-swap operation at the hardware level. No lost updates,
    // no explicit locking required.
    // ---------------------------------------------------------------
    private final AtomicInteger safeCounter = new AtomicInteger(0);

    private static final DateTimeFormatter TIME_FORMAT =
            DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

        String threadName = Thread.currentThread().getName();
        boolean reset = "true".equalsIgnoreCase(request.getParameter("reset"));

        try {
            if (reset) {
                resetCounters(threadName);
            } else {
                incrementUnsafe(threadName);
                incrementSafe(threadName);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ServletException("Counter increment was interrupted", e);
        }

        int unsafeValue;
        synchronized (unsafeLock) {
            unsafeValue = unsafeCounter;
        }
        int safeValue = safeCounter.get();

        String json = "{"
                + "\"unsafe\":" + unsafeValue + ","
                + "\"safe\":" + safeValue + ","
                + "\"thread\":\"" + threadName + "\","
                + "\"reset\":" + reset + ","
                + "\"timestamp\":\"" + timestamp() + "\""
                + "}";

        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }

    /**
     * UNSAFE increment.
     *
     * This is a textbook read-modify-write race: the value is read into a
     * local variable, the thread deliberately sleeps (widening the window
     * in which another thread can interleave), and only then is the field
     * written back. If Thread A and Thread B both read "41" before either
     * writes "42" back, one increment is silently lost.
     */
    private void incrementUnsafe(String threadName) throws InterruptedException {
        int current = unsafeCounter;              // 1. READ shared field
        System.out.println("[" + timestamp() + "] " + threadName
                + " -> UNSAFE read value = " + current);

        Thread.sleep(5);                           // simulate work / widen race window

        unsafeCounter = current + 1;                // 2. WRITE shared field (lost-update risk)
        System.out.println("[" + timestamp() + "] " + threadName
                + " -> UNSAFE wrote value = " + unsafeCounter);
    }

    /**
     * THREAD-SAFE increment.
     *
     * incrementAndGet() reads, adds 1, and writes back as ONE atomic
     * hardware operation (compare-and-swap under the hood). No other
     * thread can observe or interleave a partial update, so every
     * request's increment is guaranteed to be counted.
     */
    private void incrementSafe(String threadName) {
        int updated = safeCounter.incrementAndGet();
        System.out.println("[" + timestamp() + "] " + threadName
                + " -> SAFE atomic increment -> " + updated);
    }

    private void resetCounters(String threadName) {
        synchronized (unsafeLock) {
            unsafeCounter = 0;
        }
        safeCounter.set(0);
        System.out.println("[" + timestamp() + "] " + threadName
                + " -> COUNTERS RESET (unsafe=0, safe=0)");
    }

    private static String timestamp() {
        return LocalDateTime.now().format(TIME_FORMAT);
    }
}
