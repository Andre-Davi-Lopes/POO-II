package chatSingleton;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.PrintWriter;
import java.net.ServerSocket;

public class ServidorSocket {
    private static ServidorSocket instance;
    private List<Socket> clientes;
    private Map<Socket, String> socketCliente;

    private ServidorSocket() {
    	clientes = new ArrayList<>();
        socketCliente = new HashMap<>();
    }

    public static ServidorSocket getInstance() {
        if (instance == null) {
            instance = new ServidorSocket();
        }
        return instance;
    }

    public void iniciarServidor() throws IOException {
        ServerSocket servidor = null;
        boolean servidorFechado = false;

        try {
            servidor = new ServerSocket(7000);
            System.out.println("Servidor iniciado. Esperando conexões...");

            Thread consoleThread = new Thread(() -> {
                try {
                    BufferedReader mensagemTerminal = new BufferedReader(new InputStreamReader(System.in));
                    while (true) {
                        String mensagemServidor = mensagemTerminal.readLine();
                        for (Socket cliente : clientes) {
                            try {
                                PrintWriter writer = new PrintWriter(new OutputStreamWriter(cliente.getOutputStream()));
                                writer.println("Servidor: " + mensagemServidor);
                                writer.flush();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            consoleThread.start();

            while (!servidorFechado) {
                Socket conexao = servidor.accept();
                clientes.add(conexao);

                BufferedReader nomeUsuarioInput = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
                String nomeUsuario = nomeUsuarioInput.readLine();
                socketCliente.put(conexao, nomeUsuario);

                System.out.println("Cliente conectado: " + conexao.getInetAddress());

                Thread thread = new Thread(new Cliente(conexao, socketCliente));
                thread.start();
            }
        } catch (IOException e) {
            System.out.println("Algo errado aconteceu");
        } finally {
            if (servidor != null) {
                servidor.close();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        ServidorSocket server = ServidorSocket.getInstance();
        server.iniciarServidor();
    }
}


class Cliente implements Runnable {
    private Socket conexao;
    private Map<Socket, String> socketCliente;

    public Cliente(Socket conexao, Map<Socket, String> socketCliente) {
        this.conexao = conexao;
        this.socketCliente = socketCliente;
    }

    @Override
    public void run() {
        try {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(conexao.getInputStream()));

            do {
                String texto = entrada.readLine();
                if (texto == null || "sair".equals(texto)) {
                    break;
                }

                String nomeUsuario = socketCliente.get(conexao);
                System.out.println(nomeUsuario + ": " + texto);

                for (Socket cliente : socketCliente.keySet()) {
                    PrintWriter writer = new PrintWriter(new OutputStreamWriter(cliente.getOutputStream()));
                    writer.println(nomeUsuario + ": " + texto);
                    writer.flush();
                }

            } while (true);
        } catch (IOException e) {
            System.out.println("Erro ao processar mensagem do cliente: " + e.getMessage());
        } finally {
            try {
                conexao.close();
                socketCliente.remove(conexao);
                System.out.println("Cliente desconectado: " + conexao.getInetAddress());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}