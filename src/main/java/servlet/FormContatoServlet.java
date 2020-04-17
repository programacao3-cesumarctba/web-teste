package servlet;

import entidades.Contato;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        name = "FormContato",
        urlPatterns = {"/formContato"}
)
public class FormContatoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String opcao = "";
        String opc = "";

        Integer id      = null;
        String nome     = "";
        String telefone = "";
        String telefone2 = "";
        String celular  = "";
        String celular2  = "";
        String email    = "";

        if (req.getParameter("op").equals("novo")) {
            // inserir contato
            opcao  = "Novo Contato";
            opc = "novo";
        } else if (req.getParameter("op").equals("editar")) {
            // inserir contato
            opcao  = "Editar Contato";
            opc = "editar";
            // dados do contato
            id   =  Integer.parseInt(req.getParameter("id"));
            Contato contato = new Contato(id);
            nome = contato.getNome();
            telefone = contato.getTelefone();
            celular  = contato.getCelular();
            telefone2 = contato.getTelefone2();
            celular2  = contato.getCelular2();
            email    = contato.getEmail();
        } else if (req.getParameter("op").equals("excluir")) {
            // inserir contato
            opcao  = "Excluir Contato";
            opc = "excluir";
            // dados do contato
            id   =  Integer.parseInt(req.getParameter("id"));
            Contato contato = new Contato(id);
            nome = contato.getNome();
            telefone = contato.getTelefone();
            celular  = contato.getCelular();
            telefone2 = contato.getTelefone2();
            celular2  = contato.getCelular2();
            email    = contato.getEmail();
        }


        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("   <head>");
        out.println("       <title>Contatos</title>");
        out.println("   </head>");
        out.println("   <body>");
        out.println(String.format("<h1>Fomulario de Contatos - %s </h1>", opcao));
        out.println("<form action='actionContato' method='POST'>");

        out.println(String.format("   <input type='hidden' name='opc' value='%s'>", opc));

        if (!req.getParameter("op").equals("novo")) {
            out.println(         String.format("   ID:   <input type='text' name='id' value='%d' readonly> <br>", id));
        }

        out.println(           String.format("   Nome:   <input type='text' name='nome' value='%s'> <br>", nome) );
        out.println(           String.format("   Telefone:   <input type='text' name='telefone' value='%s'> <br>", telefone));
        out.println(           String.format("   Celular:   <input type='text' name='celular' value='%s'> <br>", celular));
        out.println(           String.format("   Telefone 2:   <input type='text' name='telefone2' value='%s'> <br>", telefone2));
        out.println(           String.format("   Celular 2:   <input type='text' name='celular2' value='%s'> <br>", celular2));
        out.println(           String.format("   E-mail:   <input type='text' name='email' value='%s'> <br>", email));
        out.println(            "   <input type='submit' value='Salvar'>");
        out.println(            "</form>");
        out.println("   </body>");
        out.println("</html>");
    }
}
