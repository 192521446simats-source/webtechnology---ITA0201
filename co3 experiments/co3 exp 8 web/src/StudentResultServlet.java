import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/result")
public class StudentResultServlet extends HttpServlet {

    // If /result is opened directly
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        response.sendRedirect("index.html");
    }

    // Process form data
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        String name = request.getParameter("studentName");
        String regno = request.getParameter("registerNumber");

        String s1 = request.getParameter("mark1");
        String s2 = request.getParameter("mark2");
        String s3 = request.getParameter("mark3");
        String s4 = request.getParameter("mark4");
        String s5 = request.getParameter("mark5");

        // Validate student details
        if (name == null || name.trim().isEmpty()) {
            showError(out, "Student name is required.");
            return;
        }

        if (regno == null || regno.trim().isEmpty()) {
            showError(out, "Register number is required.");
            return;
        }

        // Validate missing marks
        if (s1 == null || s1.trim().isEmpty() ||
            s2 == null || s2.trim().isEmpty() ||
            s3 == null || s3.trim().isEmpty() ||
            s4 == null || s4.trim().isEmpty() ||
            s5 == null || s5.trim().isEmpty()) {

            showError(out, "Please enter marks for all five subjects.");
            return;
        }

        int m1;
        int m2;
        int m3;
        int m4;
        int m5;

        // Validate numeric marks
        try {

            m1 = Integer.parseInt(s1);
            m2 = Integer.parseInt(s2);
            m3 = Integer.parseInt(s3);
            m4 = Integer.parseInt(s4);
            m5 = Integer.parseInt(s5);

        } catch (NumberFormatException e) {

            showError(out, "Marks must contain numbers only.");
            return;
        }

        // Validate range
        if (m1 < 0 || m1 > 100 ||
            m2 < 0 || m2 > 100 ||
            m3 < 0 || m3 > 100 ||
            m4 < 0 || m4 > 100 ||
            m5 < 0 || m5 > 100) {

            showError(out, "Marks must be between 0 and 100.");
            return;
        }

        // Calculate total
        int total = m1 + m2 + m3 + m4 + m5;

        // Calculate average
        double average = total / 5.0;

        // Highest mark
        int highest = Math.max(
                Math.max(m1, m2),
                Math.max(Math.max(m3, m4), m5)
        );

        // Lowest mark
        int lowest = Math.min(
                Math.min(m1, m2),
                Math.min(Math.min(m3, m4), m5)
        );

        // Pass / Fail
        boolean pass =
                m1 >= 35 &&
                m2 >= 35 &&
                m3 >= 35 &&
                m4 >= 35 &&
                m5 >= 35;

        String grade;

        if (!pass) {
            grade = "F";
        }
        else if (average >= 90) {
            grade = "A+";
        }
        else if (average >= 80) {
            grade = "A";
        }
        else if (average >= 70) {
            grade = "B";
        }
        else if (average >= 60) {
            grade = "C";
        }
        else if (average >= 50) {
            grade = "D";
        }
        else {
            grade = "E";
        }

        String status = pass ? "PASS" : "FAIL";

        // Dynamic result page
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");

        out.println("<title>Student Result</title>");

        out.println("<style>");

        out.println("*{");
        out.println("box-sizing:border-box;");
        out.println("font-family:Arial,sans-serif;");
        out.println("}");

        out.println("body{");
        out.println("margin:0;");
        out.println("min-height:100vh;");
        out.println("display:flex;");
        out.println("justify-content:center;");
        out.println("align-items:center;");
        out.println("padding:40px 20px;");
        out.println("background:linear-gradient(135deg,#312e81,#7c3aed,#db2777);");
        out.println("}");

        out.println(".card{");
        out.println("width:900px;");
        out.println("max-width:100%;");
        out.println("background:white;");
        out.println("padding:40px;");
        out.println("border-radius:28px;");
        out.println("box-shadow:0 25px 70px rgba(0,0,0,.3);");
        out.println("}");

        out.println(".success{");
        out.println("width:75px;");
        out.println("height:75px;");
        out.println("margin:auto;");
        out.println("display:flex;");
        out.println("align-items:center;");
        out.println("justify-content:center;");
        out.println("border-radius:50%;");
        out.println("background:#22c55e;");
        out.println("color:white;");
        out.println("font-size:40px;");
        out.println("}");

        out.println("h1{");
        out.println("text-align:center;");
        out.println("color:#1e1b4b;");
        out.println("}");

        out.println(".student{");
        out.println("text-align:center;");
        out.println("color:#6b7280;");
        out.println("margin-bottom:30px;");
        out.println("}");

        out.println("table{");
        out.println("width:100%;");
        out.println("border-collapse:collapse;");
        out.println("}");

        out.println("th{");
        out.println("padding:16px;");
        out.println("color:white;");
        out.println("background:linear-gradient(135deg,#6366f1,#a855f7);");
        out.println("}");

        out.println("td{");
        out.println("padding:15px;");
        out.println("text-align:center;");
        out.println("border-bottom:1px solid #e5e7eb;");
        out.println("}");

        out.println("tr:nth-child(even){");
        out.println("background:#f5f3ff;");
        out.println("}");

        out.println(".summary{");
        out.println("display:grid;");
        out.println("grid-template-columns:repeat(4,1fr);");
        out.println("gap:15px;");
        out.println("margin-top:25px;");
        out.println("}");

        out.println(".box{");
        out.println("padding:18px;");
        out.println("text-align:center;");
        out.println("border-radius:15px;");
        out.println("background:#f5f3ff;");
        out.println("color:#6b7280;");
        out.println("}");

        out.println(".box strong{");
        out.println("display:block;");
        out.println("margin-top:8px;");
        out.println("font-size:23px;");
        out.println("color:#4f46e5;");
        out.println("}");

        out.println(".status{");
        out.println("margin-top:25px;");
        out.println("padding:16px;");
        out.println("text-align:center;");
        out.println("border-radius:12px;");
        out.println("font-size:20px;");
        out.println("font-weight:bold;");
        out.println("}");

        if (pass) {
            out.println(".status{");
            out.println("background:#dcfce7;");
            out.println("color:#15803d;");
            out.println("}");
        } else {
            out.println(".status{");
            out.println("background:#fee2e2;");
            out.println("color:#dc2626;");
            out.println("}");
        }

        out.println(".back{");
        out.println("display:block;");
        out.println("margin-top:25px;");
        out.println("padding:14px;");
        out.println("text-align:center;");
        out.println("text-decoration:none;");
        out.println("color:white;");
        out.println("font-weight:bold;");
        out.println("border-radius:12px;");
        out.println("background:linear-gradient(135deg,#6366f1,#ec4899);");
        out.println("}");

        out.println("</style>");

        out.println("</head>");

        out.println("<body>");

        out.println("<div class='card'>");

        out.println("<div class='success'>✓</div>");

        out.println("<h1>Student Result</h1>");

        out.println("<div class='student'>");
        out.println("<b>Name:</b> " + name);
        out.println(" &nbsp; | &nbsp; ");
        out.println("<b>Register No:</b> " + regno);
        out.println("</div>");

        // Result table
        out.println("<table>");

        out.println("<tr>");
        out.println("<th>Subject</th>");
        out.println("<th>Mark</th>");
        out.println("<th>Result</th>");
        out.println("</tr>");

        printRow(out, "Subject 1", m1);
        printRow(out, "Subject 2", m2);
        printRow(out, "Subject 3", m3);
        printRow(out, "Subject 4", m4);
        printRow(out, "Subject 5", m5);

        out.println("</table>");

        // Summary
        out.println("<div class='summary'>");

        out.println("<div class='box'>");
        out.println("Total");
        out.println("<strong>" + total + "/500</strong>");
        out.println("</div>");

        out.println("<div class='box'>");
        out.println("Average");
        out.println("<strong>" + String.format("%.2f", average) + "</strong>");
        out.println("</div>");

        out.println("<div class='box'>");
        out.println("Highest");
        out.println("<strong>" + highest + "</strong>");
        out.println("</div>");

        out.println("<div class='box'>");
        out.println("Lowest");
        out.println("<strong>" + lowest + "</strong>");
        out.println("</div>");

        out.println("</div>");

        out.println("<div class='status'>");
        out.println("Grade: " + grade);
        out.println(" &nbsp; | &nbsp; ");
        out.println("Status: " + status);
        out.println("</div>");

        out.println("<a class='back' href='index.html'>");
        out.println("Calculate Another Result");
        out.println("</a>");

        out.println("</div>");

        out.println("</body>");
        out.println("</html>");
    }

    private void printRow(PrintWriter out,
                          String subject,
                          int mark) {

        String result;

        if (mark >= 35) {
            result = "PASS";
        } else {
            result = "FAIL";
        }

        out.println("<tr>");
        out.println("<td>" + subject + "</td>");
        out.println("<td>" + mark + "</td>");
        out.println("<td>" + result + "</td>");
        out.println("</tr>");
    }

    private void showError(PrintWriter out,
                           String message) {

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");

        out.println("<title>Error</title>");

        out.println("<style>");

        out.println("body{");
        out.println("margin:0;");
        out.println("height:100vh;");
        out.println("display:flex;");
        out.println("justify-content:center;");
        out.println("align-items:center;");
        out.println("font-family:Arial;");
        out.println("background:linear-gradient(135deg,#ef4444,#f97316);");
        out.println("}");

        out.println(".error{");
        out.println("background:white;");
        out.println("padding:45px;");
        out.println("border-radius:25px;");
        out.println("text-align:center;");
        out.println("box-shadow:0 20px 60px rgba(0,0,0,.3);");
        out.println("}");

        out.println("h2{color:#dc2626;}");

        out.println("a{");
        out.println("display:inline-block;");
        out.println("margin-top:20px;");
        out.println("padding:13px 25px;");
        out.println("background:#6366f1;");
        out.println("color:white;");
        out.println("text-decoration:none;");
        out.println("border-radius:10px;");
        out.println("}");

        out.println("</style>");

        out.println("</head>");

        out.println("<body>");

        out.println("<div class='error'>");

        out.println("<h2>⚠️ Invalid Input</h2>");

        out.println("<p>" + message + "</p>");

        out.println("<a href='index.html'>Go Back</a>");

        out.println("</div>");

        out.println("</body>");
        out.println("</html>");
    }
}