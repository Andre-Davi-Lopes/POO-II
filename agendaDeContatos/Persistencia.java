package agendaDeContatos;

public class Persistencia {
	
	protected Agenda agenda;
	
	public Persistencia(Agenda agenda) {
		this.agenda = agenda;
	}
	
	public void persisteDaados(Contato contato) {
		agenda.adicionarContato(contato);
	}
	
	public void mostraDados(Contato contato) {
		agenda.mostrarContato(contato);
	}

}
