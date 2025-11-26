package DAO.impl;

import DAO.api.ProdutoDAO;
import model.ProdutoModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDaoImpl implements ProdutoDAO {

    private static final String INSERT =
            "INSERT INTO produto (codigo, nome, quantidade) VALUES (?, ?, ?)";

    // só vamos atualizar a quantidade, como pede o enunciado
    private static final String UPDATE =
            "UPDATE produto SET quantidade = ? WHERE codigo = ?";

    private static final String DELETE =
            "DELETE FROM produto WHERE codigo = ?";

    private static final String FIND_ALL =
            "SELECT * FROM produto";

    private static final String FIND_ESTOQUE_BAIXO =
            "SELECT * FROM produto WHERE quantidade < 10";

    private Connection getConnection() {
        return Conexao.getInstance().getConnection();
    }

    @Override
    public void salvar(ProdutoModel produto) {
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT)) {

            ps.setInt(1, produto.getCodigo());
            ps.setString(2, produto.getNome());
            ps.setInt(3, produto.getQuantidade());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar produto", e);
        }
    }

    @Override
    public void atualizar(ProdutoModel produto) {
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE)) {

            // só quantidade
            ps.setInt(1, produto.getQuantidade());
            ps.setInt(2, produto.getCodigo());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar produto", e);
        }
    }

    @Override
    public void excluir(int id) {
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir produto", e);
        }
    }

    @Override
    public List<ProdutoModel> listarTodos() {
        List<ProdutoModel> produtos = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(FIND_ALL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ProdutoModel p = new ProdutoModel();
                p.setCodigo(rs.getInt("codigo"));
                p.setNome(rs.getString("nome"));
                p.setQuantidade(rs.getInt("quantidade"));
                produtos.add(p);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar produtos", e);
        }

        return produtos;
    }

    @Override
    public List<ProdutoModel> listarEstoqueBaixo() {
        List<ProdutoModel> produtos = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(FIND_ESTOQUE_BAIXO);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ProdutoModel p = new ProdutoModel();
                p.setCodigo(rs.getInt("codigo"));
                p.setNome(rs.getString("nome"));
                p.setQuantidade(rs.getInt("quantidade"));
                produtos.add(p);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar produtos com estoque baixo", e);
        }

        return produtos;
    }
}
