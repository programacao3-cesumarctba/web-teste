package servlet;

import entidades.Contato;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(
        name = "ContatoRest",
        urlPatterns = {"/api/contato"}
)
public class ContatoRestServlet extends HttpServlet {

    /**
     * Retorna um Contato ou todos os contatos
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setStatus(200);
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");

        if (req.getParameter("id") != null) {
            Contato contato = new Contato(Integer.parseInt(req.getParameter("id")));

            JSONObject obj = new JSONObject();
            obj.put("id",  contato.getId());
            obj.put("nome",  contato.getNome());
            obj.put("telefone", contato.getTelefone());
            obj.put("celular", contato.getCelular());
            obj.put("email", contato.getEmail());

            out.println(obj);
            out.flush();

        } else {
            try {
                ArrayList<JSONObject> listaJson = new ArrayList<>();
                Contato contatos = new Contato();
                List<Contato> listaContatos = contatos.busca();
                for (int i = 0; i < listaContatos.size(); i++) {
                    Contato c = listaContatos.get(i);
                    JSONObject obj = new JSONObject();
                    obj.put("id", c.getId());
                    obj.put("nome", c.getNome());
                    obj.put("telefone", c.getTelefone());
                    obj.put("celular", c.getCelular());
                    obj.put("email", c.getEmail());
                    listaJson.add(obj);
                }
                out.println(listaJson);
                out.flush();
            } catch (SQLException e) {
                e.printStackTrace();
                resp.setStatus(500);
            }
        }
    }


    /**
     * Recebe um contato via POST e cria o recurso no banco
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        resp.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
        resp.addHeader("Access-Control-Max-Age", "1728000");

        try {
            Contato contato = new Contato();
            contato.setNome(req.getParameter("nome"));
            contato.setEmail(req.getParameter("email"));
            contato.setTelefone(req.getParameter("telefone"));
            contato.setCelular(req.getParameter("celular"));
            contato.insere();
            resp.setStatus(201); // status create
        } catch (SQLException e) {
            e.printStackTrace();
            resp.setStatus(500); // server error
        }

    }


    /**
     * Recebe um ID e atualiza o Contato
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");

        BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String data = URLDecoder.decode(br.readLine());
//        out.println(data);
        String[] dataParse = data.split("&");
        Map<String, String> mapa = new HashMap<>();
        for (int i = 0; i < dataParse.length; i++) {
            String[] par = dataParse[i].split("=");
            mapa.put(par[0], par[1]);
        }

        try {
            Contato contato = new Contato(Integer.parseInt(mapa.get("id")));
            contato.setNome(mapa.get("nome"));
            contato.setEmail(mapa.get("email"));
            contato.setTelefone(mapa.get("telefone"));
            contato.setCelular(mapa.get("celular"));
            contato.altera();
            resp.setStatus(201); // status create
        } catch (SQLException e) {
            e.printStackTrace();
            resp.setStatus(500); // server error
        }
    }


    /**
     * Recebe um ID e apaga o Contato
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");

        BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String data = URLDecoder.decode(br.readLine());
        String[] dataParse = data.split("&");
        Map<String, String> mapa = new HashMap<>();
        for (int i = 0; i < dataParse.length; i++) {
            String[] par = dataParse[i].split("=");
            mapa.put(par[0], par[1]);
        }

        try {
            Contato contato = new Contato(Integer.parseInt(mapa.get("id")));
            contato.exclui();
            resp.setStatus(200); // status ok
        } catch (SQLException e) {
            e.printStackTrace();
            resp.setStatus(500); // server error
        }
    }

}
