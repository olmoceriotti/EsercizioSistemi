import java.awt.desktop.SystemEventListener;
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

    public boolean select() throws IOException {
        System.out.println("Waiting for command");
        String readLine;
        String command = "";
        boolean connect = false;
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
            case "addBook":
                addBook();
            break;
            case "getAllBooks":
                getAllBooks();
            break;
            case "getBooksOf":
                getBooksOf();
            break;
            case "Close":
                System.out.println("Client  closed the connection");
                connect  = true;
            break;
            default:
                System.out.println("Comando sbagliato:");
                System.out.println(readLine);
            break;
        }
        return connect;
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

    private void addBook() throws IOException {
        String readLine;
        int index = -1;
        String authName = "";
        String bookName = "";
        boolean trovato = false;
        while((readLine = in.readLine()) != null){
            if(readLine.equalsIgnoreCase("end.")) break;
            authName = readLine;
        }
        while((readLine = in.readLine()) != null){
            if(readLine.equalsIgnoreCase("end.")) break;
            bookName = readLine;
        }
        System.out.println(authName + " " + bookName);
        for(int i = 0;  i < catalogo.size() && !trovato; i++){
            if(catalogo.get(i).getName().equalsIgnoreCase(authName)){
                trovato = true;
                index = i;
            }
        }
        if(catalogo.get(index).addBook(bookName)) out.println("success");
        out.println("end.");
    }

    private void getAllBooks() throws IOException{
        for(int i = 0; i < catalogo.size(); i++){
            for(int j = 0;  j < catalogo.get(i).getLibriSize(); j++){
                out.println(catalogo.get(i).getName() + ":" + catalogo.get(i).getLibro(j));
            }
        }
        out.println("end.");
    }

    private void getBooksOf() throws IOException {
        String readLine;
        String authname = "";
        int indice  = -1;
        boolean trovato = false;
        while((readLine = in.readLine()) != null){
            if(readLine.equalsIgnoreCase("end.")) break;
            authname = readLine;
        }
        for(int i = 0;  i < catalogo.size() && !trovato; i++){
            if(catalogo.get(i).getName().equalsIgnoreCase(authname)){
                trovato = true;
                indice = i;
            }
        }
        if(!trovato) {
            out.println("-1\nend.");
        }else{
            for(int i = 0; i < catalogo.get(indice).getLibriSize(); i++){
                out.println(catalogo.get(indice).getLibro(i));
            }
            out.println("end.");
        }

    }

    public void close() throws IOException {
        socket.close();
        listenSocket.close();
        out.close();
        in.close();
    }
}
