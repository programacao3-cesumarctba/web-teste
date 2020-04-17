package servlet;

import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(
        name = "Exemplo",
        urlPatterns = {"/exemplo"}
)
public class ExemploServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setStatus(200);
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        if (req.getParameter("id") != null) {
            JSONObject obj = new JSONObject();
            obj.put("nome",  "Maycon 1");
            obj.put("email", "1maycon@email.com");
            obj.put("id",   1);

            out.println(obj);
            out.flush();

        } else {
            ArrayList<JSONObject> lista = new ArrayList<>();

            for (int i = 0; i < 50; i++) {
                JSONObject obj = new JSONObject();
                obj.put("nome",  "Maycon " + i);
                obj.put("email", i+"maycon@email.com");
                obj.put("id",   i + 1);
                lista.add(obj);
            }
            out.println(lista);
            out.flush();
        }
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
