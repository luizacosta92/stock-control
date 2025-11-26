package service;

import model.ProdutoModel;

import java.io.File;
import java.util.List;

public interface ServiceApi {

    void cadastrarProduto(ProdutoModel produto);

    void atualizarQuantidade(ProdutoModel produto);

    void excluirProduto(int codigo);

    List<ProdutoModel> listarTodos();

    List<ProdutoModel> listarEstoqueBaixo();

    void backupParaArquivo(File arquivo);
}
