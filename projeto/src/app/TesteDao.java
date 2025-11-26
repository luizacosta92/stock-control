package app;

import DAO.impl.ProdutoDaoImpl;
import model.ProdutoModel;

public class TesteDao {
    public static void main(String[] args) {
        try {
            ProdutoDaoImpl dao = new ProdutoDaoImpl();

            ProdutoModel p = new ProdutoModel();
            p.setCodigo(123);
            p.setNome("Produto teste");
            p.setQuantidade(5);

            dao.salvar(p);

            System.out.println("Produtos no banco:");
            dao.listarTodos().forEach(prod ->
                    System.out.println(prod.getCodigo() + " - " +
                            prod.getNome() + " - " +
                            prod.getQuantidade())
            );
        } catch (Exception e) {
            e.printStackTrace(); // <-- importante pra ver o erro real
        }
    }
}
