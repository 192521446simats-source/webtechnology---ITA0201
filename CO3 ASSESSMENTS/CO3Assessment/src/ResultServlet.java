import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ResultServlet extends HttpServlet {

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String registerNo = request.getParameter("registerNo");

        try {

            int mark1 = Integer.parseInt(
                    request.getParameter("mark1"));

            int mark2 = Integer.parseInt(
                    request.getParameter("mark2"));

            int mark3 = Integer.parseInt(
                    request.getParameter("mark3"));

            int mark4 = Integer.parseInt(
                    request.getParameter("mark4"));

            int mark5 = Integer.parseInt(
                    request.getParameter("mark5"));

            int[] marks = {
                mark1,
                mark2,
                mark3,
                mark4,
                mark5
            };

            // Validate marks
            for (int mark : marks) {

                if (mark < 0 || mark > 100) {

                    request.setAttribute(
                            "error",
                            "Marks must be between 0 and 100."
                    );

                    request.getRequestDispatcher(
                            "result.jsp"
                    ).forward(request, response);

                    return;
                }
            }

            // Calculate Total
            int total = 0;

            for (int mark : marks) {
                total += mark;
            }

            // Calculate Average
            double average = total / 5.0;

            // Find Highest
            int highest = marks[0];

            for (int mark : marks) {

                if (mark > highest) {
                    highest = mark;
                }
            }

            // Find Lowest
            int lowest = marks[0];

            for (int mark : marks) {

                if (mark < lowest) {
                    lowest = mark;
                }
            }

            // Pass / Fail
            boolean pass = true;

            for (int mark : marks) {

                if (mark < 40) {
                    pass = false;
                    break;
                }
            }

            // Grade
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

            // Send data to result.jsp
            request.setAttribute("name", name);
            request.setAttribute("registerNo", registerNo);

            request.setAttribute("mark1", mark1);
            request.setAttribute("mark2", mark2);
            request.setAttribute("mark3", mark3);
            request.setAttribute("mark4", mark4);
            request.setAttribute("mark5", mark5);

            request.setAttribute("total", total);
            request.setAttribute("average", average);
            request.setAttribute("highest", highest);
            request.setAttribute("lowest", lowest);
            request.setAttribute("grade", grade);

            request.setAttribute(
                    "status",
                    pass ? "PASS" : "FAIL"
            );

            // Open result.jsp
            request.getRequestDispatcher(
                    "result.jsp"
            ).forward(request, response);

        }
        catch (NumberFormatException e) {

            request.setAttribute(
                    "error",
                    "Please enter valid numeric marks."
            );

            request.getRequestDispatcher(
                    "result.jsp"
            ).forward(request, response);
        }
    }
}