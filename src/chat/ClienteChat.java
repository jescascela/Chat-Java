package chat;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClienteChat {
    private String ip;
    private int porta;
    
    public ClienteChat(String ip, int porta){
        this.ip = ip;
        this.porta = porta;
    }
    
    public void executa() throws IOException{
        //Conecta no servidor especificado pelo IP e Porta
        Socket cliente = new Socket(ip, porta);
        System.out.println("O cliente esta conectado no servidor.");
        
        //Thread para controlar o recebimento de mensagens do servidor
        Recebedor recebedor = new Recebedor(cliente.getInputStream());
        new Thread((Runnable) recebedor).start();
        
        //Lê as mensagens do teclado
        Scanner input = new Scanner(System.in);
        
        //Variável que enviará mensagem para o servidor
        PrintStream output = new PrintStream(cliente.getOutputStream());
        
        while(input.hasNextLine()){
            output.println(input.nextLine());//Enviar mensagem para o servidor
        }
        
        output.close();
        input.close();
        cliente.close();
    }
}
