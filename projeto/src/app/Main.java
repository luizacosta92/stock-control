package app;

import view.ProdutoView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ProdutoView tela = new ProdutoView();
            tela.setVisible(true);
        });
    }
}
