package DAO.api;

import model.ProdutoModel;

import java.util.List;

public interface ProdutoDAO extends DAO<ProdutoModel> {

    public List<ProdutoModel> listarEstoqueBaixo();



}
