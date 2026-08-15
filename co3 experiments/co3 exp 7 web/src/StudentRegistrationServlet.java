import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class StudentRegistrationServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        String name = request.getParameter("studentName");
        String regno = request.getParameter("registerNumber");
        String email = request.getParameter("email");
        String department = request.getParameter("department");
        String semester = request.getParameter("semester");

        // Validation
        if (name == null || name.trim().isEmpty() ||
            regno == null || regno.trim().isEmpty() ||
            email == null || email.trim().isEmpty() ||
            department == null || department.trim().isEmpty() ||
            semester == null || semester.trim().isEmpty()) {

            out.println("<html><head>");
            out.println("<title>Registration Error</title>");
            out.println("<style>");
            out.println("body{font-family:Arial;background:linear-gradient(135deg,#ef4444,#f97316);height:100vh;display:flex;justify-content:center;align-items:center;}");
            out.println(".box{background:white;padding:45px;border-radius:25px;text-align:center;box-shadow:0 20px 50px #0004;}");
            out.println("h2{color:#dc2626;} a{display:inline-block;margin-top:20px;padding:12px 25px;background:#6366f1;color:white;text-decoration:none;border-radius:10px;}");
            out.println("</style></head><body>");

            out.println("<div class='box'>");
            out.println("<h2>⚠️ Registration Failed</h2>");
            out.println("<p>Please fill in all the required fields.</p>");
            out.println("<a href='index.html'>Go Back</a>");
            out.println("</div>");

            out.println("</body></html>");

            return;
        }

        // Successful registration
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Registration Successful</title>");

        out.println("<style>");

        out.println("*{box-sizing:border-box;font-family:Arial;}");

        out.println("body{");
        out.println("margin:0;");
        out.println("min-height:100vh;");
        out.println("display:flex;");
        out.println("justify-content:center;");
        out.println("align-items:center;");
        out.println("background:linear-gradient(135deg,#4f46e5,#9333ea,#db2777);");
        out.println("}");

        out.println(".card{");
        out.println("width:600px;");
        out.println("max-width:90%;");
        out.println("background:white;");
        out.println("padding:45px;");
        out.println("border-radius:28px;");
        out.println("box-shadow:0 25px 60px #0004;");
        out.println("}");

        out.println(".success{");
        out.println("width:75px;");
        out.println("height:75px;");
        out.println("margin:auto;");
        out.println("border-radius:50%;");
        out.println("background:#22c55e;");
        out.println("color:white;");
        out.println("font-size:40px;");
        out.println("display:flex;");
        out.println("align-items:center;");
        out.println("justify-content:center;");
        out.println("}");

        out.println("h1{text-align:center;color:#1e1b4b;margin-top:20px;}");

        out.println(".detail{");
        out.println("padding:15px;");
        out.println("margin:10px 0;");
        out.println("background:#f5f3ff;");
        out.println("border-radius:12px;");
        out.println("color:#374151;");
        out.println("}");

        out.println("a{");
        out.println("display:block;");
        out.println("text-align:center;");
        out.println("margin-top:25px;");
        out.println("padding:14px;");
        out.println("background:linear-gradient(135deg,#6366f1,#ec4899);");
        out.println("color:white;");
        out.println("text-decoration:none;");
        out.println("border-radius:12px;");
        out.println("font-weight:bold;");
        out.println("}");

        out.println("</style>");
        out.println("</head>");

        out.println("<body>");

        out.println("<div class='card'>");

        out.println("<div class='success'>✓</div>");

        out.println("<h1>Registration Successful!</h1>");

        out.println("<div class='detail'><b>👤 Student Name:</b> " + name + "</div>");
        out.println("<div class='detail'><b>🆔 Register Number:</b> " + regno + "</div>");
        out.println("<div class='detail'><b>✉️ Email:</b> " + email + "</div>");
        out.println("<div class='detail'><b>🏫 Department:</b> " + department + "</div>");
        out.println("<div class='detail'><b>📚 Semester:</b> " + semester + "</div>");

        out.println("<a href='index.html'>Register Another Student</a>");

        out.println("</div>");

        out.println("</body>");
        out.println("</html>");
    }
}