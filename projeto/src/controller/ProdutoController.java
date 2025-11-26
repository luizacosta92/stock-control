package controller;

import model.ProdutoModel;
import service.ServiceApi;
import service.ServiceImpl;

import java.io.File;
import java.util.List;

public class ProdutoController {

    private final ServiceApi service;

    public ProdutoController() {
        this.service = new ServiceImpl();
    }

    public void cadastrarProduto(int codigo, String nome, int quantidade) {
        ProdutoModel p = new ProdutoModel();
        p.setCodigo(codigo);
        p.setNome(nome);
        p.setQuantidade(quantidade);
        service.cadastrarProduto(p);
    }

    public void atualizarQuantidade(int codigo, int quantidade) {
        ProdutoModel p = new ProdutoModel();
        p.setCodigo(codigo);
        p.setQuantidade(quantidade);
        service.atualizarQuantidade(p);
    }

    public void excluirProduto(int codigo) {
        service.excluirProduto(codigo);
    }

    public List<ProdutoModel> listarTodos() {
        return service.listarTodos();
    }

    public List<ProdutoModel> listarEstoqueBaixo() {
        return service.listarEstoqueBaixo();
    }

    public void backup(File arquivo) {
        service.backupParaArquivo(arquivo);
    }
}
