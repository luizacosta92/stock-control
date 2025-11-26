package view;

import controller.ProdutoController;
import model.ProdutoModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.util.List;

public class ProdutoView extends JFrame {

    private JTextField txtCodigo;
    private JTextField txtNome;
    private JTextField txtQuantidade;
    private JTable tabela;
    private DefaultTableModel tabelaModel;

    private ProdutoController controller;

    public ProdutoView() {
        this.controller = new ProdutoController();
        initComponents();
    }

    private void initComponents() {
        setTitle("Gerenciamento de Estoque");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);
        setLocationRelativeTo(null);

        JLabel lblCodigo = new JLabel("Código:");
        JLabel lblNome = new JLabel("Nome:");
        JLabel lblQuantidade = new JLabel("Quantidade:");

        txtCodigo = new JTextField(10);
        txtNome = new JTextField(20);
        txtQuantidade = new JTextField(10);

        JButton btnCadastrar = new JButton("Cadastrar");
        JButton btnAtualizar = new JButton("Atualizar quantidade");
        JButton btnExcluir = new JButton("Excluir");
        JButton btnListarTodos = new JButton("Listar todos");
        JButton btnListarBaixo = new JButton("Listar estoque < 10");
        JButton btnBackup = new JButton("Backup para arquivo");

        tabelaModel = new DefaultTableModel(new Object[]{"Código", "Nome", "Quantidade"}, 0);
        tabela = new JTable(tabelaModel);
        JScrollPane scroll = new JScrollPane(tabela);

        JPanel painelCampos = new JPanel(new GridLayout(3, 2));
        painelCampos.add(lblCodigo);
        painelCampos.add(txtCodigo);
        painelCampos.add(lblNome);
        painelCampos.add(txtNome);
        painelCampos.add(lblQuantidade);
        painelCampos.add(txtQuantidade);

        JPanel painelBotoes = new JPanel();
        painelBotoes.add(btnCadastrar);
        painelBotoes.add(btnAtualizar);
        painelBotoes.add(btnExcluir);
        painelBotoes.add(btnListarTodos);
        painelBotoes.add(btnListarBaixo);
        painelBotoes.add(btnBackup);

        setLayout(new BorderLayout());
        add(painelCampos, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);

        btnCadastrar.addActionListener(e -> cadastrarProduto());
        btnAtualizar.addActionListener(e -> atualizarProduto());
        btnExcluir.addActionListener(e -> excluirProduto());
        btnListarTodos.addActionListener(e -> listarTodos());
        btnListarBaixo.addActionListener(e -> listarEstoqueBaixo());
        btnBackup.addActionListener(e -> fazerBackup());
    }

    private void cadastrarProduto() {
        try {
            int codigo = Integer.parseInt(txtCodigo.getText());
            String nome = txtNome.getText();
            int quantidade = Integer.parseInt(txtQuantidade.getText());

            controller.cadastrarProduto(codigo, nome, quantidade);
            JOptionPane.showMessageDialog(this, "Produto cadastrado com sucesso!");
            limparCampos();
            listarTodos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Código e quantidade devem ser números inteiros.");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar produto: " + ex.getMessage());
        }
    }

    private void atualizarProduto() {
        try {
            int codigo = Integer.parseInt(txtCodigo.getText());
            int quantidade = Integer.parseInt(txtQuantidade.getText());

            controller.atualizarQuantidade(codigo, quantidade);
            JOptionPane.showMessageDialog(this, "Quantidade atualizada com sucesso!");
            limparCampos();
            listarTodos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Código e quantidade devem ser números inteiros.");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar produto: " + ex.getMessage());
        }
    }

    private void excluirProduto() {
        try {
            int codigo = Integer.parseInt(txtCodigo.getText());
            controller.excluirProduto(codigo);
            JOptionPane.showMessageDialog(this, "Produto excluído com sucesso!");
            limparCampos();
            listarTodos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Informe um código numérico para excluir.");
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao excluir produto: " + ex.getMessage());
        }
    }

    private void listarTodos() {
        try {
            List<ProdutoModel> produtos = controller.listarTodos();
            preencherTabela(produtos);
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao listar produtos: " + ex.getMessage());
        }
    }

    private void listarEstoqueBaixo() {
        try {
            List<ProdutoModel> produtos = controller.listarEstoqueBaixo();
            preencherTabela(produtos);
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao listar produtos com estoque baixo: " + ex.getMessage());
        }
    }

    private void fazerBackup() {
        JFileChooser chooser = new JFileChooser();
        int opcao = chooser.showSaveDialog(this);
        if (opcao == JFileChooser.APPROVE_OPTION) {
            File arquivo = chooser.getSelectedFile();
            try {
                controller.backup(arquivo);
                JOptionPane.showMessageDialog(this, "Backup gerado em:\n" + arquivo.getAbsolutePath());
            } catch (RuntimeException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao gerar backup: " + ex.getMessage());
            }
        }
    }

    private void preencherTabela(List<ProdutoModel> produtos) {
        tabelaModel.setRowCount(0);
        for (ProdutoModel p : produtos) {
            tabelaModel.addRow(new Object[]{
                    p.getCodigo(),
                    p.getNome(),
                    p.getQuantidade()
            });
        }
    }

    private void limparCampos() {
        txtCodigo.setText("");
        txtNome.setText("");
        txtQuantidade.setText("");
    }
}
