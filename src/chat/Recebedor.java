package chat;

import java.io.InputStream;
import java.util.Scanner;

public class Recebedor implements Runnable {
    
    private InputStream servidor;
    
    public Recebedor(InputStream servidor){
        this.servidor = servidor;
    }

    @Override
    public void run() {
        // Recebe mensagens do servidor e imprime na tela
        Scanner input = new Scanner(this.servidor);
        
        while(input.hasNext()){
            System.out.println(input.nextLine());
        }
    }
    
    
}
