import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {
    // Méthode pour lancer le serveur
    public void run(int portNumber){
        // Création d'un ServerSocket
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(portNumber);
            // Boucle infinie pour accepter les connexions
            while (true){
                // Attente d'une connexion
                Socket clientSocket = serverSocket.accept();
                if(clientSocket.isConnected()){
                    // Création d'un objet RequestProcessor pour traiter la requête
                    // Création d'un Thread supplémenaire pour exécuter le RequestProcessor
                    RequestProcessor requestProc = new RequestProcessor(clientSocket);
                    Thread thread = new Thread(requestProc);
                    thread.start();
                }
            }
        } 
        catch (Exception e) {
            System.err.println(e.getMessage());
        }finally {
            // Fermeture du ServerSocket
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

