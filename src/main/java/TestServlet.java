import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Cd on 17/03/2016.
 */

public class TestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        // Set response content type
        resp.setContentType("text/html");

        // Actual logic goes here.
        PrintWriter out = resp.getWriter();
        out.println("<h1>" + "Test" + "</h1>");
    }
}
