package chat;

import java.io.InputStream;
import java.util.Scanner;

//Classe reponsável por cuidar dos clientes conectados no servidor
public class TrataCliente implements Runnable {

    private InputStream cliente;
    private ServidorChat servidor;
    
    public TrataCliente(InputStream cliente, ServidorChat servidor){
        this.cliente = cliente;
        this.servidor = servidor;
    }

    @Override
    public void run() {
        //Quando chegar uma mensagem distribui para todos
        Scanner input = new Scanner(this.cliente);
        
        while(input.hasNextLine()){
            servidor.distribuiMensagem(input.nextLine());
        }
        
        input.close();
    }
    
}
