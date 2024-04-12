
import java.io.IOException;
import java.net.Socket;

public class HttpContext{
    private Socket socket;
    private HttpRequest request;
    private HttpResponse response;


    public HttpContext(Socket socket) {
        // Création d'un objet HttpRequest et d'un objet HttpResponse
        this.socket = socket;
        this.request = new HttpRequest(socket);
        this.response = new HttpResponse(socket);
    }
    // Getters pour la requête et la réponse
    public HttpRequest getRequest() {
        return request;
    }

    public HttpResponse getResponse() {
        return response;
    }   
    // Méthode pour fermer la connexion
    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

}
