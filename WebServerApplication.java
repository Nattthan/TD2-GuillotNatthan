public class WebServerApplication extends WebServer{
    // Méthode main pour lancer le serveur
    public static void main(String[] args) {
        // Création d'un objet WebServer et lancement du serveur sur le port 80
        WebServer webServer = new WebServer();
        webServer.run(80);
    }
}


