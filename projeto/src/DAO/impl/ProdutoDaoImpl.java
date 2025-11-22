package DAO.impl;

import DAO.api.DAO;
import DAO.api.ProdutoDAO;
import model.ProdutoModel;

import java.util.List;

public class ProdutoDaoImpl implements ProdutoDAO {

   private static final String INSERT = "Insert into produto (codigo, nome, quantidade) values (?, ?, ?)";
   private static final String UPDATE = "UPDATE produto SET codigo = ?, nome = ?, quantidade =?";
   private static final String DELETE =  "DELETE FROM produto WHERE codigo = ?";
   private static final String FIND_ALL = "SELECT * FROM produto";

    @Override
    public void salvar(ProdutoModel produto){
        if (produto != null && produto.getCodigo() == 0){
            this.salvar(produto);
        } else {
            this.atualizar(produto);
        }
    }

    @Override
    public void atualizar(ProdutoModel produtoModel) {

    }

    @Override
    public void excluir(int id) {

    }

    public List<ProdutoModel> listarTodos() {

        return List.of();
    }

    @Override
    public List<ProdutoModel> listarEstoqueBaixo() {
        return List.of();
    }
}
