import java.io.File;
import java.net.Socket;

public class RequestProcessor implements Runnable{
    private Socket clientSocket;
    private HttpContext context;
    // Constructeur
    RequestProcessor(Socket clientSocket){
        this.clientSocket = clientSocket;
        this.context = new HttpContext(clientSocket);
    }
    // Méthode pour traiter la requête
    @Override
    // Redéfinition de la méthode run() de l'interface Runnable
    public void run() {
        process();
        try {
            clientSocket.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }



    public void process() {
        HttpRequest request = context.getRequest();
        HttpResponse response = context.getResponse();
        // Récupération de l'url et de la méthode
        String url = request.getUrl();
        String method = request.getMethod();
        System.out.println("Method: " + method);
        System.out.println("URL: " + url);
        // Création d'un objet File pour vérifier l'existence du fichier
        File file = new File("public" + url);
        // Différents cas de figure pour traiter la requête en fonction de l'url
        if (url.equals("/")) {
            response.sendContent("text/html", "<strong>Hello World<strong>");
        }
        else if (url.equals("/index.html")) {
            response.sendFile("text/html", "public/index.html");
        }
        else if (url.endsWith(".css")) {
            response.sendFile("text/css", "public" + url);
        }
        else if (url.endsWith(".svg")) {
            response.sendFile("image/svg+xml", "public" + url);
        }
        else if (url.endsWith(".png")) {
            response.sendFile("image/png", "public" + url);
        }
        else if (url.endsWith(".jpg")) {
            response.sendFile("image/jpeg", "public" + url);
        }
        else if (file.exists()){
            response.sendFile("text/html", "public" + url);
        }
        // Cas où l'url n'existe pas
        else {
            response.notFound("Not Found");
        }
    }
}
