package servlet;

import entidades.Contato;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(
        name = "ContatoAction",
        urlPatterns = {"/actionContato"}
)
public class ContatoActionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String opc = req.getParameter("opc");
        switch (opc) {
            case "novo":
                this.novoContato(req, resp);
                break;
            case "editar":
                this.editaContato(req, resp);
                break;
            case "excluir":
                this.excluiContato(req, resp);
                break;
        }
    }


    private void novoContato(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Contato contato = new Contato();
            contato.setNome(req.getParameter("nome"));
            contato.setTelefone(req.getParameter("telefone"));
            contato.setCelular(req.getParameter("celular"));
            contato.setTelefone2(req.getParameter("telefone2"));
            contato.setCelular2(req.getParameter("celular2"));
            contato.setEmail(req.getParameter("email"));
            contato.insere();

            resp.sendRedirect("/listaContato");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void editaContato(HttpServletRequest req, HttpServletResponse resp) {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            Contato contato = new Contato(id);
            contato.setNome(req.getParameter("nome"));
            contato.setTelefone(req.getParameter("telefone"));
            contato.setCelular(req.getParameter("celular"));
            contato.setTelefone2(req.getParameter("telefone2"));
            contato.setCelular2(req.getParameter("celular2"));
            contato.setEmail(req.getParameter("email"));
            contato.altera();
            resp.sendRedirect("/listaContato");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void excluiContato(HttpServletRequest req, HttpServletResponse resp) {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            Contato contato = new Contato(id);
            contato.exclui();
            resp.sendRedirect("/listaContato");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
