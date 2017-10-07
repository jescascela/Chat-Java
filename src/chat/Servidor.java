package chat;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {
    public static void main(String[] args){
        ServidorChat servidor = new ServidorChat(12345);
        try {
            servidor.executa();
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
