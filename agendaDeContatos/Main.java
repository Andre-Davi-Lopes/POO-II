package agendaDeContatos;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Contato dados = new Contato();
		dados.setNome("André Lopes");
		dados.setTelefone("4002-8922");
		dados.setEmail("andre.lopes@gmail.com");
		dados.setDataNascimento("18-04-1973");

		Agenda agenda = new Csv();
		Persistencia contato = new Persistencia(agenda);
		contato.persisteDaados(dados);
		contato.mostraDados(dados);

		agenda = new Json();
		contato = new Persistencia(agenda);
		contato.persisteDaados(dados);
		contato.mostraDados(dados);

		agenda = new MySql();
		contato = new Persistencia(agenda);
		contato.persisteDaados(dados);
		contato.mostraDados(dados);

		agenda = new Xml();
		contato = new Persistencia(agenda);
		contato.persisteDaados(dados);
		contato.mostraDados(dados);
	}

}