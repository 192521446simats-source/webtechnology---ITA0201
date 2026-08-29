<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="com.itservice.model.ServiceRequest" %>

<%
    ServiceRequest serviceRequest =
        (ServiceRequest) request.getAttribute("serviceRequest");

    String requestNumber =
        (String) request.getAttribute("requestNumber");
%>

<!DOCTYPE html>

<html>

<head>

    <title>Request Submitted</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">

</head>

<body>

<div class="container">

    <h1>✓ Service Request Submitted Successfully!</h1>

    <p class="subtitle">
        Your technical complaint has been submitted
        to the IT Support Team.
    </p>

    <h2>
        Request Number:
        <strong><%= requestNumber %></strong>
    </h2>

    <hr>

    <p>
        <strong>Employee ID:</strong>
        <%= serviceRequest.getEmployeeId() %>
    </p>

    <p>
        <strong>Employee Name:</strong>
        <%= serviceRequest.getEmployeeName() %>
    </p>

    <p>
        <strong>Department:</strong>
        <%= serviceRequest.getDepartment() %>
    </p>

    <p>
        <strong>Problem Category:</strong>
        <%= serviceRequest.getProblemCategory() %>
    </p>

    <p>
        <strong>Priority:</strong>
        <%= serviceRequest.getPriority() %>
    </p>

    <p>
        <strong>Problem Description:</strong>
    </p>

    <p>
        <%= serviceRequest.getProblemDescription() %>
    </p>

    <hr>

    <h2>MVC Architecture</h2>

    <p>
        <strong>Model:</strong>
        ServiceRequest.java
    </p>

    <p>
        <strong>View:</strong>
        serviceRequest.jsp and acknowledgement.jsp
    </p>

    <p>
        <strong>Controller:</strong>
        ServiceRequestServlet.java
    </p>

    <a href="serviceRequest.jsp">
        Submit Another Request
    </a>

</div>

</body>

</html>