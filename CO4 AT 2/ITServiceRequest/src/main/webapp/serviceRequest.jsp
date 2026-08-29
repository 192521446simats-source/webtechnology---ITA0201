<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>

<html>

<head>

    <title>IT Service Request</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">

</head>

<body>

<div class="container">

    <h1>IT Service Request Management System</h1>

    <p class="subtitle">
        Submit your technical complaint to the IT Support Team.
    </p>

    <form
        action="${pageContext.request.contextPath}/submitServiceRequest"
        method="post">

        <label>Employee ID</label>

        <input
            type="text"
            name="employeeId"
            placeholder="Enter Employee ID"
            required>


        <label>Employee Name</label>

        <input
            type="text"
            name="employeeName"
            placeholder="Enter Employee Name"
            required>


        <label>Department</label>

        <select name="department" required>

            <option value="">
                Select Department
            </option>

            <option value="IT">
                IT
            </option>

            <option value="HR">
                Human Resources
            </option>

            <option value="Finance">
                Finance
            </option>

            <option value="Marketing">
                Marketing
            </option>

            <option value="Operations">
                Operations
            </option>

        </select>


        <label>Problem Category</label>

        <select name="problemCategory" required>

            <option value="">
                Select Category
            </option>

            <option value="Network">
                Network
            </option>

            <option value="Software">
                Software
            </option>

            <option value="Hardware">
                Hardware
            </option>

            <option value="Account">
                Account
            </option>

            <option value="Other">
                Other
            </option>

        </select>


        <label>Problem Description</label>

        <textarea
            name="problemDescription"
            rows="6"
            placeholder="Describe your problem..."
            required></textarea>


        <label>Priority</label>

        <div class="priority">

            <label>
                <input
                    type="radio"
                    name="priority"
                    value="Low"
                    required>

                Low
            </label>

            <label>
                <input
                    type="radio"
                    name="priority"
                    value="Medium">

                Medium
            </label>

            <label>
                <input
                    type="radio"
                    name="priority"
                    value="High">

                High
            </label>

        </div>


        <button type="submit">
            Submit Service Request
        </button>

    </form>

</div>

</body>

</html>