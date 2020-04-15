package persistencia;

import entidades.Entidade;

import java.sql.SQLException;
import java.util.List;

/**
 * Autor: Maycon de Moraes
 * Descrição essa interface deve ser implementada por todas as entidades que
 * irão algum tipo de persistencia
 */

public interface Persiste {

    public List busca() throws SQLException;

    public Entidade busca(Integer id) throws SQLException;

    public Boolean insere() throws SQLException;

    public Boolean altera() throws SQLException;

    public Boolean exclui() throws SQLException;
}