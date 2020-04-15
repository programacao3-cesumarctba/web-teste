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
import java.util.List;

@WebServlet(
        name = "ListaContato",
        urlPatterns = {"/listaContato"}
)
public class ListContatoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Contato contato = new Contato();
        try {
            // busca todos os contatos
            List<Contato> contatos = contato.busca();
            PrintWriter out = resp.getWriter();

            out.println("<html>");
            out.println("   <head>");
            out.println("       <title>Contatos</title>");
            out.println("   </head>");
            out.println("   <body>");
            out.println("       <h1>Lista de Contato</h1>");
            out.println("       <a href='/formContato?op=novo'>Novo Contato</a>");
            out.println("       <table border=1>");
            out.println("           <thead>");
            out.println("               <tr>");
            out.println("                   <th>ID</th>");
            out.println("                   <th>Nome</th>");
            out.println("                   <th>Telefone</th>");
            out.println("                   <th>Celular</th>");
            out.println("                   <th>E-mail</th>");
            out.println("                   <th>Opções</th>");
            out.println("               </tr>");
            out.println("           </thead>");
            out.println("           <tbody>");

            // se não tiver contato
            if (contatos.size() == 0) {
                out.println("<tr collspan='6'>Nenhum contato cadastrado!</tr>");
            } else {
                for (int i = 0; i < contatos.size(); i++) {
                    Contato c = contatos.get(i);
                    out.println("               <tr>");
                    out.println("                   <td>" + c.getId() + "</td>");
                    out.println("                   <td>" + c.getNome() + "</td>");
                    out.println("                   <td>" + c.getTelefone() + "</td>");
                    out.println("                   <td>" + c.getCelular() + "</td>");
                    out.println("                   <td>" + c.getEmail() + "</td>");
                    out.println("                   <td><a href='/formContato?op=editar&id=" + c.getId() +
                                                    "'>Editar</a> <br> <a href='/formContato?op=excluir&id=" + c.getId() +
                                                    "'>Excluir</a></td>");
                    out.println("               </tr>");
                }
            }
            out.println("           </tbody>");
            out.println("       ");
            out.println("       ");
            out.println("   </body>");
            out.println("</html>");
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }
}
