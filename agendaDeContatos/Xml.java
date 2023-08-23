package agendaDeContatos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Xml implements Agenda {

    public Xml() {
       
    }

    @Override
    public void adicionarContato(Contato contato) {
        StringBuilder xmlData = new StringBuilder();
        xmlData.append("<contato>\n");
        xmlData.append("    <nome>").append(contato.getNome()).append("</nome>\n");
        xmlData.append("    <telefone>").append(contato.getTelefone()).append("</telefone>\n");
        xmlData.append("    <email>").append(contato.getEmail()).append("</email>\n");
        xmlData.append("    <dataNascimento>").append(contato.getDataNascimento().toString()).append("</dataNascimento>\n");
        xmlData.append("</contato>\n");

        try {
            String pastaDownloads = System.getProperty("user.home") + "/Documents/Agenda/xml";
            String caminhoCompleto = pastaDownloads + "/" + "contatos.xml";

            File file = new File(caminhoCompleto);
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write(xmlData.toString());
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}