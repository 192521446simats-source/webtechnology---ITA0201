package com.example;

import jakarta.servlet.ServletConfig;
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
 * CO3 EXP 9 WEB - Servlet Lifecycle Demonstration
 *
 * This servlet demonstrates the complete Java Servlet lifecycle as managed
 * by the Servlet Container (Apache Tomcat):
 *
 *   1. Constructor   -> called ONCE, when the container creates the servlet instance
 *   2. init()        -> called ONCE, immediately after construction, before serving requests
 *   3. service()/doGet() -> called ONCE PER REQUEST, for as long as the servlet stays loaded
 *   4. destroy()     -> called ONCE, when the container unloads / stops / redeploys the servlet
 *
 * Mapped to: /lifecycle   (see web.xml)
 */
public class LifecycleServlet extends HttpServlet {

    // Tracks how many times doGet() has been invoked (i.e. how many requests served).
    // A single servlet instance is reused across requests by Tomcat, so this
    // counter persists and increments for every /lifecycle hit / page refresh.
    private final AtomicInteger requestCount = new AtomicInteger(0);

    private static final DateTimeFormatter TIME_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * CONSTRUCTOR
     * Invoked exactly once by the servlet container when it creates the
     * servlet instance (typically on first request, or at startup if
     * load-on-startup is configured in web.xml).
     */
    public LifecycleServlet() {
        super();
        System.out.println("[" + timestamp() + "] CONSTRUCTOR EXECUTED - LifecycleServlet instance created");
    }

    /**
     * INIT()
     * Invoked exactly once, immediately after the constructor, before the
     * servlet is allowed to handle any client requests. Used for one-time
     * setup work (loading config, opening resources, etc.).
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("[" + timestamp() + "] INIT() EXECUTED - Servlet initialized and ready to serve requests");
    }

    /**
     * DOGET()
     * Invoked by service() every single time an HTTP GET request hits
     * /lifecycle. This is why refreshing the dashboard page increments the
     * live request counter on every reload.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int currentCount = requestCount.incrementAndGet();

        System.out.println("[" + timestamp() + "] DOGET() EXECUTED - Request #" + currentCount
                + " from " + request.getRemoteAddr());

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

        PrintWriter out = response.getWriter();
        String json = "{"
                + "\"status\":\"success\","
                + "\"requestCount\":" + currentCount + ","
                + "\"message\":\"DOGET() EXECUTED - Request " + currentCount + "\","
                + "\"timestamp\":\"" + timestamp() + "\""
                + "}";
        out.print(json);
        out.flush();
    }

    /**
     * DESTROY()
     * Invoked exactly once by the container when the servlet is being taken
     * out of service - on server shutdown, application undeploy, or redeploy.
     * Used to release any resources acquired in init().
     */
    @Override
    public void destroy() {
        System.out.println("[" + timestamp() + "] DESTROY() EXECUTED - Servlet resources released. Total requests served: "
                + requestCount.get());
        super.destroy();
    }

    private static String timestamp() {
        return LocalDateTime.now().format(TIME_FORMAT);
    }
}
