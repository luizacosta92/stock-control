package service;

import DAO.api.ProdutoDAO;
import DAO.impl.ProdutoDaoImpl;
import model.ProdutoModel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ServiceImpl implements ServiceApi {

    private final ProdutoDAO produtoDAO;

    public ServiceImpl() {
        this.produtoDAO = new ProdutoDaoImpl();
    }

    @Override
    public void cadastrarProduto(ProdutoModel produto) {
        validarProdutoParaInclusao(produto);
        produtoDAO.salvar(produto);
    }

    @Override
    public void atualizarQuantidade(ProdutoModel produto) {
        validarProdutoParaAtualizacao(produto);
        produtoDAO.atualizar(produto);
    }

    @Override
    public void excluirProduto(int codigo) {
        produtoDAO.excluir(codigo);
    }

    @Override
    public List<ProdutoModel> listarTodos() {
        return produtoDAO.listarTodos();
    }

    @Override
    public List<ProdutoModel> listarEstoqueBaixo() {
        return produtoDAO.listarEstoqueBaixo();
    }

    @Override
    public void backupParaArquivo(File arquivo) {
        List<ProdutoModel> produtos = listarTodos();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo))) {
            for (ProdutoModel p : produtos) {
                writer.write(p.getCodigo() + ";" + p.getNome() + ";" + p.getQuantidade());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao gerar backup de produtos", e);
        }
    }

    // ======= VALIDAÇÕES =======

    private void validarProdutoParaInclusao(ProdutoModel produto) {
        if (produto.getNome() == null || produto.getNome().isBlank()) {
            throw new IllegalArgumentException("Nome do produto é obrigatório.");
        }
        if (produto.getQuantidade() < 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior ou igual a zero.");
        }
    }

    private void validarProdutoParaAtualizacao(ProdutoModel produto) {
        if (produto.getQuantidade() < 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior ou igual a zero.");
        }
    }
}
