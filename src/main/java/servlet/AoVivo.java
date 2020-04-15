package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(
    name = "AoVivo",
    urlPatterns = {"/aovivo"}
)
public class AoVivo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<form action='http://localhost:8080/hello' method='POST'>");
        out.println("Nome: <input type='text' name='nome'>");
        out.println("<input type='submit' value='Enviar'>");
        out.println("</form>");
        out.println("</html>");
    }
}
