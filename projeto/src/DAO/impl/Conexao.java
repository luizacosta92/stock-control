package DAO.impl;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static Conexao conexao;
    private Connection connection;
    private Conexao(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/estoque", "root", "root");
        } catch (ClassNotFoundException exception) {
            JOptionPane.showMessageDialog(null, "Banco de dados não localizado");
        } catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar banco de dados");
            sqlException.printStackTrace();
        }
    }

    public static Conexao getInstance(){
        if (conexao == null){
            conexao = new Conexao();
        }
        return conexao;
    }

    public Connection getConnection() {
        return connection;
    }
}
