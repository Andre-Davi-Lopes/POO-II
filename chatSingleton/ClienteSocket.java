package chatSingleton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClienteSocket {
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        
        Scanner entrada = new Scanner(System.in);

        String texto = "";

        Socket cliente = null;

        PrintStream saida = null;

        boolean userName = true;

        try {
            System.out.print("Endereço IP do servidor: ");
            String ip = entrada.nextLine();

            cliente = new Socket(ip, 7000);
            saida = new PrintStream(cliente.getOutputStream());

            Thread threadReceber = new Thread(new ReceberMensagens(cliente));
            threadReceber.start();

            System.out.println("Conectado ao servidor!");

            do {

                if(userName){
                    System.out.print("Nome de usuário: ");
                    userName = false;
                }

                texto = entrada.nextLine();

                saida.println(texto);

                if ("sair".equals(texto)) {
                    System.out.println("Aviso: você se desconectou do servidor!");
                }

            } while (!"sair".equals(texto));
        } catch (IOException e) {
            System.out.println("Servidor não encontrado");
        } finally {
            if (cliente != null) {
                cliente.close();
            }
            entrada.close();
        }
    }
}

class ReceberMensagens implements Runnable {
    private Socket cliente;

    public ReceberMensagens(Socket cliente) {
        this.cliente = cliente;
    }

    @Override
    public void run() {
        try {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));

            String mensagem;
            while ((mensagem = entrada.readLine()) != null) {
                System.out.println(mensagem);
            }
        } catch (IOException e) {
            System.out.println("Servidor Fechado: " + e.getMessage());
        }
    }
}
