package agendaDeContatos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Csv implements Agenda {

    public Csv() {
       
    }

    @Override
    public void adicionarContato(Contato contato) {
        StringBuilder csvData = new StringBuilder();
        csvData.append(contato.getNome()).append(",");
        csvData.append(contato.getTelefone()).append(",");
        csvData.append(contato.getEmail()).append(",");
        csvData.append(contato.getDataNascimento().toString()).append("\n");

        try {
            String pastaDownloads = System.getProperty("user.home") + "/Documents/Agenda/csv";
            String caminhoCompleto = pastaDownloads + "/" +  "contatos.csv";

            File file = new File(caminhoCompleto);
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write(csvData.toString());
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public String mostrarContato(Contato contato) {
        StringBuilder csvData = new StringBuilder();
        csvData.append(contato.getNome()).append(",");
        csvData.append(contato.getTelefone()).append(",");
        csvData.append(contato.getEmail()).append(",");
        csvData.append(contato.getDataNascimento()).append("\n");

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Dados => CSV:\n");
        stringBuilder.append(csvData.toString());

        return stringBuilder.toString();
    }
}