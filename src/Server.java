import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private final ServerSocket listenSocket;
    private Socket socket;
    private final static int port = 8080;
    private BufferedReader in;
    private PrintWriter out;
    private final ArrayList<Autore> catalogo;

    public Server() throws IOException {
        this.listenSocket = new ServerSocket(port);
        this.catalogo = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            catalogo.add(new Autore(i));
        }
    }

    public void connect() throws IOException {
        this.socket = listenSocket.accept();
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(), true);
    }

    public void select() throws IOException {
        System.out.println("Waiting for command");
        String readLine;
        String command = "";
        while((readLine = in.readLine()) != null){
            System.out.println(readLine);
            if(readLine.equalsIgnoreCase("end.")) break;
            command = readLine;
        }
        System.out.println("Eseguo " + command);
        switch (command) {
            case "getAllAuthors":
                getAllAuthors();
            break;
            case "getAuthorId":
                getAuthorId();
            break;
            case "addAuthor":
                addAuthor();
            break;
            default:
                System.out.println("Comando sbagliato:");
                System.out.println(readLine);
            break;
        }
    }

    private void getAllAuthors(){
        for(int i = 0; i < catalogo.size(); i++){
            out.print(catalogo.get(i)+"\n");
        }
        out.println("end.");
    }

    private void getAuthorId() throws IOException {
        String readLine;
        String authName = "";
        while((readLine = in.readLine()) != null){
            if(readLine.equalsIgnoreCase("end.")) break;
            authName = readLine;
        }
        System.out.println(authName);
        for(int i = 0; i <  catalogo.size(); i++){
            if(catalogo.get(i).getName().equalsIgnoreCase(authName)){
                out.println(catalogo.get(i).getId()+"\nend.");
                break;
            }
        }
        out.println("end.");
    }

    private void addAuthor() throws IOException {
        String readLine;
        String authName = "";
        boolean trovato = false;
        while((readLine = in.readLine()) != null){
            if(readLine.equalsIgnoreCase("end.")) break;
            authName = readLine;
        }
        for(int i = 0; i < catalogo.size() &&  !trovato; i++){
            if(catalogo.get(i).getName().equalsIgnoreCase(authName)){
                trovato =  true;
            }
        }
        if(!trovato){
            catalogo.add(new Autore(authName));
            out.println(catalogo.get(catalogo.size() - 1).getId());
        }
        out.println("end.");
    }

    public void close() throws IOException {
        socket.close();
        listenSocket.close();
        out.close();
        in.close();
    }
}
