import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Cd on 17/03/2016.
 */
@WebServlet("/HelloWorld")
public class TestServlet {
    // Extend HttpServlet class
    public class HelloWorld extends HttpServlet {

        private String message;

        public void init() throws ServletException
        {
            // Do required initialization
            message = "Hello World";
        }

        public void doGet(HttpServletRequest request,
                          HttpServletResponse response)
                throws ServletException, IOException
        {
            response.setContentType("text/html");
            PrintWriter printWriter  = response.getWriter();
            printWriter.println("<h1>Hello World!</h1>");
        }

        public void destroy()
        {
            // do nothing.
        }
    }
}
