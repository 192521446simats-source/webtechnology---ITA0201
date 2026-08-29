package com.itservice.controller;

import com.itservice.model.ServiceRequest;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet("/submitServiceRequest")
public class ServiceRequestServlet extends HttpServlet {

    private static final AtomicInteger REQUEST_COUNTER =
            new AtomicInteger(1000);

    @Override
    protected void doPost(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String employeeId = request.getParameter("employeeId");
        String employeeName = request.getParameter("employeeName");
        String department = request.getParameter("department");
        String problemCategory = request.getParameter("problemCategory");
        String problemDescription =
                request.getParameter("problemDescription");
        String priority = request.getParameter("priority");

        employeeId = clean(employeeId);
        employeeName = clean(employeeName);
        department = clean(department);
        problemCategory = clean(problemCategory);
        problemDescription = clean(problemDescription);
        priority = clean(priority);

        if (employeeId.isEmpty()
                || employeeName.isEmpty()
                || department.isEmpty()
                || problemCategory.isEmpty()
                || problemDescription.isEmpty()
                || priority.isEmpty()) {

            request.setAttribute(
                    "errorMessage",
                    "All fields are mandatory. Please fill in all details."
            );

            RequestDispatcher dispatcher =
                    request.getRequestDispatcher(
                            "serviceRequest.jsp"
                    );

            dispatcher.forward(request, response);
            return;
        }

        ServiceRequest serviceRequest =
                new ServiceRequest(
                        employeeId,
                        employeeName,
                        department,
                        problemCategory,
                        problemDescription,
                        priority
                );

        String requestNumber =
                "SR-" + REQUEST_COUNTER.incrementAndGet();

        request.setAttribute(
                "serviceRequest",
                serviceRequest
        );

        request.setAttribute(
                "requestNumber",
                requestNumber
        );

        RequestDispatcher dispatcher =
                request.getRequestDispatcher(
                        "acknowledgement.jsp"
                );

        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.sendRedirect(
                request.getContextPath()
                        + "/serviceRequest.jsp"
        );
    }

    private String clean(String value) {

        if (value == null) {
            return "";
        }

        return value.trim();
    }
}