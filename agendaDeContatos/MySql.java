package agendaDeContatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    
    @Override
    public String mostrarContato(Contato contato) {
    	StringBuilder stringBuilder = new StringBuilder();
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String sql = "SELECT * FROM contatos WHERE nome = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, contato.getNome());
            ResultSet resultSet = statement.executeQuery();

            stringBuilder.append("Dados => SQL:\n");
            if (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String telefone = resultSet.getString("telefone");
                String email = resultSet.getString("email");
                String dataNascimento = resultSet.getString("dataNascimento");

                stringBuilder.append("Nome: ").append(nome).append("\n");
                stringBuilder.append("Telefone: ").append(telefone).append("\n");
                stringBuilder.append("Email: ").append(email).append("\n");
                stringBuilder.append("Data de Nascimento: ").append(dataNascimento);
            } else {
            	stringBuilder.append("Contato não encontrado.\n");
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return stringBuilder.toString();
    }

}