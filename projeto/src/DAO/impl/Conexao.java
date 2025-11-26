package DAO.impl;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

    private static Conexao instance;

    private Conexao() { }

    public static Conexao getInstance() {
        if (instance == null) {
            instance = new Conexao();
        }
        return instance;
    }

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/estoque"
                    + "?useSSL=false"
                    + "&useTimezone=true"
                    + "&serverTimezone=UTC"
                    + "&allowPublicKeyRetrieval=true";

            String user = "root";           // seu usuário
            String password = "SENHA";  // sua senha

            return DriverManager.getConnection(url, user, password);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao conectar ao banco: " + e.getMessage(), e);
        }
    }
}
