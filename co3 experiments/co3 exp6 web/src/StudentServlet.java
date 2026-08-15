import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class StudentServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
                         throws ServletException, IOException {

        // Set response type
        response.setContentType("text/html");

        // Create PrintWriter
        PrintWriter out = response.getWriter();

        // Student details
        String studentName = "Keerthana G";
        String courseName = "B.Tech Information Technology";

        // Current date and time
        Date currentDate = new Date();

        // HTML output
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");

        out.println("<title>Student Welcome Portal</title>");

        out.println("<style>");

        out.println("* {");
        out.println("box-sizing: border-box;");
        out.println("font-family: Arial, sans-serif;");
        out.println("}");

        out.println("body {");
        out.println("margin: 0;");
        out.println("min-height: 100vh;");
        out.println("display: flex;");
        out.println("justify-content: center;");
        out.println("align-items: center;");
        out.println("background: linear-gradient(135deg, #667eea, #764ba2);");
        out.println("}");

        out.println(".card {");
        out.println("width: 500px;");
        out.println("background: white;");
        out.println("padding: 40px;");
        out.println("border-radius: 25px;");
        out.println("text-align: center;");
        out.println("box-shadow: 0 20px 40px rgba(0,0,0,0.25);");
        out.println("}");

        out.println(".icon {");
        out.println("font-size: 55px;");
        out.println("margin-bottom: 15px;");
        out.println("}");

        out.println("h1 {");
        out.println("color: #333;");
        out.println("margin-bottom: 10px;");
        out.println("}");

        out.println(".welcome {");
        out.println("color: #667eea;");
        out.println("font-size: 20px;");
        out.println("font-weight: bold;");
        out.println("margin-bottom: 25px;");
        out.println("}");

        out.println(".info {");
        out.println("background: #f5f6ff;");
        out.println("padding: 15px;");
        out.println("margin: 12px 0;");
        out.println("border-radius: 12px;");
        out.println("text-align: left;");
        out.println("}");

        out.println(".label {");
        out.println("color: #777;");
        out.println("font-size: 13px;");
        out.println("}");

        out.println(".value {");
        out.println("color: #333;");
        out.println("font-size: 17px;");
        out.println("font-weight: bold;");
        out.println("margin-top: 5px;");
        out.println("}");

        out.println(".time {");
        out.println("margin-top: 25px;");
        out.println("padding: 15px;");
        out.println("background: #667eea;");
        out.println("color: white;");
        out.println("border-radius: 12px;");
        out.println("}");

        out.println(".footer {");
        out.println("margin-top: 20px;");
        out.println("color: #888;");
        out.println("font-size: 13px;");
        out.println("}");

        out.println("</style>");

        out.println("</head>");

        out.println("<body>");

        out.println("<div class='card'>");

        out.println("<div class='icon'>🎓</div>");

        out.println("<h1>Student Welcome Portal</h1>");

        out.println("<div class='welcome'>");
        out.println("Welcome, " + studentName + "!");
        out.println("</div>");

        out.println("<div class='info'>");

        out.println("<div class='label'>Student Name</div>");

        out.println("<div class='value'>");
        out.println(studentName);
        out.println("</div>");

        out.println("</div>");

        out.println("<div class='info'>");

        out.println("<div class='label'>Course</div>");

        out.println("<div class='value'>");
        out.println(courseName);
        out.println("</div>");

        out.println("</div>");

        out.println("<div class='time'>");

        out.println("🕐 Current Date & Time");

        out.println("<br><br>");

        out.println(currentDate);

        out.println("</div>");

        out.println("<div class='footer'>");

        out.println("Generated dynamically using Java Servlet");

        out.println("</div>");

        out.println("</div>");

        out.println("</body>");

        out.println("</html>");
    }
}