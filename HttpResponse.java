import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class HttpResponse {
    // Création d'un BufferedWriter pour écrire la réponse
    private BufferedWriter output;
    // Création d'un OutputStream pour écrire la réponse (avec les bytes)
    private OutputStream outputstream;

    public HttpResponse(Socket socket){
        // Instanciation du BufferedWriter et de l'OutputStream + gestion erreurs
        try {
            this.output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.outputstream = socket.getOutputStream();                                                                                          
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    // Méthode pour envoyer une réponse "OK" au client
    public void ok(String message){
        try {
            this.output.write(message);
            output.flush();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    // Méthode pour envoyer une réponse "Not Found" au client
    public void notFound(String message){
        try {
            this.output.write("HTTP/1.0 404 Not Found\n");
            this.output.write(message);
            output.flush();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    // Méthode pour envoyer du contenu au client
    public void sendContent(String contentType, String content){
        try {
            // Envoi de la réponse au client
            output.write("HTTP/1.0 200 OK\n");
            // Envoi du type de contenu
            output.write("Content-Type: " + contentType + "\n\n");
            // Envoi du contenu
            output.write(content + "\n");
            output.write("\n");
            // Vidage du buffer
            output.flush();
        } catch (Exception e) {
            System.err.println(e.getMessage());}
    }

    // Méthode pour envoyer un fichier au client
    public void sendFile(String contentType, String fileName){
        try {
            outputstream.write("HTTP/1.0 200 OK\r\n".getBytes());
            outputstream.write(("Content-Type: " + contentType + "\r\n\r\n").getBytes());
            // Envoi du fichier au client
            FileInputStream fileInputStream = new FileInputStream(fileName);
            // Création d'un tableau de bytes pour stocker les données du fichier
            byte[] bytes = new byte[4096];
            int bytesRead;
            // Lecture du fichier et envoi des données au client
            // Tant qu'il y a des données à lire
            while ((bytesRead = fileInputStream.read(bytes)) != -1) {
                outputstream.write(bytes, 0, bytesRead);
            }
            outputstream.flush();
            fileInputStream.close();
            
        } catch (Exception e) {
            System.err.println(e.getMessage());}
    }
}
