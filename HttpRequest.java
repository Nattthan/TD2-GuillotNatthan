import java.io.BufferedReader;
import java.net.Socket;
import java.io.InputStreamReader;

public class HttpRequest {
    //stockage variables pour la méthode et l'url
    private String method;
    private String url;

    //lecture de la requête client
    private void readClientRequest(Socket socket) {
        try {
            // Création d'un BufferedReader pour lire la requête client
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // Lecture de la première ligne de la requête puis séparation des informations
            String line = reader.readLine();
            String[] informationsHttp = line.split(" ");
            
            this.method = informationsHttp[0];
            this.url = informationsHttp[1];

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // Constructeur
    public HttpRequest(Socket socket) {
        readClientRequest(socket);
    }
    // Getters pour la méthode et l'url
    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }
}