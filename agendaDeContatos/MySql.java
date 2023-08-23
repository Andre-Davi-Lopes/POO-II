package agendaDeContatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySql implements Agenda {

	private String url = "jdbc:mysql://localhost:3306/agenda";
    private String user = "root";
    private String password = "";

    public MySql() {
    	
    }

    @Override
    public void adicionarContato(Contato contato) {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String sql = "INSERT INTO contatos (nome, telefone, email, dataNascimento) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, contato.getNome());
            statement.setString(2, contato.getTelefone());
            statement.setString(3, contato.getEmail());
            statement.setString(4, contato.getDataNascimento());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}