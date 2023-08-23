package agendaDeContatos;

import javax.xml.parsers.ParserConfigurationException;

public class Main {

	public static void main(String[] args) throws ParserConfigurationException {
		// TODO Auto-generated method stub
        
        Agenda agenda = new Xml();
        
        Contato novoContato = new Contato();
        
        novoContato.setNome("André Lopes");
        novoContato.setTelefone("4002-8922");
        novoContato.setEmail("andre.lopes@gmail.com");
        novoContato.setDataNascimento("18-04-1973");
        
        agenda.adicionarContato(novoContato);
        
        System.out.println("Contato adicionado com sucesso!");
	}

}