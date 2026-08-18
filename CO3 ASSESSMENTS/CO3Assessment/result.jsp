<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Student Result</title>

    <style>

        * {
            box-sizing: border-box;
            font-family: Arial, sans-serif;
        }

        body {
            margin: 0;
            min-height: 100vh;

            background: linear-gradient(
                135deg,
                #dbeafe,
                #f8fafc
            );

            display: flex;
            justify-content: center;
            align-items: center;

            padding: 30px;
        }

        .container {
            width: 750px;

            background: white;

            padding: 35px;

            border-radius: 18px;

            box-shadow:
                0 10px 35px
                rgba(0, 0, 0, 0.15);
        }

        h1 {
            text-align: center;
            color: #1e3a8a;
            margin-bottom: 25px;
        }

        .student-info {
            background: #eff6ff;
            padding: 20px;
            border-radius: 10px;
            margin-bottom: 25px;
        }

        .student-info p {
            margin: 8px 0;
            color: #334155;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th,
        td {
            padding: 13px;
            border: 1px solid #cbd5e1;
            text-align: center;
        }

        th {
            background: #2563eb;
            color: white;
        }

        td {
            color: #334155;
        }

        .summary {
            margin-top: 25px;
            background: #f8fafc;
            padding: 20px;
            border-radius: 10px;
        }

        .summary p {
            padding: 7px;
            color: #334155;
        }

        .status {
            font-size: 20px;
            font-weight: bold;
        }

        .error {
            background: #fee2e2;
            color: #991b1b;
            padding: 15px;
            border-radius: 8px;
            text-align: center;
            font-weight: bold;
        }

        .back-button {
            display: block;
            text-align: center;
            text-decoration: none;

            background: #2563eb;
            color: white;

            padding: 13px;

            border-radius: 8px;

            margin-top: 25px;

            font-weight: bold;
        }

        .back-button:hover {
            background: #1d4ed8;
        }

    </style>

</head>

<body>

<div class="container">

    <h1>Student Result</h1>

<%
    String error =
        (String) request.getAttribute("error");

    if (error != null) {
%>

    <div class="error">
        <%= error %>
    </div>

    <a href="index.jsp" class="back-button">
        Go Back
    </a>

<%
    } else {
%>

    <div class="student-info">

        <p>
            <strong>Student Name:</strong>
            <%= request.getAttribute("name") %>
        </p>

        <p>
            <strong>Register Number:</strong>
            <%= request.getAttribute("registerNo") %>
        </p>

    </div>

    <table>

        <tr>
            <th>Subject</th>
            <th>Mark</th>
        </tr>

        <tr>
            <td>Subject 1</td>
            <td><%= request.getAttribute("mark1") %></td>
        </tr>

        <tr>
            <td>Subject 2</td>
            <td><%= request.getAttribute("mark2") %></td>
        </tr>

        <tr>
            <td>Subject 3</td>
            <td><%= request.getAttribute("mark3") %></td>
        </tr>

        <tr>
            <td>Subject 4</td>
            <td><%= request.getAttribute("mark4") %></td>
        </tr>

        <tr>
            <td>Subject 5</td>
            <td><%= request.getAttribute("mark5") %></td>
        </tr>

    </table>

    <div class="summary">

        <p>
            <strong>Total:</strong>
            <%= request.getAttribute("total") %> / 500
        </p>

        <p>
            <strong>Average:</strong>
            <%= String.format(
                    "%.2f",
                    (Double) request.getAttribute("average")
                ) %>
        </p>

        <p>
            <strong>Highest Mark:</strong>
            <%= request.getAttribute("highest") %>
        </p>

        <p>
            <strong>Lowest Mark:</strong>
            <%= request.getAttribute("lowest") %>
        </p>

        <p>
            <strong>Grade:</strong>
            <%= request.getAttribute("grade") %>
        </p>

        <p class="status">
            <strong>Status:</strong>
            <%= request.getAttribute("status") %>
        </p>

    </div>

    <a href="index.jsp" class="back-button">
        Calculate Another Result
    </a>

<%
    }
%>

</div>

</body>

</html>