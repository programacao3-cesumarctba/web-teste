package entidades;

import fabrica.FabricaJDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Contato extends Entidade{
    private Integer id;
    private String nome;
    private String telefone;
    private String celular;
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Contrutor padrão - permite instaciar um objeto em memoria
     */
    public Contato() {
    }

    /**
     * Contrutor sobrecarregado com id, recupera os dados do banco para memoria
     */
    public Contato(Integer id) {
        try {
            Contato contatoBd = this.busca(id);
            setId(id);
            setNome(contatoBd.getNome());
            setTelefone(contatoBd.getTelefone());
            setCelular(contatoBd.getCelular());
            setEmail(contatoBd.getEmail());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    @Override
    public List<Contato> busca() throws SQLException {
        List<Contato> contatos = new ArrayList<Contato>();
        // try with resources
        try (Connection conn = FabricaJDBC.criaConn()) {
            String sql = "SELECT * FROM contatos;";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                Contato c = new Contato();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setTelefone(rs.getString("telefone"));
                c.setCelular(rs.getString("celular"));
                c.setEmail(rs.getString("email"));
                contatos.add(c);
            }
        }
        return contatos;
    }

    @Override
    public Contato busca(Integer id) throws SQLException {
        try (Connection conn = FabricaJDBC.criaConn()) {
            String sql = "SELECT * FROM contatos where id = ?;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Contato c = new Contato();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setTelefone(rs.getString("telefone"));
                c.setCelular(rs.getString("celular"));
                c.setEmail(rs.getString("email"));
                return c;
            }
        }
        return null;
    }

    @Override
    public Boolean insere() throws SQLException {
        try (Connection conn = FabricaJDBC.criaConn()) {
            String sql = "INSERT INTO contatos(nome, telefone, celular, email) VALUES (?,?,?,?);";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, getNome());
            preparedStatement.setString(2, getTelefone());
            preparedStatement.setString(3, getCelular());
            preparedStatement.setString(4, getEmail());
            preparedStatement.execute();
            return true;
        }
    }

    @Override
    public Boolean altera() throws SQLException {
        try (Connection conn = FabricaJDBC.criaConn()) {
            String sql = "UPDATE contatos " +
                    "SET nome = ?," +
                    "telefone = ?," +
                    "celular = ?," +
                    "email = ?" +
                    "WHERE id = ?;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, getNome());
            preparedStatement.setString(2, getTelefone());
            preparedStatement.setString(3, getCelular());
            preparedStatement.setString(4, getEmail());
            preparedStatement.setInt( 5, getId());
            preparedStatement.execute();
            return true;
        }
    }

    @Override
    public Boolean exclui() throws SQLException {
        try (Connection conn = FabricaJDBC.criaConn()) {
            String sql = "DELETE from contatos " +
                    "WHERE id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt( 1, getId());
            preparedStatement.execute();
            return true;
        }
    }
}