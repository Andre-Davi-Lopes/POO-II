package agendaDeContatos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.Gson;

public class Json implements Agenda {

    public Json() {
    }

    @Override
    public void adicionarContato(Contato contato) {
        Gson gson = new Gson();
        String json = gson.toJson(contato) + "\n";

        try {
            String pastaDownloads = System.getProperty("user.home") + "/Documents/Agenda/json";
            String caminhoCompleto = pastaDownloads + "/" + "contatos.json";

            File file = new File(caminhoCompleto);
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write(json);
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public String mostrarContato(Contato contato) {
        Gson gson = new Gson();
        String json = gson.toJson(contato);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Dados => JSON:\n");
        stringBuilder.append(json);
        
        return stringBuilder.toString();
    }
}