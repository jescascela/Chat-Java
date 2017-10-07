package chat;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServidorChat {
    private int porta;
    private List<PrintStream> clientes;//Armazena os clientes conectados
    
    public ServidorChat(int porta){
        this.porta = porta;
        this.clientes = new ArrayList<>();
    }
    
    public void executa() throws IOException{
        //Abre a porta especificada
        ServerSocket servidor = new ServerSocket(this.porta);
        System.out.println("Porta " + porta + " aberta.");
        
        while(true){
            //Aceita um cliente
            Socket cliente = servidor.accept();
            
            //Mostra IP do cliente
            System.out.println("Nova conexao com o cliente " + cliente.getInetAddress().getHostAddress());
            
            //Adiciona saída do cliente a lista
            PrintStream ps = new PrintStream(cliente.getOutputStream());
            this.clientes.add(ps);
            
            //Cria tratador de cliente em uma nova thread
            TrataCliente tc = new TrataCliente(cliente.getInputStream(), this);
            new Thread(tc).start();
        }
       
    }
    
    public void distribuiMensagem(String msg){
        //Envia mensagem para todo mundo
        for(PrintStream cliente : this.clientes){
            cliente.println(msg);
        }
    }
    
}
